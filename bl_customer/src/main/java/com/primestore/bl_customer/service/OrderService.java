package com.primestore.bl_customer.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.primestore.bl_customer.client.ProductClient;
import com.primestore.bl_customer.domain.Customer;
import com.primestore.bl_customer.domain.Order;
import com.primestore.bl_customer.domain.ProductInfoOrder;
import com.primestore.bl_customer.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.Map;

/**
 * @author zafarzhon
 * @link <a href="https://github.com/zafarzhon">github</a>
 */
@Service
@RequiredArgsConstructor
public class OrderService {
    private final CustomerService customerService;
    private final ProductClient productClient;
    private final OrderRepository orderRepository;
    private final ObjectMapper objectMapper;

    public Customer makeOrder(String login, String cartJson) {
        Customer customer = customerService.getCustomerByLogin(login);
        Order order = new Order();
        try {
            // Создаем ObjectMapper

            // Парсим JSON в JsonNode
            JsonNode rootNode = objectMapper.readTree(cartJson);

            // Итерируем по всем ключам и значениям
            Iterator<Map.Entry<String, JsonNode>> fields = rootNode.fields();
            int total = 0;
            while (fields.hasNext()) {
                Map.Entry<String, JsonNode> field = fields.next();
                long id = Integer.parseInt(field.getKey()); // Ключ
                int qty = field.getValue().asInt(); // Значение
                String byId = productClient.getProduct(id);
                JsonNode jsonNode = objectMapper.readTree(byId);
                int priceWithDiscount = jsonNode.get("price_with_discount").asInt();
                int countInStock = jsonNode.get("count").asInt();
                if (countInStock < qty) {
                    throw new RuntimeException("Insufficient stock");
                }
                ProductInfoOrder productInfoOrder =
                        ProductInfoOrder.builder().productId(id).quantity(qty).price(priceWithDiscount).build();
                total = total + qty * priceWithDiscount;
                order.setTotalPrice(total);
                order.addProduct(productInfoOrder);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (order.getTotalPrice() == null || order.getTotalPrice() > customer.getBalance()) {
                throw new RuntimeException("Insufficient balance");
            }
            for (var p : order.getProductInfoOrders()) {
                productClient.decrementProduct(p.getProductId(), p.getQuantity());
            }
            customer.setBalance(customer.getBalance() - order.getTotalPrice());
            customer.addOrder(order);
            orderRepository.save(order);
        }
        return customerService.save(customer);
    }


}

package com.primestore.integration_level.dto.order;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.primestore.integration_level.dto.Customer;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zafarzhon
 * @link <a href="https://github.com/zafarzhon">github</a>
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Order {
    private Integer id;
    private List<ProductInfoOrder> productInfoOrders = new ArrayList<>();
    private Customer customer;
    private LocalDateTime createdAt;
    private Status status;
    private Integer totalPrice;
}

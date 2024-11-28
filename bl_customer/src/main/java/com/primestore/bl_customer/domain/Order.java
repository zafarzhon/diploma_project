package com.primestore.bl_customer.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zafarzhon
 * @link <a href="https://github.com/zafarzhon">github</a>
 */

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Order {
    @Id
    @SequenceGenerator(name = "order_seq_generator", sequenceName = "order_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_seq_generator")
    private Long id;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<ProductInfoOrder> productInfoOrders = new ArrayList<>();
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    @JsonBackReference
    private Customer customer;
    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status = Status.IN_PROGRESS;
    private Integer totalPrice;

    public void addProduct(ProductInfoOrder p) {
        productInfoOrders.add(p);
        p.setOrder(this);
    }

    @PrePersist
    protected void onCreate() {
        if (productInfoOrders != null && !productInfoOrders.isEmpty()) {
            totalPrice = productInfoOrders.stream().map(p -> p.getPrice() * p.getQuantity()).mapToInt(p -> p).sum();
        }
    }

    @PreUpdate
    protected void onUpdate() {
        if (productInfoOrders != null && !productInfoOrders.isEmpty()) {
            totalPrice = productInfoOrders.stream().map(p -> p.getPrice() * p.getQuantity()).mapToInt(p -> p).sum();
        }
    }
}

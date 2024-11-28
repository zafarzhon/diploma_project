package com.primestore.bl_customer.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zafarzhon
 * @link <a href="https://github.com/zafarzhon">github</a>
 */
@Table(name = "customers")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Customer {
    @Id
    @SequenceGenerator(name = "customer_seq_generator", sequenceName = "customer_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_seq_generator")
    private Long id;
    @Size(min = 4, max = 20)
    @NotBlank
    @Column(unique = true, nullable = false)
    private String login;
    @NotBlank
    private String password;
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Order> orders = new ArrayList<>();
    @Min(0)
    @Max(1_000_000)
    @Column(columnDefinition = "int default '0'")
    private Integer balance;
    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public void addOrder(Order order) {
        orders.add(order);
        order.setCustomer(this);
    }

    @PrePersist
    protected void onCreate() {
        this.balance = 0;

    }
}

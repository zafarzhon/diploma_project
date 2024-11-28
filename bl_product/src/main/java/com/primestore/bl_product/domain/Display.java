package com.primestore.bl_product.domain;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;
import lombok.*;

/**
 * @author zafarzhon
 * @link <a href="https://github.com/zafarzhon">github</a>
 */

@Entity
@Table(name = "displays")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Display {
    @Id
    @GeneratedValue
    private Long id;
    @Column(columnDefinition = "Numeric(3,1)")
    private Double diagonal;
    private Integer refreshRate;
    @Column(length = 10)
    private String type;
    @Column(length = 30)
    private String resolution;

}
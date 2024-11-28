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
@Table(name = "memories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Memory {
    @Id
    @GeneratedValue
    private Long id;
    private Integer ram;
    @Column(name = "ram_type", length = 10)
    private String ramType;
    private Integer rom;
    @Column(name = "rom_type", length = 10)
    private String romType;
}

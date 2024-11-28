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
@Table(name = "batteries")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Battery {
    @Id
    @GeneratedValue
    private Long id;
    private Integer capacity;
    @Column(name = "max_charge_power", columnDefinition = "Numeric(4,1)")
    private Double maxChargePower;
    @Column(name = "has_wireless_charge")
    private Boolean hasWirelessCharge;
    @Column(name = "has_reverse_charge")
    private Boolean hasReverseCharge;
    @Column(name = "has_fast_charge")
    private Boolean hasFastCharge;
}

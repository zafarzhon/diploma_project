package com.primestore.bl_product.domain;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * @author zafarzhon
 * @link <a href="https://github.com/zafarzhon">github</a>
 */
@Entity
@Table(name = "smartphones")
@DiscriminatorValue("SMARTPHONE")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Smartphone extends Product {
    @Transient
    private String fullName;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cpu_id")
    private Cpu cpu;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "memory_id")
    private Memory memory;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "display_id")
    private Display display;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "battery_id")
    private Battery battery;
    @Column(name = "operation_system", length = 15)
    private String operationSystem;
    @Column(name = "has_ex_memory_card_slot")
    private Boolean hasExMemoryCardSlot;
    @Column(name = "count_sim_cards")
    private Integer countSimCards;
    @Column(name = "esim_support")
    private Boolean esimSupport;
    @Column(name = "communication_standard", length = 10)
    private String communicationStandard;
    @Column(name = "count_main_camera")
    private Integer countMainCamera;
    @Column(name = "main_camera_resolution", length = 30)
    private String mainCameraResolution;
    @Column(name = "has_nfc")
    private Boolean hasNFC;
    @Column(length = 10)
    private String bluetooth;
    @Column(length = 30)
    private String wifi;

    @PostLoad
    public void postLoad() {
        this.fullName = String.format("%s %s %s %d/%d %s", getType(), getBrand(), getModel(), getMemory().getRam(),
                getMemory().getRom(), getColor());
    }
}

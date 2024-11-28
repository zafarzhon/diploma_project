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
@Table(name = "laptops")
@DiscriminatorValue("LAPTOP")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Laptop extends Product {
    @Transient
    private String fullName;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cpu_id")
    private Cpu cpu;
    @Column(length = 30)
    private String gpu;
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
    @Column(name = "camera_resolution")
    private Integer cameraResolution;
    @Column(length = 10)
    private String bluetooth;
    @Column(length = 30)
    private String wifi;
    @Column(name = "for_playing")
    private Boolean forPlaying;

    @PostLoad
    public void postLoad() {
        this.fullName = String.format("%s %s %s %d/%d %s", getType(), getBrand(), getModel(), getMemory().getRam(),
                getMemory().getRom(), getColor());
    }
}

package com.primestore.integration_level.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * @author zafarzhon
 * @link <a href="https://github.com/zafarzhon">github</a>
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Laptop extends ProductDto {
    private String fullName;
    private Cpu cpu;
    private String gpu;
    private Memory memory;
    private Display display;
    private Battery battery;
    private String operationSystem;
    private Integer cameraResolution;
    private String bluetooth;
    private String wifi;
    private Boolean forPlaying;
}

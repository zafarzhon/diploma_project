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
public class Smartphone extends ProductDto {
    private String fullName;
    private Cpu cpu;
    private Memory memory;
    private Display display;
    private Battery battery;
    private String operationSystem;
    private Boolean hasExMemoryCardSlot;
    private Integer countSimCards;
    private Boolean esimSupport;
    private String communicationStandard;
    private Integer countMainCamera;
    private String mainCameraResolution;
    private Boolean hasNFC;
    private String bluetooth;
    private String wifi;
}

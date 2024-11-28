package com.primestore.integration_level.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

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
public class Cpu {
    private Integer id;
    private String model;
    private Integer cores;
}
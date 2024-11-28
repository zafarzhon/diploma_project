package com.primestore.bl_product.dto;

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
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ProductDto {
    private Integer id;
    private Integer productCode;
    private String type;
    private String brand;
    private String model;
    private String description;
    private Integer discount;
    private Integer priceExDiscount;
    private Integer priceWithDiscount;
    private String color;
    private Integer count;
    private Integer warranty;
    private Integer releaseYear;
    private Double height;
    private Double width;
    private Double thickness;
    private Double weight;
    private String country;
}

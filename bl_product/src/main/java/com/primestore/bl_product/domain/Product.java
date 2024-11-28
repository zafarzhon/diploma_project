package com.primestore.bl_product.domain;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Comment;
import org.hibernate.validator.constraints.Length;

import java.util.Random;

/**
 * @author zafarzhon
 * @link <a href="https://github.com/zafarzhon">github</a>
 */
@Entity
@Table(name = "products")
@DiscriminatorColumn(name = "product_type", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("BASE")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@SuperBuilder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public abstract class Product {
    @Id
    @SequenceGenerator(name = "product_generator_seq", sequenceName = "product_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_seq")
    @Column(updatable = false)
    private Long id;
    @Max(value = 9_999_999)
    @Min(value = 1_000_000)
    @Comment("Generated in insert")
    @Column(name = "product_code", nullable = false, unique = true)
    private Integer productCode;
    @Column(nullable = false, length = 10)
    private String type;
    @Column(nullable = false, length = 10)
    private String brand;
    @Column(nullable = false, length = 40)
    private String model;
    @Column(nullable = false, columnDefinition = "bpchar") // to do -> bpchar
    private String description;
    @Min(value = 0)
    @Max(value = 100)
    @Column(nullable = false, columnDefinition = "Integer default 0")
    private Integer discount;
    @Comment("Price without discount")
    @Column(name = "price_ex_discount", nullable = false, columnDefinition = "Integer default 1.0")
    private Integer priceExDiscount;
    @Comment("Price with discount. Generated in update")
    @Column(name = "price_with_discount", nullable = false, columnDefinition = "Integer default 1.0")
    private Integer priceWithDiscount;
    @Comment("Real cost")
    @Column(nullable = false, columnDefinition = "Numeric(9,2) default 1.0")
    private Double cost;
    @Column(length = 20)
    private String color;
    @Min(value = 0)
    @Column(nullable = false, columnDefinition = "Integer default 0.0")
    private Integer count;
    @Column(nullable = false, columnDefinition = "Integer default 0.0")
    @Comment("unit month")
    private Integer warranty;
    @Column(name = "release_year")
    private Integer releaseYear;
    @Comment("Unit millimetres")
    @Column(columnDefinition = "Numeric(7,2)")
    private Double height;
    @Comment("Unit millimetres")
    @Column(columnDefinition = "Numeric(7,2)")
    private Double width;
    @Comment("Unit millimetres")
    @Column(columnDefinition = "Numeric(7,2)")
    private Double thickness;
    @Comment("Unit gram")
    @Column(columnDefinition = "Numeric(8,2)")
    private Double weight;
    @Length(max = 15)
    private String country;

    @PrePersist
    private void prePersist() {
        generateProductCode();
        setPriceWithDiscount(calcPriceWithDiscount());
    }

    private void generateProductCode() {
        if (this.productCode == null) {
            var b = Math.abs(this.brand.hashCode()) % 9 + 1;
            var t = Math.abs(this.type.hashCode()) % 9 + 1;
            var m = Math.abs(this.model.hashCode()) % 9 + 1;
            var left = (b * 100 + t * 10 + m) * 10000;
            var right = new Random().nextInt(9000) + 1000;
            this.productCode = left + right;
        }
    }

    @PreUpdate
    private void preUpdate() {
        setPriceWithDiscount(calcPriceWithDiscount());
    }

    private Integer calcPriceWithDiscount() {
        Integer newPrice = (int) Math.ceil(this.priceExDiscount * (1. - this.discount / 100.));
        return this.priceExDiscount - newPrice < 1 ? 1 : newPrice;
    }
}

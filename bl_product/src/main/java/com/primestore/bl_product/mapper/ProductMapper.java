package com.primestore.bl_product.mapper;

import com.primestore.bl_product.domain.Laptop;
import com.primestore.bl_product.domain.Product;
import com.primestore.bl_product.domain.Smartphone;
import com.primestore.bl_product.dto.LaptopDto;
import com.primestore.bl_product.dto.ProductDto;
import com.primestore.bl_product.dto.SmartphoneDto;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author zafarzhon
 * @link <a href="https://github.com/zafarzhon">github</a>
 */
@Mapper(componentModel = "Spring")
public interface ProductMapper {

    List<ProductDto> productsToDtos(List<Product> products);

    ProductDto productToDto(Product product);

    List<SmartphoneDto> smartphonesToDtos(List<Smartphone> smartphones);

    SmartphoneDto smartphoneToDto(Smartphone smartphone);

    List<LaptopDto> laptopsToDtos(List<Laptop> laptops);

    LaptopDto laptopToDto(Laptop laptop);

}

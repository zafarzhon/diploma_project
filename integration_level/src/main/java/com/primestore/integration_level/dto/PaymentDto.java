package com.primestore.integration_level.dto;

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
//@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PaymentDto {
    private Integer cardNumber;
    private Integer expiryMonth;
    private Integer expiryYear;
    private Integer cvv;
    private Integer amount;
}

package org.writer.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class ItemDtoShow {
    private Long productId;
    private ProductDto productDto;
    private Integer quantity;
    private BigDecimal priceAtPurchase;
}

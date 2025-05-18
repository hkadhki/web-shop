package org.writer.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
public class OrdersDtoShow {
    private Long id;
    private String email;
    private BigDecimal totalAmount;
    private List<ItemDtoShow> items;
}

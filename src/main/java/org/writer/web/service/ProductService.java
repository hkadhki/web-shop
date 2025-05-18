package org.writer.web.service;

import org.writer.web.dto.ProductDto;

import java.util.List;

public interface ProductService {
    List<ProductDto> showAllProducts(Integer limit, Integer offset);
    ProductDto showProductById(Long id);
    List<ProductDto> showProductByTitle(String title, Integer limit, Integer offset);
}

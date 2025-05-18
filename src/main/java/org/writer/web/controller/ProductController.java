package org.writer.web.controller;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.writer.web.dto.ProductDto;
import org.writer.web.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {


    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<ProductDto> showAllProducts(@RequestParam(value = "offset", defaultValue = "0") @Min(0) Integer offset,
                                            @RequestParam(value = "limit", defaultValue = "20") @Min(1) @Max(100) Integer limit) {
        return productService.showAllProducts(limit, offset);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public ProductDto showProductById(@PathVariable(value = "id") Long id) {
        return productService.showProductById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/find")
    public List<ProductDto> showByTitleProducts(@RequestParam String title,
                                                @RequestParam(value = "offset", defaultValue = "0") @Min(0) Integer offset,
                                                @RequestParam(value = "limit", defaultValue = "20") @Min(1) @Max(100) Integer limit) {
        return productService.showProductByTitle(title, limit, offset);
    }
}

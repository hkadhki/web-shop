package org.writer.web.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.writer.web.dto.ProductDto;
import org.writer.web.dto.SpecDto;
import org.writer.web.model.ProductEntity;
import org.writer.web.repository.ProductRepository;
import org.writer.web.repository.SpecRepository;
import org.writer.web.service.ProductService;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final SpecRepository specRepository;

    public ProductServiceImpl(ProductRepository productRepository, SpecRepository specRepository) {
        this.productRepository = productRepository;
        this.specRepository = specRepository;
    }

    @Override
    public List<ProductDto> showAllProducts(Integer limit, Integer offset) {
        Page<ProductDto> products = productRepository.findAllDto(PageRequest.of(offset, limit));

        products.getContent().forEach(product -> {
            List<SpecDto> specs = specRepository.findSpecsByProductId(product.getId());
            product.setSpecs(specs);
        });

        return products.getContent();
    }

    @Override
    public ProductDto showProductById(Long id) {
        ProductDto product = productRepository.findDtoById(id);
        List<SpecDto> specs = specRepository.findSpecsByProductId(id);
        product.setSpecs(specs);

        return product;
    }

    @Override
    public List<ProductDto> showProductByTitle(String title, Integer limit, Integer offset) {
        Page<ProductDto> products = productRepository.findDtoByTitle(title, PageRequest.of(offset, limit));
        products.getContent().forEach(product -> {
            List<SpecDto> specs = specRepository.findSpecsByProductId(product.getId());
            product.setSpecs(specs);
        });

        return products.getContent();
    }

    public ProductEntity getProductById(Long id) {
        Optional<ProductEntity> entity = productRepository.findById(id);
        return entity.orElse(null);
    }
}

package org.writer.web.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.writer.web.dto.ProductDto;
import org.writer.web.dto.SpecDto;
import org.writer.web.model.ProductEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    @Query("""
                SELECT new org.writer.web.dto.ProductDto(
                    p.id, 
                    p.title, 
                    p.description, 
                    p.price, 
                    p.stockQuantity, 
                    p.category.name, 
                    p.brand.name,
                    null 
                ) 
                FROM ProductEntity p
            """)
    Page<ProductDto> findAllDto(Pageable pageable);

    @Query("""
                SELECT new org.writer.web.dto.ProductDto(
                    p.id, 
                    p.title, 
                    p.description, 
                    p.price, 
                    p.stockQuantity, 
                    p.category.name, 
                    p.brand.name,
                    null 
                ) 
                FROM ProductEntity p
                    where p.id = :id
            """)
    ProductDto findDtoById(Long id);


    @Query("""
                SELECT new org.writer.web.dto.ProductDto(
                    p.id, 
                    p.title, 
                    p.description, 
                    p.price, 
                    p.stockQuantity, 
                    p.category.name, 
                    p.brand.name,
                    null 
                ) 
                FROM ProductEntity p
                    where p.title like '%' || :title || '%'
            """)
    Page<ProductDto> findDtoByTitle(String title, Pageable pageable);

    @Modifying
    @Query("""
            UPDATE ProductEntity p
                        SET p.stockQuantity = p.stockQuantity - :quantity
                        WHERE p.id = :productId
            """)
    void updateQuantity(Long productId, Integer quantity);


}

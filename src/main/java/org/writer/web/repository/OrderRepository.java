package org.writer.web.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.writer.web.dto.OrdersDtoShow;
import org.writer.web.dto.ProductDto;
import org.writer.web.model.OrderEntity;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {



    @Query("""
                SELECT new org.writer.web.dto.OrdersDtoShow(
                    o.id, 
                    o.email,
                    o.totalAmount, 
                    null 
                ) 
                FROM OrderEntity o
                    where o.email = :email
            """)
    Page<OrdersDtoShow> findDtoByEmail(String email, Pageable pageable);

    @Query("""
                SELECT new org.writer.web.dto.OrdersDtoShow(
                    o.id, 
                    o.email,
                    o.totalAmount, 
                    null 
                ) 
                FROM OrderEntity o
                    where o.id = :id
            """)
    OrdersDtoShow findDtoById(Long id);
}

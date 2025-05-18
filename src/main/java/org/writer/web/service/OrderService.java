package org.writer.web.service;

import org.writer.web.dto.OrdersDtoCreate;
import org.writer.web.dto.OrdersDtoShow;

import java.util.List;

public interface OrderService {
    void createOrder(OrdersDtoCreate order);
    List<OrdersDtoShow> findByEmail(String email, Integer limit, Integer offset);
    OrdersDtoShow findById(Long id);
}

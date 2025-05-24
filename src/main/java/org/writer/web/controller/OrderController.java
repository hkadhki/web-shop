package org.writer.web.controller;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.writer.web.dto.ItemDtoShow;
import org.writer.web.dto.OrdersDtoCreate;
import org.writer.web.dto.OrdersDtoShow;
import org.writer.web.service.OrderService;

import java.util.List;

@RequestMapping("/api/order")
@RestController
@CrossOrigin(origins = "*")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public String createOrder(@RequestBody OrdersDtoCreate orderDto) {
        orderService.createOrder(orderDto);
        return "Order created";
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<OrdersDtoShow> findByEmail(@RequestParam String email,
                                                   @RequestParam(value = "offset", defaultValue = "0") @Min(0) Integer offset,
                                                   @RequestParam(value = "limit", defaultValue = "20") @Min(1) @Max(100) Integer limit) {

        return orderService.findByEmail(email,limit, offset);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public OrdersDtoShow findById(@PathVariable Long id) {
        return orderService.findById(id);
    }
}

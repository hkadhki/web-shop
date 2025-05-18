package org.writer.web.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.writer.web.dto.ItemDto;
import org.writer.web.dto.ItemDtoShow;
import org.writer.web.dto.OrdersDtoCreate;
import org.writer.web.dto.OrdersDtoShow;
import org.writer.web.model.ItemEntity;
import org.writer.web.model.OrderEntity;
import org.writer.web.model.Status;
import org.writer.web.repository.ItemRepository;
import org.writer.web.repository.OrderRepository;
import org.writer.web.repository.ProductRepository;
import org.writer.web.service.OrderService;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final ItemRepository itemRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderServiceImpl(ItemRepository itemRepository, OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.itemRepository = itemRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    @Override
    public void createOrder(OrdersDtoCreate order) {
        Date date = Date.valueOf(LocalDate.now());
        OrderEntity entity = orderRepository.save(new OrderEntity(null, order.getTotalAmount(), order.getEmail(), Status.NEW.toString(), date,date ));
        for (ItemDto itemDto : order.getItems()) {
            itemRepository.insertItem(itemDto.getPriceAtPurchase(), itemDto.getQuantity(), itemDto.getProductId(), entity.getId());
            productRepository.updateQuantity(itemDto.getProductId(), itemDto.getQuantity());
        }
    }

    @Override
    public List<OrdersDtoShow> findByEmail(String email, Integer limit, Integer offset) {
        List<OrdersDtoShow> ordersDtoShow = orderRepository.findDtoByEmail(email, PageRequest.of(offset, limit)).getContent();
        for(OrdersDtoShow orderDtoShow : ordersDtoShow){
            List<ItemDtoShow> itemDtos = itemRepository.findDtoByOrder(orderDtoShow.getId());
            for(ItemDtoShow itemDto : itemDtos){
                itemDto.setProductDto(productRepository.findDtoById(itemDto.getProductId()));
            }
            orderDtoShow.setItems(itemDtos);
        }
        return ordersDtoShow;
    }

    @Override
    public OrdersDtoShow findById(Long id) {
        OrdersDtoShow ordersDtoShow = orderRepository.findDtoById(id);
        List<ItemDtoShow> itemDtos = itemRepository.findDtoByOrder(id);
        for(ItemDtoShow itemDtoShow : itemDtos){
            itemDtoShow.setProductDto(productRepository.findDtoById(itemDtoShow.getProductId()));
        }
        ordersDtoShow.setItems(itemDtos);
        return ordersDtoShow;
    }
}

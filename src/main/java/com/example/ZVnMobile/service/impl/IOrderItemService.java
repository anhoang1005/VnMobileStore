package com.example.ZVnMobile.service.impl;

import java.util.List;

import com.example.ZVnMobile.dto.OrderItemsDto;
import com.example.ZVnMobile.entities.OrderEntity;

public interface IOrderItemService {
	boolean insertListOrderItems(OrderEntity orderEntity, List<OrderItemsDto> listItems);
}

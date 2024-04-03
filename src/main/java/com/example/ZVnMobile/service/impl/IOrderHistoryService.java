package com.example.ZVnMobile.service.impl;

import com.example.ZVnMobile.entities.OrderEntity;

public interface IOrderHistoryService {
	boolean insertFirstHistory(OrderEntity orderEntity);
	boolean insertNewHistory(OrderEntity orderEntity, String status);
}

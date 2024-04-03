package com.example.ZVnMobile.service.impl;

import com.example.ZVnMobile.dto.OrderTrackingDto;
import com.example.ZVnMobile.entities.OrderEntity;

public interface IOrderTrackingService {
	boolean insertTracking(OrderEntity orderEntity, OrderTrackingDto trackingDto);
}

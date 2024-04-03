package com.example.ZVnMobile.service.impl;

import com.example.ZVnMobile.dto.OrderPaymentDto;
import com.example.ZVnMobile.entities.OrderEntity;

public interface IOrderPayMentService {
	boolean insertPayment(OrderEntity orderEntity, OrderPaymentDto paymentDto);
}

package com.example.ZVnMobile.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ZVnMobile.convert.OrderConverter;
import com.example.ZVnMobile.dto.OrderPaymentDto;
import com.example.ZVnMobile.entities.OrderEntity;
import com.example.ZVnMobile.entities.OrderPaymentEntity;
import com.example.ZVnMobile.repository.OrderPaymentRepository;
import com.example.ZVnMobile.service.impl.IOrderPayMentService;

@Service
public class OrderPaymentService implements IOrderPayMentService{
	
	@Autowired
	private OrderConverter orderConverter;
	
	@Autowired
	private OrderPaymentRepository paymentRepository;

	@Override
	public boolean insertPayment(OrderEntity orderEntity, OrderPaymentDto paymentDto) {
		boolean isSuccess = false;
		try {
			OrderPaymentEntity paymentEntity = new OrderPaymentEntity();
			paymentEntity = orderConverter.orderPaymentDtoToEntity(orderEntity, paymentDto);
			paymentEntity = paymentRepository.save(paymentEntity);
			isSuccess = true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error: " + e.getMessage());
		}
		return isSuccess;
	}

}

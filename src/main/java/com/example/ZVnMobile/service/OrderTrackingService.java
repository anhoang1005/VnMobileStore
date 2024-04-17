package com.example.ZVnMobile.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ZVnMobile.convert.OrderConverter;
import com.example.ZVnMobile.dto.OrderTrackingDto;
import com.example.ZVnMobile.entities.OrderEntity;
import com.example.ZVnMobile.entities.OrderTrackingEntity;
import com.example.ZVnMobile.repository.OrderTrackingRepository;
import com.example.ZVnMobile.service.impl.IOrderTrackingService;

@Service
public class OrderTrackingService implements IOrderTrackingService{
	
	@Autowired
	private OrderTrackingRepository trackingRepository;
	
	@Autowired
	private OrderConverter orderConverter;

	@Override
	public boolean insertTracking(OrderEntity orderEntity, OrderTrackingDto trackingDto) {
		boolean isSuccess = false;
		try {			
			OrderTrackingEntity trackingEntity = orderConverter.orderTrackingDtoToEntity(orderEntity, trackingDto);
			trackingEntity = trackingRepository.save(trackingEntity);
			isSuccess = true;
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();
		}
		return isSuccess;
	}

}

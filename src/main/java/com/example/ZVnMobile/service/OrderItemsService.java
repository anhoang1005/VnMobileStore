package com.example.ZVnMobile.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ZVnMobile.dto.OrderItemsDto;
import com.example.ZVnMobile.entities.OrderEntity;
import com.example.ZVnMobile.entities.OrderItemEntity;
import com.example.ZVnMobile.entities.ProductColorEntity;
import com.example.ZVnMobile.repository.OrderItemsRepository;
import com.example.ZVnMobile.repository.ProductColorRepository;
import com.example.ZVnMobile.service.impl.IOrderItemService;

@Service
public class OrderItemsService implements IOrderItemService{
	
	@Autowired
	private OrderItemsRepository itemsRepository;
	
	@Autowired
	private ProductColorRepository colorRepository;

	@Override
	public boolean insertListOrderItems(OrderEntity orderEntity, List<OrderItemsDto> listItems) {
		boolean isSuccess = false;
		try {
			for(OrderItemsDto itemsDto : listItems) {
				OrderItemEntity itemEntity = new OrderItemEntity();
				itemEntity.setColor(itemsDto.getColor());
				itemEntity.setPrice(itemsDto.getPrice());
				itemEntity.setQuantity(itemsDto.getQuantity());
				itemEntity.setTotalPrice(itemsDto.getTotalPrice());
				itemEntity.setOrderEntityByItem(orderEntity);
				
				ProductColorEntity colorEntity = colorRepository.findOneById(itemsDto.getOrderColorId());
				itemEntity.setProductColorEntityInItem(colorEntity);
				itemEntity = itemsRepository.save(itemEntity);
			}
			isSuccess = true;
		} catch (Exception e) {
			isSuccess = false;
			e.printStackTrace();
			System.out.println("Error: " + e.getMessage());
		}
		return isSuccess;
	}
	
}

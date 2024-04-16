package com.example.ZVnMobile.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ZVnMobile.entities.OrderEntity;
import com.example.ZVnMobile.entities.OrderHistoryEntity;
import com.example.ZVnMobile.repository.OrderHistoryRepository;
import com.example.ZVnMobile.service.impl.IOrderHistoryService;

@Service
public class OrderHistoryService implements IOrderHistoryService{
	
	@Autowired
	private OrderHistoryRepository historyRepository;

	@Override
	public boolean insertFirstHistory(OrderEntity orderEntity) {
		boolean isSuccess = false;
		try {
			OrderHistoryEntity historyEntity = new OrderHistoryEntity();
			historyEntity.setEvent("Đơn hàng được tạo");
			historyEntity.setEventDes("Đơn hàng được bạn tạo vào " + orderEntity.getCreatedAt());
			historyEntity.setCreatedAt(orderEntity.getCreatedAt());
			historyEntity.setOrderEntityInHistory(orderEntity);
			historyEntity = historyRepository.save(historyEntity);
			
			if(historyEntity!=null) {
				isSuccess = true;
			}
		} catch (Exception e) {
			isSuccess = false;
			System.out.println("Error: " + e.getMessage());
		}
		return isSuccess;
	}

	@Override
	public boolean insertNewHistory(OrderEntity orderEntity, String status) {
		boolean isSuccess = false;
		try {
			OrderHistoryEntity historyEntity = new OrderHistoryEntity();
			historyEntity.setCreatedAt(orderEntity.getModifiedAt());
			historyEntity.setEvent(status);
			historyEntity.setEventDes(status + " vào lúc " + orderEntity.getModifiedAt() + "bởi Admin");
			historyEntity.setOrderEntityInHistory(orderEntity);
			
			historyEntity = historyRepository.save(historyEntity);
			if(historyEntity!=null) {
				isSuccess = true;
			}
		} catch (Exception e) {
			isSuccess = false;
			System.out.println("Error: " + e.getMessage());
		}
		return isSuccess;
	}
	
}

package com.example.ZVnMobile.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ZVnMobile.convert.OrderConverter;
import com.example.ZVnMobile.dto.OrderDto;
import com.example.ZVnMobile.entities.OrderEntity;
import com.example.ZVnMobile.entities.UsersEntity;
import com.example.ZVnMobile.payload.DataResponse;
import com.example.ZVnMobile.payload.request.CheckOutRequest;
import com.example.ZVnMobile.repository.OrderRepository;
import com.example.ZVnMobile.repository.UserRepository;
import com.example.ZVnMobile.service.impl.IOrderHistoryService;
import com.example.ZVnMobile.service.impl.IOrderItemService;
import com.example.ZVnMobile.service.impl.IOrderPayMentService;
import com.example.ZVnMobile.service.impl.IOrderService;
import com.example.ZVnMobile.service.impl.IOrderTrackingService;

@Service
public class OrderService implements IOrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OrderConverter orderConverter;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private IOrderTrackingService iTrackingService;

	@Autowired
	private IOrderPayMentService iPayMentService;

	@Autowired
	private IOrderHistoryService ihistoryService;

	@Autowired
	private IOrderItemService iOrderItemService;

	@Override
	public DataResponse getAllOrder() {
		DataResponse dataResponse = new DataResponse();
		List<OrderDto> listOrderDtos = new ArrayList<>();
		try {
			List<OrderEntity> listOrderEntities = orderRepository.findAll();
			for (OrderEntity orderEntity : listOrderEntities) {
				OrderDto orderDto = orderConverter.OrderEntityToOrderDto(orderEntity);
				listOrderDtos.add(orderDto);
			}
			dataResponse.setData(listOrderDtos);
			dataResponse.setSuccess(true);
		} catch (Exception e) {
			dataResponse.setMessage("Loi: " + e.getMessage());
			dataResponse.setData("Loi");
			dataResponse.setSuccess(false);
		}
		return dataResponse;
	}

	@Override
	public DataResponse getOrderByStatus(String status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataResponse getOrderByUserId(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataResponse getOrderById() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataResponse insertOrder(CheckOutRequest checkOutRequest) {
		DataResponse dataResponse = new DataResponse();
		try {
			OrderEntity orderEntity = new OrderEntity();
			UsersEntity usersEntity = userRepository.findByEmail(checkOutRequest.getEmail());
			orderEntity.setUsersEntityInOrder(usersEntity);
			orderEntity.setCustomerNote(checkOutRequest.getCustomerNote());
			orderEntity.setCreatedAt(new Date());
			orderEntity.setStatus("Chờ xác nhận");
			orderEntity.setTotal_price(checkOutRequest.getTotalPrice());
			// Luu Order moi vao csdl
			orderEntity = orderRepository.save(orderEntity);

			boolean isTrackingSuccess = iTrackingService.insertTracking(orderEntity,
					checkOutRequest.getOrderTracking());
			boolean isPaymentSuccess = iPayMentService.insertPayment(orderEntity, checkOutRequest.getOrderPayment());
			boolean isFirstHitorySuccess = ihistoryService.insertFirstHistory(orderEntity);
			boolean isOrderItemsSuccess = iOrderItemService.insertListOrderItems(orderEntity,
					checkOutRequest.getListItem());
			if (isTrackingSuccess == true && isPaymentSuccess == true && isFirstHitorySuccess == true
					&& isOrderItemsSuccess == true) {
				dataResponse.setData("OK");
				dataResponse.setSuccess(true);
			}
		} catch (Exception e) {
			dataResponse.setData("Loi");
			dataResponse.setMessage("Loi: " + e.getMessage());
			dataResponse.setSuccess(false);
		}
		return dataResponse;
	}

	@Override
	public DataResponse updateOrder(Long orderId, String status) {
		DataResponse dataResponse = new DataResponse();
		try {
			OrderEntity orderEntity = orderRepository.findOneById(orderId);
			orderEntity.setStatus(status);
			if (status.equals("Đã nhận hàng")) {
				orderEntity.setDelivery_at(new Date());
			}
			orderEntity = orderRepository.save(orderEntity);
			boolean isInsertHistory = ihistoryService.insertNewHistory(orderEntity, status);

			if (isInsertHistory == true) {
				dataResponse.setData("OK");
				dataResponse.setSuccess(true);
			}
		} catch (Exception e) {
			dataResponse.setMessage("Error: " + e.getMessage());
			dataResponse.setSuccess(false);
		}
		return dataResponse;
	}

	@Override
	public DataResponse cancelOrder(Long orderId, String email) {
		DataResponse dataResponse = new DataResponse();
		try {
			OrderEntity orderEntity = orderRepository.findOneById(orderId);
			if (!orderEntity.getStatus().equals("Đã hủy")) {
				orderEntity.setStatus("Đã hủy");
				orderEntity = orderRepository.save(orderEntity);
				boolean isInsertHistory = ihistoryService.insertNewHistory(orderEntity, "Đã hủy");
				if (isInsertHistory == true) {
					dataResponse.setData(orderEntity.getStatus());
					dataResponse.setSuccess(true);
				}
			}
			else {
				dataResponse.setData("don hang da bi huy truoc do!");
				dataResponse.setSuccess(false);
			}
		} catch (Exception e) {
			dataResponse.setMessage("Error: " + e.getMessage());
			dataResponse.setSuccess(false);
		}
		return dataResponse;
	}
}

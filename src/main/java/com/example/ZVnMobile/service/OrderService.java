package com.example.ZVnMobile.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.ZVnMobile.convert.OrderConverter;
import com.example.ZVnMobile.dto.OrderDto;
import com.example.ZVnMobile.dto.OrderItemsDto;
import com.example.ZVnMobile.entities.OrderEntity;
import com.example.ZVnMobile.entities.OrderHistoryEntity;
import com.example.ZVnMobile.entities.OrderItemEntity;
import com.example.ZVnMobile.entities.ProductColorEntity;
import com.example.ZVnMobile.entities.UsersEntity;
import com.example.ZVnMobile.payload.DataResponse;
import com.example.ZVnMobile.payload.request.CheckOutRequest;
import com.example.ZVnMobile.repository.OrderRepository;
import com.example.ZVnMobile.repository.ProductColorRepository;
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

	@Autowired
	private ProductColorRepository colorRepository;

	@Override
	public DataResponse getAllOrder(int pageNumber) {
		DataResponse dataResponse = new DataResponse();
		List<OrderDto> listOrderDtos = new ArrayList<>();
		try {
			Pageable pageable = PageRequest.of(pageNumber - 1, 5);
			Page<OrderEntity> listOrderEntities = orderRepository.findAll(pageable);
			long pageCount = 0;
			long pageTotal = orderRepository.count();
			if (orderRepository.count() % 10 == 0) {
				pageCount = pageTotal / 10;
			} else {
				pageCount = pageTotal / 10 + 1;
			}

			for (OrderEntity orderEntity : listOrderEntities) {
				OrderDto orderDto = orderConverter.OrderEntityToOrderDto(orderEntity);
				listOrderDtos.add(orderDto);
			}
			dataResponse.setMessage("Success!");
			dataResponse.setPageData(pageCount);
			dataResponse.setData(listOrderDtos);
			dataResponse.setSuccess(true);
		} catch (Exception e) {
			dataResponse.setData("Error");
			dataResponse.setErrorCode(e.getMessage());
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
	public DataResponse getOrderById(Long id) {
		DataResponse dataResponse = new DataResponse();
		try {
			OrderEntity orderEntity = orderRepository.findOneById(id);
			OrderDto orderDto = orderConverter.OrderEntityToOrderDto(orderEntity);
			dataResponse.setData(orderDto);
			dataResponse.setSuccess(true);
			dataResponse.setMessage("Get thành công!");
		} catch (Exception e) {
			dataResponse.setMessage("Error");
			dataResponse.setSuccess(false);
			dataResponse.setErrorCode(e.getMessage());
		}
		return dataResponse;
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
			orderEntity.setTotalPrice(checkOutRequest.getTotalPrice());
			orderEntity.setOrderCode("#DH000" + "TEST");

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
			dataResponse.setData("Error");
			dataResponse.setErrorCode(e.getMessage());
			dataResponse.setSuccess(false);
		}
		return dataResponse;
	}

	@Override
	public DataResponse insertOrderTest(CheckOutRequest checkOutRequest) {
		DataResponse dataResponse = new DataResponse();
		try {
			OrderEntity orderEntity = new OrderEntity();
			UsersEntity usersEntity = userRepository.findByEmail(checkOutRequest.getEmail());
			orderEntity.setUsersEntityInOrder(usersEntity);
			orderEntity.setCustomerNote(checkOutRequest.getCustomerNote());
			orderEntity.setCreatedAt(new Date());
			orderEntity.setStatus("Chờ xác nhận");
			orderEntity.setTotalPrice(checkOutRequest.getTotalPrice());
			orderEntity.setOrderCode("#DH000");

			orderEntity.setOrderTrackingEntityInOrder(orderConverter.orderTrackingDtoToOrderTrackingEntity(orderEntity,
					checkOutRequest.getOrderTracking()));
			orderEntity.setOrderPaymentEntityInOrder(
					orderConverter.orderPaymentDtoToEntity(orderEntity, checkOutRequest.getOrderPayment()));

			List<OrderHistoryEntity> listOrderHistoryEntities = new ArrayList<>();
			OrderHistoryEntity firstHistoryEntity = new OrderHistoryEntity();
			firstHistoryEntity.setEvent("Khởi tạo đơn hàng");
			firstHistoryEntity.setEventDes("Đơn hàng được bạn tạo!");
			firstHistoryEntity.setOrderEntityInHistory(orderEntity);
			listOrderHistoryEntities.add(firstHistoryEntity);
			orderEntity.setListOrderHistoryEntities(listOrderHistoryEntities);

			List<OrderItemEntity> listOrderItemEntities = new ArrayList<>();
			for (OrderItemsDto itemsDto : checkOutRequest.getListItem()) {
				ProductColorEntity colorEntity = colorRepository.findOneById(itemsDto.getOrderColorId());
				OrderItemEntity itemEntity = orderConverter.orderItemDtoToOrderItemEntity(orderEntity, colorEntity,
						itemsDto);
				listOrderItemEntities.add(itemEntity);
			}
			orderEntity.setListOrderItemEntities(listOrderItemEntities);

			orderEntity = orderRepository.save(orderEntity);

			if (orderEntity != null) {
				orderEntity.setOrderCode("#DH000" + orderEntity.getId());
				orderEntity = orderRepository.save(orderEntity);
				dataResponse.setMessage("Thêm đơn hàng " + orderEntity.getId() + " thành công!");
				dataResponse.setData("OK");
				dataResponse.setSuccess(true);
			}

		} catch (Exception e) {
			dataResponse.setData("Error");
			dataResponse.setErrorCode(e.getMessage());
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
				orderEntity.setDeliveryAt(new Date());
			}
			orderEntity = orderRepository.save(orderEntity);
			boolean isInsertHistory = ihistoryService.insertNewHistory(orderEntity, status);

			if (isInsertHistory == true) {
				dataResponse.setData("OK");
				dataResponse.setSuccess(true);
			}
		} catch (Exception e) {
			dataResponse.setData("Error");
			dataResponse.setErrorCode(e.getMessage());
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
			} else {
				dataResponse.setData("Đơn hàng đã bị hủy!");
				dataResponse.setSuccess(false);
			}
		} catch (Exception e) {
			dataResponse.setData("Error");
			dataResponse.setErrorCode(e.getMessage());
			dataResponse.setSuccess(false);
		}
		return dataResponse;
	}

	@Override
	public DataResponse getOrderByUser(String email, String status, int pageNumber) {
		DataResponse dataResponse = new DataResponse();
		try {
			UsersEntity user = userRepository.findByEmail(email);
			Pageable pageable = PageRequest.of(pageNumber - 1, 5);
			Page<OrderEntity> orderPage = orderRepository.findByUsersEntityInOrderAndStatus(user, status, pageable);
			long pageCount = 0;
			long pageTotal = orderRepository.countByUsersEntityInOrderAndStatus(user, status);
			if (pageTotal == 0) {
				pageCount = 0;
			} else {
				if (orderRepository.count() % 10 == 0) {
					pageCount = pageTotal / 10;
				} else {
					pageCount = pageTotal / 10 + 1;
				}
			}
			List<OrderDto> listOrderDtos = new ArrayList<>();
			for (OrderEntity orderEntity : orderPage) {
				OrderDto orderDto = orderConverter.OrderEntityToOrderDto(orderEntity);
				listOrderDtos.add(orderDto);
			}
			dataResponse.setMessage("Success!");
			dataResponse.setPageData(pageCount);
			dataResponse.setData(listOrderDtos);
			dataResponse.setSuccess(true);
		} catch (Exception e) {
			dataResponse.setData("Error");
			dataResponse.setErrorCode(e.getMessage());
			dataResponse.setSuccess(false);
		}
		return dataResponse;
	}

}

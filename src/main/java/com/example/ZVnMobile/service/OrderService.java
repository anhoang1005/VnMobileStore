package com.example.ZVnMobile.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.ZVnMobile.convert.OrderConverter;
import com.example.ZVnMobile.dto.OrderDto;
import com.example.ZVnMobile.dto.OrderItemsDto;
import com.example.ZVnMobile.dto.SaleOrderDto;
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
import com.example.ZVnMobile.service.impl.IOrderService;
import com.example.ZVnMobile.utils.PageUtils;
import com.example.ZVnMobile.utils.ValidateUtils;

@Service
public class OrderService implements IOrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OrderConverter orderConverter;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private IOrderHistoryService ihistoryService;

	@Autowired
	private ProductColorRepository colorRepository;

	@Autowired
	private PageUtils pageUtils;
	
	@Autowired
	private ValidateUtils validateUtils;

	@Override
	public DataResponse getAllOrder(int pageNumber) {
		DataResponse dataResponse = new DataResponse();
		List<OrderDto> listOrderDtos = new ArrayList<>();
		try {
			Pageable pageable = PageRequest.of(pageNumber - 1, 10);
			Page<OrderEntity> listOrderEntities = orderRepository.findAll(pageable);
			for (OrderEntity orderEntity : listOrderEntities) {
				OrderDto orderDto = orderConverter.OrderEntityToOrderDto(orderEntity);
				listOrderDtos.add(orderDto);
			}
			dataResponse.setMessage("Success!");
			dataResponse.setPageData(pageUtils.getPageCount(listOrderEntities.getTotalElements(), 10));
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
	public DataResponse updateOrder(Long orderId, int status) {
		DataResponse dataResponse = new DataResponse();
		String statusStr = "Chờ xác nhận";
		if (status == 1) {
			statusStr = "Chờ xác nhận";
		} else if (status == 2) {
			statusStr = "Đang lấy hàng";
		} else if (status == 3) {
			statusStr = "Đang giao hàng";
		} else if (status == 4) {
			statusStr = "GH thành công";
		} else if (status == 5) {
			statusStr = "Đã hủy";
		}
		try {
			OrderEntity orderEntity = orderRepository.findOneById(orderId);
			orderEntity.setStatus(statusStr);
			if (status == 4) {
				orderEntity.setDeliveryAt(new Date());
			}
			orderEntity = orderRepository.save(orderEntity);
			boolean isInsertHistory = ihistoryService.insertNewHistory(orderEntity, statusStr);

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
			Pageable pageable = PageRequest.of(pageNumber - 1, 10);
			Page<OrderEntity> orderPage = orderRepository.findByUsersEntityInOrderAndStatus(user, status, pageable);
			List<OrderDto> listOrderDtos = new ArrayList<>();
			for (OrderEntity orderEntity : orderPage) {
				OrderDto orderDto = orderConverter.OrderEntityToOrderDto(orderEntity);
				listOrderDtos.add(orderDto);
			}
			dataResponse.setMessage("Success!");
			dataResponse.setPageData(pageUtils.getPageCount(orderPage.getTotalElements(), 10));
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
	public DataResponse getOrderAdminSearchOrder(int status, int gateway, String keyword, int pageNumber) {
		DataResponse dataResponse = new DataResponse();
		String statusStr = "";
		if (status == 0) {
			statusStr = null;
		} else if(status == 1) {
			statusStr = "Chờ xác nhận";
		} else if (status == 2) {
			statusStr = "Đang lấy hàng";
		} else if (status == 3) {
			statusStr = "Đang giao hàng";
		} else if (status == 4) {
			statusStr = "GH thành công";
		} else if (status == 5) {
			statusStr = "Đã hủy";
		}
		String gatewayStr = "";
		if(gateway == 0) {
			gatewayStr = null;
		} else if(gateway == 1) {
			gatewayStr = "Thanh toán khi nhận hàng";
		} else if(gateway == 2) {
			gatewayStr = "VNPAY";
		} else if(gateway == 3) {
			gatewayStr = "ZALOPAY";
		}
		if (keyword.equals("EMPTY")) {
			keyword = null;
		}
		try {
			Pageable pageable = PageRequest.of(pageNumber - 1, 10);
			Page<OrderEntity> orderPage = orderRepository.findOrdersByStatusAndGatewayAndKeyword(statusStr, gatewayStr, keyword, pageable);
			List<OrderDto> listOrderDtos = new ArrayList<>();
			for (OrderEntity orderEntity : orderPage) {
				OrderDto orderDto = orderConverter.OrderEntityToOrderDto(orderEntity);
				listOrderDtos.add(orderDto);
			}
			dataResponse.setMessage("Success!");
			dataResponse.setPageData(pageUtils.getPageCount(orderPage.getTotalElements(), 10));
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
	public DataResponse getOrderAdminSearchOrder2(int status, int gateway, String keyword, String startDate,
		String endDate, int pageNumber) {
		DataResponse dataResponse = new DataResponse();
		String statusStr = "";
		if (status == 0) {
			statusStr = null;
		} else if(status == 1) {
			statusStr = "Chờ xác nhận";
		} else if (status == 2) {
			statusStr = "Đang lấy hàng";
		} else if (status == 3) {
			statusStr = "Đang giao hàng";
		} else if (status == 4) {
			statusStr = "GH thành công";
		} else if (status == 5) {
			statusStr = "Đã hủy";
		}
		String gatewayStr = "";
		if(gateway == 0) {
			gatewayStr = null;
		} else if(gateway == 1) {
			gatewayStr = "Thanh toán khi nhận hàng";
		} else if(gateway == 2) {
			gatewayStr = "VNPAY";
		} else if(gateway == 3) {
			gatewayStr = "ZALOPAY";
		}
		if (keyword.equals("EMPTY")) {
			keyword = null;
		}
		Date startDateFinal;
		Date endDateFinal;
		if(startDate.equals("EMPTY") || endDate.equals("EMPTY")) {
			startDateFinal = null;
			endDateFinal = null;
		} else {
			startDateFinal = validateUtils.convertStringToDate(startDate);
			endDateFinal = validateUtils.convertStringToDate(endDate);
		}
		try {
			Pageable pageable = PageRequest.of(pageNumber - 1, 10);
			Page<OrderEntity> orderPage = orderRepository.adminFind2OrderSort(
					statusStr, gatewayStr, keyword, gatewayStr, startDateFinal, endDateFinal, pageable);
			List<OrderDto> listOrderDtos = new ArrayList<>();
			for (OrderEntity orderEntity : orderPage) {
				OrderDto orderDto = orderConverter.OrderEntityToOrderDto(orderEntity);
				listOrderDtos.add(orderDto);
			}
			dataResponse.setMessage("Success!");
			dataResponse.setPageData(pageUtils.getPageCount(orderPage.getTotalElements(), 10));
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

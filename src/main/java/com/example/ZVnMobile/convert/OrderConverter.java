package com.example.ZVnMobile.convert;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.ZVnMobile.dto.OrderDto;
import com.example.ZVnMobile.dto.OrderHistoryDto;
import com.example.ZVnMobile.dto.OrderItemsDto;
import com.example.ZVnMobile.dto.OrderPaymentDto;
import com.example.ZVnMobile.dto.OrderTrackingDto;
import com.example.ZVnMobile.entities.OrderEntity;
import com.example.ZVnMobile.entities.OrderHistoryEntity;
import com.example.ZVnMobile.entities.OrderItemEntity;
import com.example.ZVnMobile.entities.OrderPaymentEntity;
import com.example.ZVnMobile.entities.OrderTrackingEntity;
import com.example.ZVnMobile.entities.ProductColorEntity;

@Component
public class OrderConverter {

	public OrderDto OrderEntityToOrderDto(OrderEntity entity) {
		OrderDto dto = new OrderDto();
		dto.setOrderId(entity.getId());
		dto.setUserId(entity.getUsersEntityInOrder().getId());
		dto.setCustomerNote(entity.getCustomerNote());
		dto.setTotalPrice(entity.getTotalPrice());
		dto.setStatus(entity.getStatus());
		dto.setCreatedAt(entity.getCreatedAt());
		dto.setUpdatedAt(entity.getModifiedAt());
		dto.setDeliveryAt(entity.getDeliveryAt());

		dto.setOrderTracking(orderTrackingEntityToDto(entity.getOrderTrackingEntityInOrder()));
		dto.setOrderPayment(orderPaymentEntityToDto(entity.getOrderPaymentEntityInOrder()));

		List<OrderHistoryDto> listHistoryDtos = new ArrayList<>();
		for (OrderHistoryEntity historyEntity : entity.getListOrderHistoryEntities()) {
			OrderHistoryDto historyDto = new OrderHistoryDto();
			historyDto = orderHistoryEntityToDto(historyEntity);
			listHistoryDtos.add(historyDto);
		}
		dto.setOrderHistory(listHistoryDtos);

		List<OrderItemsDto> listItemsDtos = new ArrayList<>();
		for (OrderItemEntity itemEntity : entity.getListOrderItemEntities()) {
			OrderItemsDto itemsDto = new OrderItemsDto();
			itemsDto = orderItemsEntityToOrderItemsDto(itemEntity);
			listItemsDtos.add(itemsDto);
		}
		dto.setOrderItems(listItemsDtos);
		return dto;
	}

	public OrderItemsDto orderItemsEntityToOrderItemsDto(OrderItemEntity entity) {
		OrderItemsDto dto = new OrderItemsDto();
		dto.setId(entity.getId());
		dto.setPrice(entity.getPrice());
		dto.setQuantity(entity.getQuantity());
		dto.setTotalPrice(entity.getTotalPrice());
		dto.setOrderColorId(entity.getProductColorEntityInItem().getId());
		dto.setColor(entity.getProductColorEntityInItem().getColor());
		return dto;
	}

	public OrderTrackingDto orderTrackingEntityToDto(OrderTrackingEntity entity) {
		OrderTrackingDto dto = new OrderTrackingDto();
		dto.setCarrier(entity.getCarrier());
		dto.setUrl(entity.getUrl());
		// dto.setOrderId(entity.getOrderId());
		dto.setShippingCode(entity.getShippingCode());
		dto.setFee(entity.getFee());
		dto.setCustomerName(entity.getCustomerName());
		dto.setCustomerTelephone(entity.getCustomerTelephone());
		dto.setCustomerEmail(entity.getCustomerEmail());
		dto.setCustomerAdress(entity.getCustomerAdress());
		dto.setCustomerWard(entity.getCustomerWard());
		dto.setCustomerDistrict(entity.getCustomerDistrict());
		dto.setCustomerProvince(entity.getCustomerProvince());
		return dto;
	}
	
	public OrderTrackingEntity orderTrackingDtoToOrderTrackingEntity(OrderEntity orderEntity, OrderTrackingDto dto) {
		OrderTrackingEntity entity = new OrderTrackingEntity();
		entity.setCarrier(dto.getCarrier());
		entity.setFee(dto.getFee());
		entity.setShippingCode(dto.getShippingCode());
		entity.setUrl(dto.getUrl());
		entity.setCustomerName(dto.getCustomerName());
		entity.setCustomerTelephone(dto.getCustomerTelephone());
		entity.setCustomerEmail(dto.getCustomerEmail());
		entity.setCustomerAdress(dto.getCustomerAdress());
		entity.setCustomerWard(dto.getCustomerWard());
		entity.setCustomerDistrict(dto.getCustomerDistrict());
		entity.setCustomerProvince(dto.getCustomerProvince());
		entity.setOrderEntityInTracking(orderEntity);
		
		return entity;
	}

	public OrderPaymentDto orderPaymentEntityToDto(OrderPaymentEntity entity) {
		OrderPaymentDto dto = new OrderPaymentDto();
		// dto.setOrderId(entity.getOrderId());
		dto.setBankCode(entity.getBankCode());
		dto.setGateway(entity.getGateway());
		dto.setPaymentInfo(entity.getPaymentInfo());
		dto.setStatus(entity.getStatus());
		return dto;
	}

	public OrderHistoryDto orderHistoryEntityToDto(OrderHistoryEntity entity) {
		OrderHistoryDto dto = new OrderHistoryDto();
		dto.setId(entity.getId());
		dto.setEvent(entity.getEvent());
		dto.setEventDes(entity.getEventDes());
		// dto.setOrderId(entity.getOrderEntityInHistory().getId());
		dto.setCreatedAt(entity.getCreatedAt());
		return dto;
	}

	public OrderTrackingEntity orderTrackingDtoToEntity(OrderEntity orderEntity, OrderTrackingDto trackingDto) {
		OrderTrackingEntity trackingEntity = new OrderTrackingEntity();
		trackingEntity.setCarrier(trackingDto.getCarrier());
		trackingEntity.setUrl(trackingDto.getUrl());
		trackingEntity.setShippingCode(trackingDto.getShippingCode());
		trackingEntity.setFee(trackingDto.getFee());
		trackingEntity.setCustomerName(trackingDto.getCustomerName());
		trackingEntity.setCustomerTelephone(trackingDto.getCustomerTelephone());
		trackingEntity.setCustomerEmail(trackingDto.getCustomerEmail());
		trackingEntity.setCustomerAdress(trackingDto.getCustomerAdress());
		trackingEntity.setCustomerWard(trackingDto.getCustomerWard());
		trackingEntity.setCustomerDistrict(trackingDto.getCustomerDistrict());
		trackingEntity.setCustomerProvince(trackingDto.getCustomerProvince());
		trackingEntity.setOrderEntityInTracking(orderEntity);
		return trackingEntity;
	}
	
	public OrderPaymentEntity orderPaymentDtoToEntity(OrderEntity orderEntity, OrderPaymentDto paymentDto) {
		OrderPaymentEntity paymentEntity = new OrderPaymentEntity();
		paymentEntity.setGateway(paymentDto.getGateway());
		paymentEntity.setBankCode(paymentDto.getBankCode());
		paymentEntity.setPaymentInfo(paymentDto.getPaymentInfo());
		paymentEntity.setStatus(paymentDto.getStatus());
		paymentEntity.setOrderEntityInPayMent(orderEntity);
		return paymentEntity;
	}
	
	public OrderItemEntity orderItemDtoToOrderItemEntity(OrderEntity orderEntity, ProductColorEntity productColorEntity, OrderItemsDto dto) {
		OrderItemEntity entity = new OrderItemEntity();
		entity.setColor(dto.getColor());
		entity.setPrice(dto.getPrice());
		entity.setQuantity(dto.getQuantity());
		entity.setTotalPrice((Double)dto.getPrice()*dto.getQuantity());
		entity.setOrderEntityByItem(orderEntity);
		entity.setProductColorEntityInItem(productColorEntity);
		
		return entity;
	}
}

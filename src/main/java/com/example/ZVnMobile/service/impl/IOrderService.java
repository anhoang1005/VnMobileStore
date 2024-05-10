package com.example.ZVnMobile.service.impl;

import com.example.ZVnMobile.payload.DataResponse;
import com.example.ZVnMobile.payload.request.CheckOutRequest;

public interface IOrderService {
	DataResponse getAllOrder(int pageNumber);
	DataResponse getOrderByUser(String email, String status, int pageNumber);
	DataResponse getOrderByStatus(String status);
	DataResponse getOrderByUserId(Long userId);
	DataResponse getOrderById(Long id);
	DataResponse insertOrder(CheckOutRequest checkOutRequest);
	DataResponse insertOrderTest(CheckOutRequest checkOutRequest);
	DataResponse updateOrder(Long orderId ,String status);
	DataResponse cancelOrder(Long orderId, String email);
}

package com.example.ZVnMobile.service.impl;

import com.example.ZVnMobile.payload.DataResponse;
import com.example.ZVnMobile.payload.request.CheckOutRequest;

public interface IOrderService {
	DataResponse getAllOrder(int pageNumber);
	DataResponse getOrderByUser(String email, String status, int pageNumber);
	DataResponse getOrderAdminSearchOrder(int status, int gateway, String keyword, int pageNumber);
	DataResponse getOrderAdminSearchOrder2(int status, int gateway, String keyword, String startDate, String endDate, int pageNumber);
	DataResponse getOrderById(Long id);
	DataResponse insertOrderTest(CheckOutRequest checkOutRequest);
	DataResponse updateOrder(Long orderId , int status);
	DataResponse cancelOrder(Long orderId, String email);
}

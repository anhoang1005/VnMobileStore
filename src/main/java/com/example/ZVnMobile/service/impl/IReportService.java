package com.example.ZVnMobile.service.impl;

import com.example.ZVnMobile.payload.DataResponse;

public interface IReportService {
	DataResponse getSaleOrderOn12MonthOfYear(int year);
	DataResponse getSaleOnQuaterOfYear(int year);
	DataResponse getSaleOn10YearNear();
	DataResponse getSaleOnAllYear();
}

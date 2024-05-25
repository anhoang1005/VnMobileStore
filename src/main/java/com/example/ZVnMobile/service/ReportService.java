package com.example.ZVnMobile.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ZVnMobile.dto.SaleOrderDto;
import com.example.ZVnMobile.payload.DataResponse;
import com.example.ZVnMobile.repository.OrderRepository;
import com.example.ZVnMobile.service.impl.IReportService;

@Service
public class ReportService implements IReportService {
	
	@Autowired
	private OrderRepository orderRepository;

	@Override
	public DataResponse getSaleOrderOn12MonthOfYear(int year) {
		DataResponse dataResponse = new DataResponse();
		try {
			List<Object[]> results = orderRepository.getMonthlyOrderStatistics(year);
//			List<SaleOrderDto> saleOrderDtos = results.stream()
//		            .map(result -> new SaleOrderDto(
//		                ((Number) result[0]).intValue(),
//		                ((Number) result[1]).intValue(),
//		                ((Number) result[2]).longValue(),
//		                (BigDecimal) result[3]
//		            ))
//		            .collect(Collectors.toList());
			List<SaleOrderDto> saleOrderDtos = new ArrayList<>(12);
			IntStream.rangeClosed(1, 12).forEach(month -> {
				saleOrderDtos.add(new SaleOrderDto(year, month, 0, BigDecimal.ZERO));
			});
			results.forEach(result -> {
				int month = ((Number) result[1]).intValue();
				SaleOrderDto dto = saleOrderDtos.get(month - 1);
				dto.setTotalOrders(((Number) result[2]).longValue());
				dto.setRevenue((BigDecimal) result[3]);
			});
			dataResponse.setMessage("OK");
			dataResponse.setData(saleOrderDtos);
			dataResponse.setSuccess(true);
		} catch (Exception e) {
			dataResponse.setData("Error");
			dataResponse.setErrorCode(e.getMessage());
			dataResponse.setSuccess(false);
		}
		return dataResponse;
	}

	@Override
	public DataResponse getSaleOn10YearNear() {
		DataResponse dataResponse = new DataResponse();
		try {
			List<Object[]> results = orderRepository.getSaleOrderOn10Year();
			dataResponse.setMessage("OK");
			dataResponse.setData(results);
			dataResponse.setSuccess(true);
		} catch (Exception e) {
			dataResponse.setData("Error");
			dataResponse.setErrorCode(e.getMessage());
			dataResponse.setSuccess(false);
		}
		return dataResponse;
	}

	@Override
	public DataResponse getSaleOnAllYear() {
		DataResponse dataResponse = new DataResponse();
		try {
			List<Object[]> results = orderRepository.getSaleOrderOnAllYears();
			dataResponse.setMessage("OK");
			dataResponse.setData(results);
			dataResponse.setSuccess(true);
		} catch (Exception e) {
			dataResponse.setData("Error");
			dataResponse.setErrorCode(e.getMessage());
			dataResponse.setSuccess(false);
		}
		return dataResponse;
	}

	@Override
	public DataResponse getSaleOnQuaterOfYear(int year) {
		DataResponse dataResponse = new DataResponse();
		try {
			List<Object[]> results = orderRepository.getSaleOrderByQuaterOnYear(year);
			dataResponse.setMessage("OK");
			dataResponse.setData(results);
			dataResponse.setSuccess(true);
		} catch (Exception e) {
			dataResponse.setData("Error");
			dataResponse.setErrorCode(e.getMessage());
			dataResponse.setSuccess(false);
		}
		return dataResponse;
	}

}

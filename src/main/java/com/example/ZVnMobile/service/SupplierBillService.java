package com.example.ZVnMobile.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.ZVnMobile.entities.SupplierBillEntity;
import com.example.ZVnMobile.payload.DataResponse;
import com.example.ZVnMobile.repository.SupplierBillRepository;
import com.example.ZVnMobile.service.impl.ISupplierBillService;

@Service
public class SupplierBillService implements ISupplierBillService{
	
	@Autowired
	private SupplierBillRepository billRepository;

	@Override
	public DataResponse getAllSupplierBill(int pageNumber) {
		DataResponse dataResponse = new DataResponse();
		try {
			Pageable pageable = PageRequest.of(pageNumber, 15);
			Page<SupplierBillEntity> listBill = billRepository.findAll(pageable);
			long pageCount = 0;
			if(billRepository.count()%15==0) {
				pageCount = billRepository.count()/15;
			} else {
				pageCount = billRepository.count() + 1;
			}
			dataResponse.setData(listBill);
			dataResponse.setPageData(pageCount);
			dataResponse.setMessage("Lấy data Bill thành công!");
			dataResponse.setSuccess(true);
		} catch (Exception e) {
			dataResponse.setMessage("Error");
			dataResponse.setErrorCode(e.getMessage());
			dataResponse.setSuccess(false);
		}
		return dataResponse;
	}

	@Override
	public DataResponse getByCreatedAt(Date createdAt) {
		// TODO Auto-generated method stub
		return null;
	}

}

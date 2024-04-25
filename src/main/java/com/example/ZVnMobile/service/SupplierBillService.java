package com.example.ZVnMobile.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.ZVnMobile.convert.SupplierConverter;
import com.example.ZVnMobile.dto.BillItemDto;
import com.example.ZVnMobile.dto.SupplierBillDto;
import com.example.ZVnMobile.entities.ProductColorEntity;
import com.example.ZVnMobile.entities.ProductTypeEntity;
import com.example.ZVnMobile.entities.SupplierBillEntity;
import com.example.ZVnMobile.entities.SupplierEntity;
import com.example.ZVnMobile.entities.UsersEntity;
import com.example.ZVnMobile.payload.DataResponse;
import com.example.ZVnMobile.payload.request.BillItemsRequest;
import com.example.ZVnMobile.payload.request.BillRequest;
import com.example.ZVnMobile.repository.ProductColorRepository;
import com.example.ZVnMobile.repository.ProductTypeRepository;
import com.example.ZVnMobile.repository.SupplierBillRepository;
import com.example.ZVnMobile.repository.SupplierRepository;
import com.example.ZVnMobile.repository.UserRepository;
import com.example.ZVnMobile.service.impl.ISupplierBillService;
import com.example.ZVnMobile.utils.PoiReportUtils;

@Service
public class SupplierBillService implements ISupplierBillService{
	
	@Autowired
	private SupplierBillRepository billRepository;
	
	@Autowired
	private SupplierRepository supplierRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ProductTypeRepository productTypeRepository;
	
	@Autowired
	private ProductColorRepository productColorRepository;
	
	@Autowired
	private SupplierConverter supplierConverter;
	
	@Autowired
	private PoiReportUtils reportUtils;

	@Override
	public DataResponse getAllSupplierBill(int pageNumber) {
		DataResponse dataResponse = new DataResponse();
		try {
			Pageable pageable = PageRequest.of(pageNumber-1, 15);
			Page<SupplierBillEntity> listBill = billRepository.findAll(pageable);
			List<SupplierBillDto> listBillDtos = new ArrayList<>();
			for(SupplierBillEntity billEntity : listBill) {
				SupplierBillDto dto = supplierConverter.billEntityToBillDto(billEntity);
				listBillDtos.add(dto);
			}
			long pageCount = 0;
			if(billRepository.count()%15==0) {
				pageCount = billRepository.count()/15;
			} else {
				pageCount = billRepository.count()/15 + 1;
			}
			dataResponse.setData(listBillDtos);
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

	@Override
	public DataResponse createBill(BillRequest bill) {
		DataResponse dataResponse = new DataResponse();
		try {
			UsersEntity user = userRepository.findByEmail(bill.getEmailUser());
			SupplierEntity supplier = supplierRepository.findOneById(bill.getSupplierId());
			if(user!=null && supplier!=null && supplier.isDeleted()==true && user.isDeleted()==true ) {
				SupplierBillEntity billEntity = new SupplierBillEntity();
				billEntity.setSupplierEntityInBill(supplier);
				billEntity.setTitle("Phiếu nhập hàng " + supplier.getSupplierName());
				Double totalPrice = 0.0;
				List<BillItemDto> listItemDtos = new ArrayList<>();
				for(BillItemsRequest item : bill.getListBillItems()) {
					ProductTypeEntity typeEntity = productTypeRepository.findOneById(item.getTypeId());
					ProductColorEntity colorEntity = productColorRepository.findOneById(item.getColorId());
					colorEntity = productColorRepository.save(colorEntity);
					colorEntity.setInventoryQuantity(colorEntity.getInventoryQuantity()+item.getQuantity());
					totalPrice = totalPrice + (Double)typeEntity.getBasePrice()*item.getQuantity();
					
					BillItemDto itemDto = new BillItemDto();
					itemDto.setColor(colorEntity.getColor());
					itemDto.setBasePrice(typeEntity.getBasePrice());
					itemDto.setProductId(item.getProductId());
					itemDto.setQuantity(item.getQuantity());
					itemDto.setVersion(typeEntity.getRam() + "GB-" + typeEntity.getRoom()+"GB");
					itemDto.setTitle(typeEntity.getProductEntityInType().getTitle());
					listItemDtos.add(itemDto);
				}
				billEntity.setTotalPrice(totalPrice);
				billEntity.setDescription("OK");
				billEntity = billRepository.save(billEntity);
				Boolean isSuccess = reportUtils.createSupplierBill(user, supplier, listItemDtos, billEntity.getId());
				if(isSuccess == true) {
					billEntity.setDescription("supplier-bill-" + billEntity.getId() + ".docx");
					billEntity = billRepository.save(billEntity);
					System.out.println(billEntity.getId());
					dataResponse.setData("supplier-bill-" + billEntity.getId() + ".docx");
					dataResponse.setMessage("Lưu hóa đơn thành công!");
					dataResponse.setSuccess(true);
				}
				else {
					dataResponse.setMessage("Không thể tạo hóa đơn!");
					dataResponse.setSuccess(false);
				}
			}
			else {
				dataResponse.setMessage("Không hợp lệ!");
				dataResponse.setSuccess(false);
			}
		} catch (Exception e) {
			dataResponse.setErrorCode(e.getMessage());
			dataResponse.setMessage("Error");
			dataResponse.setSuccess(false);
		}
		
		return dataResponse;
	}
}

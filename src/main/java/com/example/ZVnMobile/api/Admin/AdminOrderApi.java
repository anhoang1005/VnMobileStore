package com.example.ZVnMobile.api.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ZVnMobile.service.impl.IOrderService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/admin/order")
public class AdminOrderApi {
	
	@Autowired
	private IOrderService iOrderService;
	
	@GetMapping("/getall/{pageNumber}")
	public ResponseEntity<?> getAllOrder(
			@PathVariable("pageNumber") int pageNumber){
		return new ResponseEntity<>(iOrderService.getAllOrder(pageNumber), HttpStatus.OK);
	}
	
	@PostMapping("/update")
	public ResponseEntity<?> updateOrder(
			@RequestParam("orderId")Long orderId,
			@RequestParam("status") int status){
		return new ResponseEntity<>(iOrderService.updateOrder(orderId, status), HttpStatus.OK);
	}
	
	@GetMapping("/getbyid/{id}")
	public ResponseEntity<?> getOrderById(
			@PathVariable("id") Long id){
		return new ResponseEntity<>(iOrderService.getOrderById(id), HttpStatus.OK);
	}
	
	@GetMapping("/getorderbyid")
	public ResponseEntity<?> adminGetOrderById(
			@RequestParam("id") Long id){
		return new ResponseEntity<>(iOrderService.getOrderById(id), HttpStatus.OK);
	}
	
	@GetMapping("/admin-search")
	public ResponseEntity<?> adminSearchOrder(
			@RequestParam("status") int status,
			@RequestParam("gateway") int gateway,
			@RequestParam("keyword") String keyword,
			@RequestParam("pageNumber") int pageNumber){
		return new ResponseEntity<>(iOrderService.getOrderAdminSearchOrder(status, gateway, keyword, pageNumber), HttpStatus.OK);
	}
	
	@GetMapping("/admin-search2")
	public ResponseEntity<?> adminSearchOrder2(
			@RequestParam("status") int status,
			@RequestParam("gateway") int gateway,
			@RequestParam("keyword") String keyword,
			@RequestParam("pageNumber") int pageNumber,
			@RequestParam("startDate") String startDate,
			@RequestParam("endDate") String endDate){
		return new ResponseEntity<>(iOrderService.getOrderAdminSearchOrder2(status, gateway, keyword, startDate, endDate, pageNumber), HttpStatus.OK);
	}
}

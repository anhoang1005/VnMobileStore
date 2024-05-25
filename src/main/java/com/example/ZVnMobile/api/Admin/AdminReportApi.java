package com.example.ZVnMobile.api.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ZVnMobile.service.impl.IReportService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/admin/report")
public class AdminReportApi {
	
	@Autowired
	private IReportService iReportService;
	
	@GetMapping("/getSaleOrderOnYear")
	public ResponseEntity<?> getSaleOrder(
			@RequestParam("year") int year){
		return new ResponseEntity<>(iReportService.getSaleOrderOn12MonthOfYear(year), HttpStatus.OK);
	}
	
	@GetMapping("/getSaleOrderOn10YearNear")
	public ResponseEntity<?> getSaleOrderOn10YearNear(){
		return new ResponseEntity<>(iReportService.getSaleOn10YearNear(), HttpStatus.OK);
	}
	
	@GetMapping("/getOrderall")
	public ResponseEntity<?> getSaleOrderAllYear(){
		return new ResponseEntity<>(iReportService.getSaleOnAllYear(), HttpStatus.OK);
	}
	
	@GetMapping("/getOrderQuater")
	public ResponseEntity<?> getSaleOrderOnQuaterOfYear(
			@RequestParam("year") int year){
		return new ResponseEntity<>(iReportService.getSaleOnQuaterOfYear(year), HttpStatus.OK);
	}
}

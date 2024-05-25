package com.example.ZVnMobile.api.Admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.ZVnMobile.dto.ProductDto;
import com.example.ZVnMobile.payload.request.InsertProductRequest;
import com.example.ZVnMobile.service.impl.IProductInfoService;
import com.example.ZVnMobile.service.impl.IProductService;
import com.example.ZVnMobile.service.impl.IProductTypeService;
import com.fasterxml.jackson.databind.ObjectMapper;

@CrossOrigin
@RestController
@RequestMapping("/api/admin/product")
public class AdminProductApi {

	@Autowired
	private IProductService iProductService;
	
	@Autowired
	private IProductInfoService iProductInfoService;
	
	@Autowired 
	private IProductTypeService itypeService;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@PostMapping("/inserttest")
	public ResponseEntity<?> insertProduct(
			@RequestBody InsertProductRequest insertProductRequest){
		return new ResponseEntity<>(iProductService.insertProduct(insertProductRequest), HttpStatus.OK);
	}
	
	@PostMapping("/insert")
	public ResponseEntity<?> inserttestProduct(
			@RequestParam("file") MultipartFile file,
	        @RequestParam("listFile") List<MultipartFile> listFile,
			@RequestParam("insertProductRequest") String jsonData){
		try {
	        InsertProductRequest insertProductRequest = objectMapper.readValue(jsonData, InsertProductRequest.class);
	        return new ResponseEntity<>(iProductService.insertProductWithThumbnail(file, listFile, insertProductRequest), HttpStatus.OK);
	    } catch (Exception e) {
	        return ResponseEntity.badRequest().body("Invalid JSON data");
	    }
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> updateProduct(@RequestBody ProductDto productDto){
		return new ResponseEntity<>(iProductService.updateProduct(productDto), HttpStatus.OK);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<?> deleteProduct(
			@RequestParam("id")Long id,
			@RequestParam("deleted")boolean deleted){
		return new ResponseEntity<>(iProductService.lockOrUnlockProduct(id, deleted), HttpStatus.OK);
	}
	
	@GetMapping("/info/{id}")
	public ResponseEntity<?> getInfoByProductId(
			@PathVariable("id")Long id){
		
		return new ResponseEntity<>(iProductInfoService.getByProductId(id), HttpStatus.OK);
	}
	
	@GetMapping("/getbysupplier/{id}/{pageNumber}")
	public ResponseEntity<?> getProductBySupplier(
			@PathVariable("id")Long id,
			@PathVariable("pageNumber")int pageNumber){
		return new ResponseEntity<>(iProductService.getProductBySupplier(id, pageNumber), HttpStatus.OK);
	}
	
	@GetMapping("/dashboard/{pageNumber}")
	public ResponseEntity<?> getDashboad(@PathVariable("pageNumber") int pageNumber){
		return new ResponseEntity<>(iProductService.getProductDashboard(pageNumber), HttpStatus.OK);
	}
	
	@GetMapping("/gettype/{id}")
	public ResponseEntity<?> getTypeProduct(@PathVariable("id")Long id){
		return new ResponseEntity<>(itypeService.getTypeByProductId(id), HttpStatus.OK);
	}
	
	@GetMapping("/getdashboard")
	public ResponseEntity<?> getDashBoard1(
			@RequestParam("category") Long categoryId,
			@RequestParam("supplier") Long supplierId,
			@RequestParam("status") Boolean status,
			@RequestParam("sort") int sort,
			@RequestParam("pageNumber") int pageNumber){
		return new ResponseEntity<>(iProductService.getProductAdminDashboard(categoryId, supplierId, status, sort, pageNumber), HttpStatus.OK);
	}
	
	@GetMapping("/searchById/{id}")
	public ResponseEntity<?> getByIdDashBoardProduct(
			@PathVariable("id")Long id){
		return new ResponseEntity<>(iProductService.getProductByIdDashBoard(id), HttpStatus.OK);
	}
	
	@GetMapping("/searchByTitle")
	public ResponseEntity<?> getByTitleDashBoardProduct(
			@RequestParam("title") String title,
			@RequestParam("pageNumber") int pageNumber){
		return new ResponseEntity<>(iProductService.getProductByTitleLike(title, pageNumber), HttpStatus.OK);
	}
}

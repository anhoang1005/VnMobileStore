package com.example.ZVnMobile.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.List;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;

import com.example.ZVnMobile.dto.BillItemDto;
import com.example.ZVnMobile.entities.SupplierEntity;
import com.example.ZVnMobile.entities.UsersEntity;

@Component
public class PoiReportUtils {
	
	private Path root;
	
	public void init() {
		try {
			root = Paths.get("./upload/bill");		
			if(Files.notExists(root)) {
				Files.createDirectories(root);
			}
		} catch (Exception e) {
			System.err.println("Erorr create file: "+ e.getMessage());
		}
	}
	
	public Resource loadFile(String filename) {
		init();
		try {
			Path file = root.resolve(filename);
			Resource resource = new UrlResource(file.toUri());
			if(resource.exists() || resource.isReadable()) {
				return resource;
			}
		} catch (Exception e) {
			System.err.println("Error load file: " + e.getMessage());
		}
		return null;
	}
	
//	public MultipartFile loadMultiPartFile(String filename) {
//	    init();
//	    try {
//	        Path file = root.resolve(filename);
//	        Resource resource = new UrlResource(file.toUri());
//	        if (resource.exists() && resource.isReadable()) {
//	            File convFile = file.toFile();
//	            FileInputStream input = new FileInputStream(convFile);
//	            MultipartFile multipartFile = new MockMultipartFile("file",
//	                    convFile.getName(), "text/plain", IOUtils.toByteArray(input));
//	            return multipartFile;
//	        }
//	    } catch (Exception e) {
//	        System.err.println("Error load file: " + e.getMessage());
//	    }
//	    return null;
//	}
//	
//	public MultipartFile convertToMultipartFile(String part) throws IOException {
//		try {
//            File docxFile = new File(part);
//            FileInputStream fileInputStream = new FileInputStream(docxFile);
//            byte[] bytes = new byte[(int) docxFile.length()];
//            fileInputStream.read(bytes);
//            fileInputStream.close();
//
//            // Tạo đối tượng MockMultipartFile từ mảng byte
//            MultipartFile multipartFile = new MockMultipartFile("file", docxFile.getName(), "application/vnd.openxmlformats-officedocument.wordprocessingml.document", bytes);
//            return multipartFile;
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//		return null;
//    }

	@SuppressWarnings("resource")
	public boolean createSupplierBill(UsersEntity user, SupplierEntity supplier, List<BillItemDto> listItem, Long id) throws IOException {
		boolean isSuccess = false;
		try {
			XWPFDocument document = new XWPFDocument();
			FileOutputStream fos = new FileOutputStream(new File("upload/bill/supplier-bill-" + id + ".docx"));
			
			XWPFParagraph parag = document.createParagraph();
			parag.setAlignment(ParagraphAlignment.LEFT);
			XWPFRun runthis = parag.createRun();
			runthis.setBold(true);
			runthis.setFontSize(14);
			runthis.setColor("FF0000");
			runthis.setText("CỬA HÀNG ĐIỆN THOẠI VNMOBILE");
			runthis.addTab(); runthis.addTab(); runthis.addTab();
			runthis.setText("     PHIẾU NHẬP HÀNG");
			
			LocalDate now = LocalDate.now();
			String nowString = "Hà Nội, Ngày " + now.getDayOfMonth() + " tháng " + now.getMonthValue() + " năm "
					+ now.getYear();
			parag = document.createParagraph();
			parag.setAlignment(ParagraphAlignment.LEFT);
			runthis = parag.createRun();
			runthis.setFontSize(13);
			runthis.setText("Địa chỉ: Mộ Lao, Hà Đông, Hà Nội");
			runthis.addTab(); runthis.addTab(); runthis.addTab();
			runthis.setText("       " + nowString);
			runthis.addBreak();
			runthis.setText("Số điện thoại: 0867865001");
			runthis.addTab(); runthis.addTab(); runthis.addTab(); runthis.addTab(); 
			runthis.addTab(); runthis.addTab();
			runthis.setText("Mã HĐ: #HD000" + id);
			runthis.addBreak();
			runthis.setText("Email: vananhoang10052002@gmail.com");

			parag = document.createParagraph();
			parag.setAlignment(ParagraphAlignment.LEFT);
			runthis = parag.createRun();
			runthis.setFontSize(13);
			runthis.addBreak();
			runthis.setText("Người tạo phiếu: " + user.getFullName());
			runthis.addBreak();
			runthis.setText("Số điện thoại: " + user.getPhoneNumber());
			runthis.addBreak();
			runthis.setText("Email: " + user.getEmail());
			runthis.addBreak();
			runthis.setText("Nhà cung cấp: " + supplier.getSupplierName());
			runthis.addBreak();
			runthis.setText("Số điện thoại nhà cung cấp: " + supplier.getPhoneNumber());
			runthis.addBreak();
			runthis.setText("Địa chỉ nhà cung cấp: " + supplier.getAdress());

			XWPFTable table = document.createTable();
			XWPFTableRow row = table.getRow(0);
			XWPFTableCell cell = row.getCell(0);
			cell.setWidth("700");
			XWPFParagraph para = cell.getParagraphs().get(0);
			para.setAlignment(ParagraphAlignment.CENTER);
			XWPFRun run = para.createRun();
			run.setBold(true);
			run.setText("STT");

			cell = row.createCell();
			cell.setWidth("1500");
			para = cell.getParagraphs().get(0);
			para.setAlignment(ParagraphAlignment.CENTER);
			run = para.createRun();
			run.setBold(true);
			run.setText("Mã sản phẩm");

			cell = row.createCell();
			cell.setWidth("1500");
			para = cell.getParagraphs().get(0);
			para.setAlignment(ParagraphAlignment.CENTER);
			run = para.createRun();
			run.setBold(true);
			run.setText("Tên sản phẩm");

			cell = row.createCell();
			cell.setWidth("1500");
			para = cell.getParagraphs().get(0);
			para.setAlignment(ParagraphAlignment.CENTER);
			run = para.createRun();
			run.setBold(true);
			run.setText("Phiên bản");

			cell = row.createCell();
			cell.setWidth("800");
			para = cell.getParagraphs().get(0);
			para.setAlignment(ParagraphAlignment.CENTER);
			run = para.createRun();
			run.setBold(true);
			run.setText("Màu");

			cell = row.createCell();
			cell.setWidth("1500");
			para = cell.getParagraphs().get(0);
			para.setAlignment(ParagraphAlignment.CENTER);
			run = para.createRun();
			run.setBold(true);
			run.setText("Số lượng");

			cell = row.createCell();
			cell.setWidth("1500");
			para = cell.getParagraphs().get(0);
			para.setAlignment(ParagraphAlignment.CENTER);
			run = para.createRun();
			run.setBold(true);
			run.setText("Đơn giá");

			cell = row.createCell();
			cell.setWidth("1500");
			para = cell.getParagraphs().get(0);
			para.setAlignment(ParagraphAlignment.CENTER);
			run = para.createRun();
			run.setBold(true);
			run.setText("Thành tiền");
			
			DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
			
			Double totalPrice = 0.0;
			for (int i = 0; i < listItem.size(); i++) {
				XWPFTableRow row1 = table.createRow();
				row1.getCell(0).setText(String.valueOf(i+1));
				row1.getCell(1).setText("#SP00" + listItem.get(i).getProductId());
				row1.getCell(2).setText(listItem.get(i).getTitle());
				row1.getCell(3).setText(listItem.get(i).getVersion());
				row1.getCell(4).setText(listItem.get(i).getColor());
				row1.getCell(5).setText(String.valueOf(listItem.get(i).getQuantity()));
				
				String basePriceFormat = decimalFormat.format(listItem.get(i).getBasePrice());
				String sumPrice = decimalFormat.format(listItem.get(i).getQuantity()*listItem.get(i).getBasePrice());
				
				row1.getCell(6).setText(basePriceFormat + "đ");
				row1.getCell(7).setText(sumPrice + "đ");
				totalPrice = totalPrice + listItem.get(i).getQuantity()*listItem.get(i).getBasePrice();
			}
			XWPFTableRow row1 = table.createRow();
			XWPFTableCell cell6 = row1.getCell(6);
			XWPFParagraph para6 = cell6.getParagraphs().get(0);
			XWPFRun run6 = para6.createRun();
			run6.setBold(true);
			run6.setText("THUẾ VAT(0%)");
			
			XWPFTableRow row2 = table.createRow();
			XWPFTableCell cell62 = row2.getCell(6);
			XWPFParagraph para62 = cell62.getParagraphs().get(0);
			XWPFRun run62 = para62.createRun();
			run62.setBold(true);
			run62.setText("TỔNG CỘNG");
			
			String totalPriceFormat = decimalFormat.format(totalPrice);
			
			XWPFTableCell cell72 = row2.getCell(7);
			XWPFParagraph para72 = cell72.getParagraphs().get(0);
			XWPFRun run72 = para72.createRun();
			run72.setBold(true);
			run72.setText(totalPriceFormat + "đ");
			
			parag = document.createParagraph();
			parag.setAlignment(ParagraphAlignment.LEFT);
			runthis = parag.createRun();
			runthis.setFontSize(13);
			runthis.addBreak();
			runthis.setText("Ghi chú:..................................................."
					+ "..........................................................................");
			runthis.addBreak(); runthis.addBreak();
			runthis.addTab(); runthis.addTab(); runthis.addTab(); runthis.addTab();
			runthis.addTab(); runthis.addTab(); runthis.addTab();
			runthis.setText("        " + nowString);
			
			parag = document.createParagraph();
			parag.setAlignment(ParagraphAlignment.LEFT);
			runthis = parag.createRun();
			runthis.setBold(true);
			runthis.setFontSize(13);
			runthis.setText("NHÀ CUNG CẤP");
			runthis.addTab(); runthis.addTab(); runthis.addTab(); runthis.addTab();
			runthis.addTab(); runthis.addTab();
			runthis.setText("    NHÂN VIÊN NHẬP HÀNG");
			
			document.write(fos);
			fos.close();
			isSuccess = true;
			//System.out.println("Create Success!");
		} catch (IOException e) {
			isSuccess = false;
			e.printStackTrace();
		}
		return isSuccess;
	}
}

package com.example.ZVnMobile.api;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ZVnMobile.utils.PoiReportUtils;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/uploadfile")
public class FileApi {
	
	@Autowired
	private PoiReportUtils poiReportUtils;
	
	@GetMapping("/file/{filename:.+}")
	public ResponseEntity<?> loadRestaurant(@PathVariable("filename") String filename){
		Resource resource = poiReportUtils.loadFile(filename);
		if (resource != null) {
            return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
        } else {
            return ResponseEntity.notFound().build();
        }
	}
	
	
	@GetMapping("/viewfile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(
    		@PathVariable String fileName) throws IOException{
		Path filePath = Paths.get("./upload/bill/" + fileName);
        
        byte[] fileContent = Files.readAllBytes(filePath);
        
        ByteArrayResource resource = new ByteArrayResource(fileContent);
        
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=" + fileName)
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.wordprocessingml.document"))
                .body(resource);
    }

}

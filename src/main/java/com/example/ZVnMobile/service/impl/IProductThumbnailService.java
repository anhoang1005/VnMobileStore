package com.example.ZVnMobile.service.impl;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.ZVnMobile.entities.ProductEntity;

public interface IProductThumbnailService {
	boolean insertListThumbnail(ProductEntity product, List<MultipartFile> listFile);
}

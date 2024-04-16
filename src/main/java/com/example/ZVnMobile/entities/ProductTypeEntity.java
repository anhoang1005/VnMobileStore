package com.example.ZVnMobile.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "product_type")
public class ProductTypeEntity extends BaseEntity{
	
	@Column(columnDefinition = "INT")
	private Integer ram;
	
	@Column(columnDefinition = "INT")
	private Integer room;
	
	@Column(columnDefinition = "DECIMAL")
	private Double basePrice;
	
	@Column(columnDefinition = "DECIMAL")
	private Double price;
	
	@Column(columnDefinition = "DECIMAL")
	private Double discount;
	
//	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "product_id")
	private ProductEntity productEntityInType;

	@OneToMany(mappedBy = "productTypeEntityInColor")
	private List<ProductColorEntity> listTypeColorEntities;

	public Integer getRam() {
		return ram;
	}

	public void setRam(Integer ram) {
		this.ram = ram;
	}

	public Integer getRoom() {
		return room;
	}

	public void setRoom(Integer room) {
		this.room = room;
	}

	public Double getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(Double basePrice) {
		this.basePrice = basePrice;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public ProductEntity getProductEntityInType() {
		return productEntityInType;
	}

	public void setProductEntityInType(ProductEntity productEntityInType) {
		this.productEntityInType = productEntityInType;
	}

	public List<ProductColorEntity> getListTypeColorEntities() {
		return listTypeColorEntities;
	}

	public void setListTypeColorEntities(List<ProductColorEntity> listTypeColorEntities) {
		this.listTypeColorEntities = listTypeColorEntities;
	}

}

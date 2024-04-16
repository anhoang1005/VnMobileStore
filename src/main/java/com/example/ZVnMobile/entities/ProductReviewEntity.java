package com.example.ZVnMobile.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "product_review")
public class ProductReviewEntity extends BaseEntity{
	
	@Column(columnDefinition = "INT")
	private int ratingStar;
	
	@Column(columnDefinition = "TEXT")
	private String comment;
	
	@Column(columnDefinition = "TEXT")
	private String images;
	
	@Column(columnDefinition = "INT")
	private Integer likeCount;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private UsersEntity usersEntityInReview;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private ProductEntity productEntityInReview;

	public int getRatingStar() {
		return ratingStar;
	}

	public void setRatingStar(int ratingStar) {
		this.ratingStar = ratingStar;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public UsersEntity getUsersEntityInReview() {
		return usersEntityInReview;
	}

	public void setUsersEntityInReview(UsersEntity usersEntityInReview) {
		this.usersEntityInReview = usersEntityInReview;
	}

	public ProductEntity getProductEntityInReview() {
		return productEntityInReview;
	}

	public void setProductEntityInReview(ProductEntity productEntityInReview) {
		this.productEntityInReview = productEntityInReview;
	}

	public Integer getLike() {
		return likeCount;
	}

	public void setLike(Integer like) {
		this.likeCount = like;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public Integer getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(Integer likeCount) {
		this.likeCount = likeCount;
	}
}

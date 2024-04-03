package com.example.ZVnMobile.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "product_review")
public class ProductReviewEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition = "INT")
	private int ratingStar;
	
	@Column(columnDefinition = "TEXT")
	private String comment;
	
	@Column(columnDefinition = "INT")
	private Integer likeCount;
	
	@Column(columnDefinition = "TIMESTAMP")
	private Date createdAt;
	
	@Column(columnDefinition = "TIMESTAMP")
	private Date updateAt;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private UsersEntity usersEntityInReview;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private ProductEntity productEntityInReview;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(Date updateAt) {
		this.updateAt = updateAt;
	}
}

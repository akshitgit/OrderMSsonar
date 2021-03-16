package com.infosys.dto;

public class ProductDTO {
	
	Integer prodId;
	String brand;
	String category;
	String description;
	String image;
	Double price;
	String productName;
	Integer rating;
	Integer sellerId;
	Integer stock;
	String subcategory;
	
	
	public Integer getProdId() {
		return prodId;
	}
	public void setProdId(Integer prodId) {
		this.prodId = prodId;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Integer getRating() {
		return rating;
	}
	public void setRating(Integer rating) {
		this.rating = rating;
	}
	
	public Integer getSellerId() {
		return sellerId;
	}
	public void setSellerid(Integer sellerId) {
		this.sellerId = sellerId;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	public String getSubcategory() {
		return subcategory;
	}
	public void setSubcategory(String subcategory) {
		this.subcategory = subcategory;
	}
	
	
	@Override
	public String toString() {
		return "ProductDTO [productId=" + prodId + ", brand=" + brand + ", category=" + category
				+ ", description=" + description +  ", image=" + image +  ", price=" + price + ", productName=" + productName + 
				", rating=" + rating +  ", sellerId=" + sellerId +  ", stock=" + stock +  ", subcategory=" + subcategory + "]";
	}	

}

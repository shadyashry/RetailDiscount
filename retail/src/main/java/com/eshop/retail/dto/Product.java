package com.eshop.retail.dto;

public class Product {
	private String skuCode;
	private String price;
	private String quantity;
	private ProductCategory productCategory;

	public String getSkuCode() {
		return skuCode;
	}

	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public ProductCategory getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(ProductCategory productCategory) {
		this.productCategory = productCategory;
	}
	
	public double totalAmount() {
		return Double.parseDouble(price) * Integer.parseInt(quantity);
	}

}

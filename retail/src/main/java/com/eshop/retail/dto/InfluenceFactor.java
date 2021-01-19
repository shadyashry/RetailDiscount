package com.eshop.retail.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@AllArgsConstructor
@Setter
@Getter
public class InfluenceFactor {
	private String criteriaName;
	private String criteriaType;
	private String discountValue;
	private DiscountType discountType;
	public InfluenceFactor(String criteriaName, String criteriaType, String discountValue, DiscountType discountType) {
		super();
		this.criteriaName = criteriaName;
		this.criteriaType = criteriaType;
		this.discountValue = discountValue;
		this.discountType = discountType;
	}
	public String getCriteriaName() {
		return criteriaName;
	}
	public void setCriteriaName(String criteriaName) {
		this.criteriaName = criteriaName;
	}
	public String getCriteriaType() {
		return criteriaType;
	}
	public void setCriteriaType(String criteriaType) {
		this.criteriaType = criteriaType;
	}
	public String getDiscountValue() {
		return discountValue;
	}
	public void setDiscountValue(String discountValue) {
		this.discountValue = discountValue;
	}
	public DiscountType getDiscountType() {
		return discountType;
	}
	public void setDiscountType(DiscountType discountType) {
		this.discountType = discountType;
	}


}

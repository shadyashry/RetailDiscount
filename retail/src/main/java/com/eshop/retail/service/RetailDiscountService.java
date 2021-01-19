package com.eshop.retail.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

import com.eshop.retail.dto.Customer;
import com.eshop.retail.dto.DiscountType;
import com.eshop.retail.dto.InfluenceFactor;
import com.eshop.retail.dto.Product;
import com.eshop.retail.dto.ProductCategory;
import com.eshop.retail.utils.DiscountUtil;

@Service
public class RetailDiscountService {
	private static final List<InfluenceFactor> influenceFactors;

	static {
		//it should be in cache comming from db configuration but we hard coded for demo
		InfluenceFactor f = new InfluenceFactor("EMPLOYEE", "STAFF_FACTOR", "30", DiscountType.PRECENTAGE);
		InfluenceFactor f2 = new InfluenceFactor("AFFILIATE", "COMPANY_FACTOR", "10", DiscountType.PRECENTAGE);
		InfluenceFactor f3 = new InfluenceFactor("OLD_CUSTOMER", "2YEAR_FACTOR", "5", DiscountType.PRECENTAGE);
		InfluenceFactor f4 = new InfluenceFactor("OVERALL_BILL", "BILL_FACTOR", "5", DiscountType.CONSTANT);
		influenceFactors = Arrays.asList(f, f2, f3, f4);
	}

	public double getTotalNetProductsPrice(Customer customer, List<Product> products) {
		double totalPrice = getGroceriesProductsTotalPrice(products) + getOtherProductsTotalPrice(customer, products);

		if (totalPrice > 100) {
			InfluenceFactor factor = DiscountUtil.<InfluenceFactor>applyFilterOnCondition(
					f -> "OVERALL_BILL".equals(f.getCriteriaName()), influenceFactors);
			totalPrice = totalPrice - (((int) totalPrice / 100) * Double.valueOf(factor.getDiscountValue()));
		}

		System.out.println("TOTAL FINAL PRICE = [" + totalPrice + "]");
		System.out.println("------------------------------------------------------------");

		return totalPrice;
	}

	private double getGroceriesProductsTotalPrice(List<Product> products) {

		double otherProductPrice = products.stream()
				.filter(p -> ProductCategory.GROCERIES.name().equals(p.getProductCategory().name()))
				.mapToDouble(Product::totalAmount).sum();
		System.out.println("TOTAL GROCERIES PRICE = [" + otherProductPrice + "]");
		return otherProductPrice;
	}

	private double getOtherProductsTotalPrice(Customer customer, List<Product> products) {

		double otherProductPrice = products.stream()
				.filter(p -> ProductCategory.OTHERS.name().equals(p.getProductCategory().name()))
				.mapToDouble(Product::totalAmount).sum();

		if (otherProductPrice == 0) {
			return 0;
		}
		InfluenceFactor factor = loadPrecentageFactor(customer);
		if (factor == null) {
			return otherProductPrice;
		}

		otherProductPrice = otherProductPrice - (otherProductPrice * (Double.valueOf(factor.getDiscountValue()) / 100));

		System.out.println("TOTAL OTHERS PRICE = [" + otherProductPrice + "]");

		return otherProductPrice;
	}

	private InfluenceFactor loadPrecentageFactor(Customer customer) {
		InfluenceFactor factor = DiscountUtil.<InfluenceFactor>applyFilterOnCondition(
				f -> customer.getSegment().equals(f.getCriteriaName()), influenceFactors);
		if (factor == null) {
			if (DiscountUtil.findDifference(customer.getRegisterDate(),
					new SimpleDateFormat("dd-MM-yyyy").format(new Date())) >= 2) {
				factor = DiscountUtil.<InfluenceFactor>applyFilterOnCondition(
						f -> "OLD_CUSTOMER".equals(f.getCriteriaName()), influenceFactors);
			}
		}
		return factor;
	}

}

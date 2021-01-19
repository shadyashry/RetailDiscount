package com.eshop.retail.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eshop.retail.dto.Basket;
import com.eshop.retail.service.RetailDiscountService;

@RestController
@RequestMapping("/manageOrder")
public class RetailProductsController {

	@Autowired
	RetailDiscountService service;

	@GetMapping("/generateBill")
	public Basket generateBill(@RequestBody Basket basket) {
		basket.setTotalBillAmount(service.getTotalNetProductsPrice(basket.getCustomer(), basket.getProducts()));
		return basket;
	}
}

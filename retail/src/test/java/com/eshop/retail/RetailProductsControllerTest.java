package com.eshop.retail;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.eshop.retail.dto.Basket;
import com.eshop.retail.dto.Customer;
import com.eshop.retail.dto.Product;
import com.eshop.retail.dto.ProductCategory;
import com.eshop.retail.service.RetailDiscountService;
public class RetailProductsControllerTest {

	
	@Test
	public void testEmployeeOtherProducts() {
		
		RetailDiscountService discountService =  new RetailDiscountService();
		Basket basket =  new Basket();
		Customer customer =  new Customer();
		customer.setName("Mohamed");
		customer.setRegisterDate("10-12-2018");
		customer.setSegment("EMPLOYEE");
		basket.setCustomer(customer);
		
		Product tv =  new Product();
		tv.setPrice("120");
		tv.setQuantity("2");
		tv.setSkuCode("SMK001");
		tv.setProductCategory(ProductCategory.OTHERS);
		List<Product> products = new ArrayList<Product>();
		products.add(tv);
		basket.setProducts(products);
		
		assertEquals(163, discountService.getTotalNetProductsPrice(customer, products));
	}
	
	
	@Test
	public void testAffiliateOtherProducts() {
		
		RetailDiscountService discountService =  new RetailDiscountService();
		Basket basket =  new Basket();
		Customer customer =  new Customer();
		customer.setName("Mohamed");
		customer.setRegisterDate("10-12-2018");
		customer.setSegment("AFFILIATE");
		basket.setCustomer(customer);
		
		Product tv =  new Product();
		tv.setPrice("120");
		tv.setQuantity("2");
		tv.setSkuCode("SMK001");
		tv.setProductCategory(ProductCategory.OTHERS);
		List<Product> products = new ArrayList<Product>();
		products.add(tv);
		basket.setProducts(products);
		
		assertEquals(206, discountService.getTotalNetProductsPrice(customer, products));
	}
	
	
	
	@Test
	public void testOldCustomerOtherProducts() {
		
		RetailDiscountService discountService =  new RetailDiscountService();
		Basket basket =  new Basket();
		Customer customer =  new Customer();
		customer.setName("Mohamed");
		customer.setRegisterDate("10-12-2018");
		customer.setSegment("NON");
		basket.setCustomer(customer);
		
		Product tv =  new Product();
		tv.setPrice("120");
		tv.setQuantity("2");
		tv.setSkuCode("SMK001");
		tv.setProductCategory(ProductCategory.OTHERS);
		List<Product> products = new ArrayList<Product>();
		products.add(tv);
		basket.setProducts(products);
		
		assertEquals(218, discountService.getTotalNetProductsPrice(customer, products));
	}
	
	
	@Test
	public void testCustomerGroceriesProducts() {
		RetailDiscountService discountService =  new RetailDiscountService();
		Basket basket =  new Basket();
		Customer customer =  new Customer();
		customer.setName("Mohamed");
		customer.setRegisterDate("10-12-2018");
		customer.setSegment("NON");
		basket.setCustomer(customer);
		
		Product tv =  new Product();
		tv.setPrice("120");
		tv.setQuantity("2");
		tv.setSkuCode("SMK001");
		tv.setProductCategory(ProductCategory.GROCERIES);
		List<Product> products = new ArrayList<Product>();
		products.add(tv);
		basket.setProducts(products);
		
		assertEquals(230, discountService.getTotalNetProductsPrice(customer, products));
	}
	
	
	
	
	@Test
	public void testCustomerGroceriesNoOverallBill() {
		RetailDiscountService discountService =  new RetailDiscountService();
		Basket basket =  new Basket();
		Customer customer =  new Customer();
		customer.setName("Mohamed");
		customer.setRegisterDate("10-12-2018");
		customer.setSegment("NON");
		basket.setCustomer(customer);
		
		Product tv =  new Product();
		tv.setPrice("90");
		tv.setQuantity("1");
		tv.setSkuCode("SMK001");
		tv.setProductCategory(ProductCategory.GROCERIES);
		List<Product> products = new ArrayList<Product>();
		products.add(tv);
		basket.setProducts(products);
		
		assertEquals(90, discountService.getTotalNetProductsPrice(customer, products));
	}
}

package com.stockMarket;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class AppTest{
	
	Stockdao b1=new Stockdao();
	@Disabled
	@Test
	public void testregister() throws Exception {
		Details c1=new Details();
		c1.cusid=5;
		c1.cusname="pravallika";
		c1.cuspin=2020;
		c1.cusStocks=50;
		b1.dbconnection();
		int exp=1;
		int res=b1.registercustomer(c1);
		assertEquals(exp,res);
	}
	
	
	@Disabled
	@Test
	public void testlogin() throws Exception {
		b1.dbconnection();
		int exp=1;
		int res=b1.login("xyz", 123);
		assertEquals(exp,res);
	}
	@Disabled
	@Test
	public void testdeposit() throws Exception {
		b1.dbconnection();
		int exp=40010;
		int res=b1.deposit(40010, 1);
		assertEquals(exp,res);
	}
	@Disabled
	@Test
	public void testwithdraw() throws Exception {
		b1.dbconnection();
		int exp=5;
		int res=b1.withdraw(10,1235,12);
		assertEquals(exp,res);
	}
	//test case to test changepassword method
	@Disabled
	@Test
	public void testchangepwd() throws Exception {
		b1.dbconnection();
		int exp=1;
		int res=b1.changepin(123,321,1);
		assertEquals(exp,res);
	}
	//test case to test delete account method
	
	@Test
	public void testdelaccount() throws Exception {
		b1.dbconnection();
		int exp=1;
		int res=b1.deleteAccount(321,1);
		assertEquals(exp,res);
	}
}
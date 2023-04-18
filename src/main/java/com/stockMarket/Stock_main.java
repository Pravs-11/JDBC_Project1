package com.stockMarket;
import java.util.*;
//import java.sql.*;

public class Stock_main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
Scanner bs=new Scanner(System.in);
Stockdao dao=new Stockdao();
Details c1=new Details();
System.out.println("\t\t\t******Welcome to online trading platform***");
System.out.println();

System.out.println("Press 1 for Registration \n Press 2 for login");
int operation=bs.nextInt();
switch(operation) {
case 1->{

System.out.println("Enter Customer name");
bs.nextLine();
String cname=bs.nextLine();
System.out.println("Enter customer id");
int cid=bs.nextInt();
System.out.println("Enter customer pin");
int cpin=bs.nextInt();
System.out.println("Enter customer stocks");
int cstocks=bs.nextInt();
c1.cusid=cid;
c1.cusname=cname;
c1.cuspin=cpin;
c1.cusStocks=cstocks;
dao.dbconnection();
int res =dao.registercustomer(c1);
if(res==1) {
	System.out.println("account registration successful");
}
else {
	System.out.println("Error, Not able to register");
}
}
case 2->{
	System.out.println("Welcome to login page");
	System.out.println("Enter username");
	bs.nextLine();
	String uname=bs.nextLine();
	System.out.println("Enter Pin");
	int pin=bs.nextInt();
	//get connection with data base
	dao.dbconnection();
	//login method
	int res=dao.login(uname, pin);
	//handling the response
	if(res==0) {
		System.out.println("username/password is incorrect");	
	}
	else if(res==-1) {
		System.out.println("Unable to find the customer details");	
		
	}
	else {
		System.out.println("Login successful");
		System.out.println("Press 1 to add stocks\nPress 2 to remove stocks\nPress 3 to change password\nPress 4 for Delete Acc");
		int process=bs.nextInt();
		switch(process) {
		case 1->{
			System.out.println("Enter stocks to add");
			int stocks=bs.nextInt();
			int bal=dao.deposit(stocks,res);
			System.out.println("added successful\n Available stocks count : "+bal);
		}
		case 2->{
			System.out.println("Enter stocks to remove");
			int stocks=bs.nextInt();
			System.out.println("Confirm your pin");
			int confmpin=bs.nextInt();
			int availstocks=dao.withdraw(stocks, confmpin, res);
			if(availstocks==-1) {
				System.out.println("Low stocks");
			}
			else if(availstocks==0) {
				System.out.println("Incorrect password");
			}
			else {
				System.out.println("stocks removed successfully \n Available stocks:"+availstocks);
			}
	}
case 3->{
	System.out.println("Enter current pin");
	int currentpin=bs.nextInt();
	System.out.println("Enter new pin");
	int newpin=bs.nextInt();
	
	int status=dao.changepin(currentpin, newpin, res);
	if(status==1) {
		System.out.println("Password changed...");
	}
	else {
		System.out.println("Something went wrong");
	}
	
}
case 4->{
	System.out.println("Enter pin to delete stock account of customer");
	int pass=bs.nextInt();
	int status=dao.deleteAccount(pass,res);
	if(status==1) {
		System.out.println("Your account is deleted successfully\n Thankyou!....");
	}
	else {
		System.out.println("Something went wrong");
	}
	
	
	
	
	
}


}




}

}
}
bs.close();



	}

}

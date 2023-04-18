package com.stockMarket;
//controller which is communicating with db
import java.sql.*;
public class Stockdao {
	Connection con=null;
	//method to get connection with Data base
	public void dbconnection() throws Exception{
		Class.forName("com.mysql.cj.jdbc.Driver");
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/trading", "root","Pravs@99");	
	}
//Method to register customer to data base
	public int registercustomer(Details c1)throws Exception {
		String query="insert into val values(?,?,?,?)";
		PreparedStatement pst=con.prepareStatement(query);
		pst.setInt(1, c1.cusid);
		pst.setString(2,c1.cusname);
		pst.setInt(3,c1.cuspin);
		pst.setInt(4,c1.cusStocks);
		int response=pst.executeUpdate();
	return response;
	}
	//method to login the customer
	public int login(String uname, int pin)throws Exception{
		String query="select * from val where cusname='"+uname+"'";
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery(query);
		//checking whether the customer details present or not
		if(rs.next()) {
			//fetching the password from db
			int cuspin=rs.getInt(3);
			
			//matching the password
			if(cuspin==pin) {
				//login success
				return rs.getInt(1);
			}
			else {
				//password incorrect
				return 0;
			}
		}
		else {
			//unable to fetch the details
			return -1;
		}
	
	}
	public int deposit(int stocks, int customerid)throws Exception{
		//fetching user details based on customer id
		String query2="select * from val where cusid="+customerid;
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery(query2);
		rs.next();
		//extracting  balance stocks
		int balstocks=rs.getInt(4);
		//updating stocks
		stocks+=balstocks;
		//storing updated stocks
		String query="update val set cusstocks="+stocks+" where cusid="+customerid;
		PreparedStatement pst=con.prepareStatement(query);
		pst.executeUpdate();
		//return updated stocks
		return stocks;
	}
	public int withdraw(int stocks,int pin,int cusid)throws Exception {
		//fetching user details based on customer id
				String 	query2="select  * from val where cusid="+cusid;
				
				Statement st=con.createStatement();
				
				ResultSet rs=st.executeQuery(query2);
				rs.next();
				//extracting available stocks
				int availstocks=rs.getInt(4);
				if(pin==rs.getInt(3))
				{
					if(stocks<availstocks) {
						availstocks-=stocks;
						String query="update val set cusstocks="+availstocks+" where cusid="+cusid;
						
						PreparedStatement pst=con.prepareStatement(query);
						pst.executeUpdate();
						return availstocks;		
					}
					else {
						return -1;
					}
				}
				else {
					return 0;
				}
	}
	
	
	public int changepin(int currentpin,int newpin, int cusid)throws Exception{
		//fetching user details
		String 	query2="select  * from val where cusid="+cusid;
		
		Statement st=con.createStatement();
		
		ResultSet rs=st.executeQuery(query2);
		rs.next();
		//confirming existing password
		if(currentpin==rs.getInt(3)) {
			//update new pin in db
			String query="update val set cuspin="+newpin+" where cusid="+cusid;
			
				PreparedStatement pst=con.prepareStatement(query);
				pst.executeUpdate();
				return 1;
		}
		else {
			return 0;
		}
	}
	
	public int deleteAccount(int pin, int cusid)throws Exception{
		//fetching user details
		String 	query2="select  * from val where cusid="+cusid;
		
		Statement st=con.createStatement();
		
		ResultSet rs=st.executeQuery(query2);
		rs.next();
		//confirming pin
		if(pin==rs.getInt(3)) {
		
			//delete the account
			String query="delete from val where cusid="+cusid;
			
			PreparedStatement pst=con.prepareStatement(query);
				pst.executeUpdate();
			return 1;
		}
		else {
			return 0;
		}
	}
	

}

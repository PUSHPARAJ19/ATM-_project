package com.atm;

import java.io.*;
import java.util.*;

public class UpdateCustomer {
	
	public void addCustomer() throws IOException
	{
		CustomerInfo cus1 = new CustomerInfo(101,"Suresh",2343,25234);
		CustomerInfo cus2 = new CustomerInfo(102,"Ganesh",5432,34123);
		CustomerInfo cus3 = new CustomerInfo(103,"Magesh",7854,26100);
		CustomerInfo cus4 = new CustomerInfo(104,"Naresh",2345,80000);
		CustomerInfo cus5 = new CustomerInfo(105,"Harish",1907,103400);
		FileOutputStream fout = new FileOutputStream("customer");
		ObjectOutputStream ot = new ObjectOutputStream(fout);
		ArrayList<CustomerInfo> alcus = new ArrayList<CustomerInfo>();
		alcus.add(cus1);
		alcus.add(cus2);
		alcus.add(cus3);
		alcus.add(cus4);
		alcus.add(cus5);
		ot.writeObject(alcus);
		fout.close();
		ot.close();
		
	}
	
	public static void diplayCustomer() throws ClassNotFoundException, IOException
	{
		ArrayList<CustomerInfo> li = null;
		FileInputStream fin = new FileInputStream("customer");
		
		try {
			ObjectInputStream oin=new ObjectInputStream(fin);
			li = (ArrayList<CustomerInfo>) oin.readObject();
			oin.close();
		} catch (Exception e) {
			
		}
		fin.close();
		if(li==null)
			li = new ArrayList<>();
		  System.out.println("+-------+-------------+-------+--------+");
		  System.out.println("| Accno | Acc.holder  |   pin | accbsl |");
		  System.out.println("+-------+-------------+-------+--------+");
		  for(int i=0;i<li.size();i++)
		  {
			CustomerInfo cob = li.get(i);
			System.out.format( "| %5s | %11s | %5s | %6s |\n", cob.getAccNum(), cob.getAccHolder(), cob.getPinNum(), cob.getAccBalance());
          }
		  System.out.println("+-------+-------------+-------+--------+");
		
	}
	
//  public static void main(String[] args) throws IOException, ClassNotFoundException {
//	
//	  UpdateCustomer uc =  new UpdateCustomer();
//	  uc.addCustomer();
//	  diplayCustomer();
//	  
//	  
//}
	
	
}

package com.atm;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ATMProcess {

	 public static CustomerInfo validate(int accNum,int pinNum) throws IOException, ClassNotFoundException
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
			int flag=0;
			for(int i=0;i<li.size();i++)
			{
				CustomerInfo cus = li.get(i);
				if(cus.getAccNum()==accNum ) 
				{   
                     if(cus.getPinNum()==pinNum)
                    	  return cus;
                     else
                     {
                    	 System.err.println ("        Invalid pin!     \nEnter a valid pin");
                    	 return null;
                     }
				}
				else
					flag=1;
			}	
				if(flag==1)
				{
					System.out.println("Enter a valid account Number");
					return null;
				}
					
			
		 return null;
	 }
	 
	 public static void checkBalance(CustomerInfo cusObj) throws IOException, ClassNotFoundException
	 {
			System.out.println("+-------------------------------------------+");
			System.out.println("| Acc No | Account Holder | Account Balance |");
			System.out.println("+-------------------------------------------+");
			System.out.println("|  "+cusObj.getAccNum()+"      "+ cusObj.getAccHolder()+"          "+cusObj.getAccBalance());
			System.out.println("+-------------------------------------------+");
	 }
	 public static void get_cash(int amount,CustomerInfo cus,Cash cs) throws IOException
	 {
		 int available_2knote = cs.getNote2000(),avai_5note = cs.getNote500(),ava_1note = cs.getNote100();
		int de_amount = amount,count_2k=0,count_5=0,count_1=0;
		 
		if(amount<=cs.getNote2000()*2000)
		  {       
			  while(amount>2000 && available_2knote*2000>=amount)
			  { 
				 count_2k++;
				 amount-=2000;
				 available_2knote--;
			  }
		  }
		  if(amount<=cs.getNote500()*500)
		  {
			  while(amount>500 && avai_5note*500>=amount)
			  { 
				 count_5++;
				 amount-=500;
				 avai_5note--;
			  }
		  }
		  if(amount<=cs.getNote100()*100)
		  {
			  while(amount>=100 && ava_1note*100>=amount)
			  { 
				 count_1++;
				 amount-=100;
				 ava_1note--;
			  }
		  } 
			  
			  if(amount==0)
			  {
				  System.out.println("--- Amount Withdrawn Successfully---");
				  System.out.println("+---------+-------------+-------+");
				  System.out.println("| Denominations | Count | Value|");
				  System.out.println("+---------+-------------+-------+");
				  System.out.format( "| Rs.2000       |  %2s  | %4s   |\n",count_2k,count_2k*2000);
				  System.out.format( "| Rs.500        |  %2s  | %4s   |\n",count_5,count_5*500);
				  System.out.format( "| Rs.100        |  %2s  | %4s   |\n",count_1,count_1*100);
				  System.out.println("+---------------+-------+-------+");
				  cs.setNote500(avai_5note);
				  cs.setNote100(ava_1note);
				  cs.setNote2000(available_2knote);
				  FileOutputStream fout = new FileOutputStream("cash");
				  ObjectOutputStream out = new ObjectOutputStream(fout);
				  out.writeObject(cs);
				  out.close();
				  //retireving list
				  ArrayList<CustomerInfo> li = null;
					FileInputStream fin = new FileInputStream("customer");
					try {
						ObjectInputStream oin=new ObjectInputStream(fin);
						li = (ArrayList<CustomerInfo>) oin.readObject();
						oin.close();
					} catch (Exception e) {
						
					}
					fin.close();
					for(int i=0;i<li.size();i++)
					{
					  if(li.get(i).getAccNum()==cus.getAccNum())
					  {
						  li.get(i).setAccBalance(li.get(i).getAccBalance()-de_amount);
					  }
					}
				  
				  FileOutputStream fot = new FileOutputStream("customer");
				  ObjectOutputStream ot = new ObjectOutputStream(fot);
				  ot.writeObject(li);
				  ot.close();
				  fot.close();
				  
			  }
			  else
			  {
				  System.out.println("Insufficent Denominations");
			  }
			  
		  
		
	 }
	 public static void withdrawMoney(CustomerInfo cusObj) throws IOException, ClassNotFoundException
	 {
		 Scanner sc = new Scanner(System.in);
		 FileInputStream fin = new FileInputStream("cash");
			ObjectInputStream oin = new ObjectInputStream(fin);
			Cash cs = (Cash)oin.readObject();
		 System.out.println("Enter the Amount to Withdraw:");
		 int amount= sc.nextInt();
		 if(amount<=10000 && amount>=100)
		 {
			  if(amount<=AddCash.atmBalance())
			  {
				  if(amount<=cusObj.getAccBalance())
				  {
					  get_cash(amount,cusObj,cs);
				  }
				  else
				  {
					  System.out.println(" **** Insufficent Account Balance ****");
				  }
			  }
			  else
			  {
				  System.out.println("*** Insufficent Funds in ATM ***");
			  }
		 }
		 else
		 {
			 System.err.println("Withdrawal Limit exceeded");
		 }
			
			
		
	 }
	 
	 public static void transferMoney(CustomerInfo sen,int receiver_acc) throws IOException, ClassNotFoundException
	 {
		 Scanner sc = new Scanner(System.in);
		 ArrayList<CustomerInfo> li = null;
			FileInputStream fin = new FileInputStream("customer");
			try {
				ObjectInputStream oin=new ObjectInputStream(fin);
				li = (ArrayList<CustomerInfo>) oin.readObject();
				oin.close();
			} catch (Exception e) {
				
			}
			fin.close();

			CustomerInfo receiver=null;
			for(int i=0;i<li.size();i++)
			{
				CustomerInfo cus = li.get(i);
				if(cus.getAccNum()==receiver_acc) {
					receiver = cus;
				}
			}
			CustomerInfo sender = sen;
			if(receiver==null)
			{
				System.out.println("Invalid account Number");
			}
			else
			{
				System.out.println("Enter the amount to transfer");
				int transferAmount = sc.nextInt();
				if(transferAmount<=10000 && transferAmount>=1000)
				{
					if(transferAmount<=sender.getAccBalance())
					{
						for(int i1=0;i1<li.size();i1++)
						{
							if(li.get(i1).getAccNum() == sender.getAccNum())
							{
								li.get(i1).setAccBalance(sender.getAccBalance()-transferAmount);
							}
							if(li.get(i1).getAccNum()==receiver_acc)
							{
								li.get(i1).setAccBalance(li.get(i1).getAccBalance()+transferAmount);
							}
						}
						
						System.out.println("Rs."+transferAmount+" /- has succesfully transfered ");
					}
					else
					{
						System.out.println("Insufficient funds");
					}
				}
				else
				{
					System.out.println("Transaction limit exceeded");
				}
				
				
			}
			FileOutputStream fout = new FileOutputStream("customer");
			ObjectOutputStream out = new ObjectOutputStream(fout);
			out.writeObject(li);
			out.close();
			fout.close();
		
	 }
	 
//	public static void main(String[] args) throws ClassNotFoundException, IOException {
//		
//		//checkBalance(102,5432);
//		//transferMoney(102,5432,104);
//		 CustomerInfo cus = validate(102,5432);
//		 FileInputStream fin = new FileInputStream("cash");
//		 ObjectInputStream oin = new ObjectInputStream(fin);
//		 Cash cs = (Cash)oin.readObject();
//			//System.out.println(cs.getNote2000());
//		 Scanner scc = new Scanner(System.in);
//		 int amount_with = scc.nextInt();
//         get_cash(amount_with,cus,cs);
//          //System.out.println(cs.getNote2000());
//	}
	
}

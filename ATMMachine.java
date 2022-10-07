package com.atm;

import java.io.IOException;
import java.util.Scanner;

public class ATMMachine {
	
	  public static void main(String[] args) throws ClassNotFoundException, IOException {
		Scanner sc = new Scanner(System.in);
		System.out.println("    -----------  ATM  -----------\n");
		int flag=1;
		while(flag==1)
		{
			int accNum,pinNum;
			System.out.println("+-----------------------------+");
			System.out.println("|      -----MENU-----         |");
			System.out.println("|     1.Check Balance         |");
			System.out.println("|     2.Withdraw Money        |");
			System.out.println("|     3.Transfer Money        |");
			System.out.println("|     4.Check ATM Balance     |");
			System.out.println("|     5.Display All Customer  |");
			System.out.println("|     6.Exit                  |");
			System.out.println("+-----------------------------+");
			System.out.println("Enter Your choice:  ");
			int choice = sc.nextInt();
			switch(choice)
			{
			case 1:
			{
				System.out.println("Enter Your Account Number and Pin Number");
				accNum=sc.nextInt();
				pinNum = sc.nextInt();
				CustomerInfo ci = ATMProcess.validate(accNum, pinNum);
				if(ci!=null)
				ATMProcess.checkBalance(ci);
				else
					System.out.println("Enter Valid Account Details");
				break;
			}
			case 2:
			{
				System.out.println("Enter Your Account Number and Pin Number");
				accNum=sc.nextInt();
				pinNum = sc.nextInt();
				CustomerInfo ci = ATMProcess.validate(accNum, pinNum);
				if(ci!=null)
				{
					 ATMProcess.withdrawMoney(ci);
				}
				else
					System.out.println("Enter Valid Account Details");
				break;
			}
			case 3:
			{
				System.out.println("Enter Your Account Number and Pin Number");
				accNum=sc.nextInt();
				pinNum = sc.nextInt();
				CustomerInfo customerObj = ATMProcess.validate(accNum, pinNum);
				if(customerObj!=null) {
				System.out.println("Enter the Account number to Transfer Money");
				int transferAccNum = sc.nextInt();
				ATMProcess.transferMoney(customerObj, transferAccNum);
				}
				else
				{
					System.out.println("Enter a Valid Account Details");
				}
				break;
				
			}
			case 4:
			{
				AddCash.displayAtmBalance();
				break;
			}
			case 5:
			{
				UpdateCustomer.diplayCustomer();
				break;
			}
				
			case 6:
			{
				flag=0;
				break;
			}
			default:
				System.out.println("Enter a Valid Choice");
			}
			
		}
		if(flag==0)
		{
			System.out.println("       Thank You For Using Our ATM     ");
			System.out.println("--------------Visit Again-------------");
		}
		
		  
		  
		  
	}

}

package com.atm;

import java.io.*;

public class AddCash {
	
	public static int atmBalance() throws IOException, ClassNotFoundException
	{
		FileInputStream fin = new FileInputStream("cash");
		ObjectInputStream oin = new ObjectInputStream(fin);
		int sum=0;
		Cash cs = (Cash)oin.readObject();
		sum = cs.getNote100()*100 + cs.getNote500()*500 +cs.getNote2000()*2000;
		return sum;
	}
	public static void FeedCash(int note2000,int note500,int note100) throws ClassNotFoundException, IOException
	{
		Cash cs = null;
		FileInputStream fin = new FileInputStream("cash");
		try {
			ObjectInputStream oin = new ObjectInputStream(fin);
			cs =(Cash)oin.readObject();
		} catch (IOException e) {
			
		}
		fin.close();
		if(cs==null)
		{
			cs = new Cash();
		}
		
		cs.setNote2000(cs.getNote2000()+note2000);
		cs.setNote500(cs.getNote500()+note500);
		cs.setNote100(cs.getNote100()+note100);
		FileOutputStream fout = new FileOutputStream("cash");
		ObjectOutputStream ot = new ObjectOutputStream(fout);
		ot.writeObject(cs);
		fout.close();
		
	}
	public static void displayAtmBalance() throws IOException, ClassNotFoundException
	{
       FileInputStream fin = new FileInputStream("cash");
       ObjectInputStream oin = new ObjectInputStream(fin);
		Cash cs =(Cash)oin.readObject();
		System.out.println("---------------|-------------------");
		System.out.println("Denomination   |   Number     Value");
		System.out.println("---------------|-------------------");
		System.out.println("2000"+"           |     "+cs.getNote2000()+"         "+cs.getNote2000()*2000);
		System.out.println("---------------|-------------------");
		System.out.println("500"+"            |     "+cs.getNote500()+"          "+cs.getNote500()*500);
		System.out.println("---------------|-------------------");
		System.out.println("100"+"            |     "+cs.getNote100()+"          "+cs.getNote100()*100);
		System.out.println("----------------------------------");
		
	}
//	public static void main(String[] args) throws ClassNotFoundException, IOException {
//		
//	AddCash.FeedCash(20,100, 100);
//	AddCash.displayAtmBalance();
//	}
//	
}

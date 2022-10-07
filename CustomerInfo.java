package com.atm;

import java.io.Serializable;

public class CustomerInfo  implements Serializable {
	private int accNum;
	private String accHolder;
	private int pinNum;
	private long accBalance;
	public CustomerInfo(int accNum, String accHolder, int pinNum, long accBalance) {
		this.accNum = accNum;
		this.accHolder = accHolder;
		this.pinNum = pinNum;
		this.accBalance = accBalance;
	}
	public int getAccNum() {
		return accNum;
	}
	public void setAccNum(int accNum) {
		this.accNum = accNum;
	}
	public String getAccHolder() {
		return accHolder;
	}
	public void setAccHolder(String accHolder) {
		this.accHolder = accHolder;
	}
	public int getPinNum() {
		return pinNum;
	}
	public void setPinNum(int pinNum) {
		this.pinNum = pinNum;
	}
	public long getAccBalance() {
		return accBalance;
	}
	public void setAccBalance(long accBalance) {
		this.accBalance = accBalance;
	}
}

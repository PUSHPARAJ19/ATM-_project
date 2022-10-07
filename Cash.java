package com.atm;

import java.io.Serializable;

public class Cash implements Serializable{
	private int note2000;
	private int note500;
	private int note100;
	public Cash()
	{
		
	}
	public int getNote2000() {
		return note2000;
	}
	public void setNote2000(int note2000) {
		this.note2000 = note2000;
	}
	public int getNote500() {
		return note500;
	}
	public void setNote500(int note500) {
		this.note500 = note500;
	}
	public int getNote100() {
		return note100;
	}
	public void setNote100(int note100) {
		this.note100 = note100;
	}
}

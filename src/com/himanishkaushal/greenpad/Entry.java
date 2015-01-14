package com.himanishkaushal.greenpad;

public class Entry {
	
	private int id;
	private int year, month, day;
	private String payee;
	private double payment;
	
	public Entry(int year, int month, int day, String payee, double payment) {
		this.year = year;
		this.month = month;
		this.day = day;
		this.payee = payee;
		this.payment = payment;
	}

	public int getId() {
		return id;
	}
	
	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public String getPayee() {
		return payee;
	}

	public void setPayee(String payee) {
		this.payee = payee;
	}

	public double getPayment() {
		return payment;
	}

	public void setPayment(double payment) {
		this.payment = payment;
	}

}

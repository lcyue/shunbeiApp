package com.example.administrator.instantaneousbeiapp.view;

public class sqlit {
	String dates;
	String isselct;

	public sqlit() {
	}

	public sqlit(String date, String isselct) {
		this.dates = date;
		this.isselct = isselct;
	}

	public String getDate() {
		return dates;
	}

	public void setDate(String date) {
		this.dates = date;
	}

	public String getIsselct() {
		return isselct;
	}

	public void setIsselct(String isselct) {
		this.isselct = isselct;
	}

}

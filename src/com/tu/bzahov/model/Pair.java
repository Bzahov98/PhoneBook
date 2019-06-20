package com.tu.bzahov.model;

public class Pair implements Comparable<Pair> {
	private Phone phone;
	private String name;
	private int inCallsCount;
	private int outCallsCount;

	public Pair(Phone phone, String name) {
		this.phone = phone;
		this.name = name;
		this.inCallsCount = 0;
		this.outCallsCount = 0;
	}


	public int increaseInCalls(){
		return ++inCallsCount;
	}
	public int increaseOutCalls(){
		return ++outCallsCount;
	}

	public int getInCallsCount() {
		return inCallsCount;
	}

	public int getOutCallsCount() {
		return outCallsCount;
	}


	public Phone getPhone() {
		return phone;
	}

	public void setPhone(Phone phone) {
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int compareTo(Pair o) {
		return this.getName().compareTo(o.getName());
	}
}

package com.tu.bzahov.model;

import java.util.ArrayList;
import java.util.List;

public class CountryInfo {
	public static final String BG_NUMBER_CODE = "+359";
	public String validRegexBG = "(\\+359|00359|0)(87|88|89)";
	private String countryCode;
	@Deprecated
	private List<String> operatorsList = new ArrayList<>();

	public CountryInfo() { // Default
		this.countryCode = BG_NUMBER_CODE;
		setDefaultOperatorsList();
	}

	public CountryInfo(String countryCode, List<String> operatorsList) {
		this.countryCode = countryCode;
		this.operatorsList = operatorsList;
	}

	@Deprecated // TODO make for all countries codes
	private void setDefaultOperatorsList() {
		operatorsList.add("87");
		operatorsList.add("88");
		operatorsList.add("89");
	}

	private String createCountryRegexForPhone(){
		//TODO
		return "";
	}
	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public List<String> getOperatorsList() {
		return operatorsList;
	}

	public void setOperatorsList(List<String> operatorsList) {
		this.operatorsList = operatorsList;
	}
}

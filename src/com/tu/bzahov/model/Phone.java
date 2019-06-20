package com.tu.bzahov.model;

import com.tu.bzahov.exceptions.InvalidPhoneException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Phone {
	private String phone;
	private CountryInfo countryInfo;
	private static String isPhoneValidRegexBG = "^(\\+359|00359|0)(87|88|89)[2-9][0-9]{6}";

	public Phone(String phone) throws InvalidPhoneException { // with Default Country
		setPhoneStr(phone);
		setCountryInfo(new CountryInfo());
	}

	public Phone(String phone, CountryInfo countryInfo) throws InvalidPhoneException {
		setPhoneStr(phone);
		setCountryInfo(countryInfo);
	}

	public String getPhoneStr() {
		return phone;
	}

	public void setPhoneStr(String phone) throws InvalidPhoneException {
		if (isValidPhone(phone/*, countryInfo*/)) {
			this.phone = phone;
		} else {
			System.err.println(InvalidPhoneException.INVALID_PHONE + " for: " + phone);
			throw new InvalidPhoneException();
		}
	}

	public boolean isValid() {
		return isValidPhone(this.phone);//, this.getCountryInfo());
	}

	public static boolean isValidPhone(String phone){/*, CountryInfo countryInfo) {*/
		// TODO:
		Pattern p = Pattern.compile(isPhoneValidRegexBG);
		Matcher m = p.matcher(phone);
		return m.matches();
	}

	public CountryInfo getCountryInfo() {
		return countryInfo;
	}

	public void setCountryInfo(CountryInfo countryInfo) {
		this.countryInfo = countryInfo;
	}

	@Override
	public String toString() {
		return getPhoneStr();
	}
}

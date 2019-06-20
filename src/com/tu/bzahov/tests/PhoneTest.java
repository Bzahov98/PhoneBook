package com.tu.bzahov.tests;

import com.tu.bzahov.model.Phone;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PhoneTest {

	@BeforeEach
	void setUp() {
	}

	// Only for BG phones now
	@Test
	void isValidPhonePositive() {
		assertTrue(Phone.isValidPhone("0889302288"));
		assertTrue(Phone.isValidPhone("+359889302288"));
		assertTrue(Phone.isValidPhone("00359889302288"));
	}
	@Test
	void isValidPhoneNegative() {
		assertFalse(Phone.isValidPhone("0809302288"));
		assertFalse(Phone.isValidPhone("+3598890302288"));
		assertFalse(Phone.isValidPhone("01359889302288"));
	}
}
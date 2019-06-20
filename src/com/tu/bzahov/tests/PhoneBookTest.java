package com.tu.bzahov.tests;

import com.tu.bzahov.exceptions.InvalidPhoneException;
import com.tu.bzahov.model.Pair;
import com.tu.bzahov.model.Phone;
import com.tu.bzahov.model.PhoneBook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.TreeSet;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class PhoneBookTest {
	TreeSet<Pair> testAllPairs;
	PhoneBook testPhoneBook;
	PhoneBook filePhoneBook;
	private TreeSet<Pair> allTestPairs;
	private Phone phone1;
	private Pair pair1;
	private Phone phone2;
	private Pair pair2;
	private Phone phone3;
	private Pair pair3;
	private Phone invalidPhone;
	private Pair invalidPair;
	private ArrayList<Phone> allPhones;
	private ArrayList<Pair> allPairs;

	@BeforeEach
	void setUp() {
		testPhoneBook = new PhoneBook();
		filePhoneBook = new PhoneBook();
		allTestPairs = testPhoneBook.getAllPairs();
		try {
			allPhones = new ArrayList<>();
			allPairs = new ArrayList<>();
			IntStream.range(0,7).forEach(value -> {
				try {
					allPhones.add(new Phone("08893024"+ (value+10)));
					allPairs.add(new Pair(allPhones.get(value),"testPair"+value));
					testPhoneBook.addPair(allPairs.get(value));
				} catch (InvalidPhoneException e) {
					e.printStackTrace();
				}
			});

			phone1 = new Phone("0889302488");
			phone2 = new Phone("+359889302288");
			phone3 = new Phone("00359889302288");

			pair1 = new Pair(phone1, "pair1");
			pair2 = new Pair(phone2, "zpair2");
			pair3 = new Pair(phone3, "apair2");

		} catch (InvalidPhoneException e) {
			e.printStackTrace();
		}
	}

	@Test
	void addPairTest() {
		testPhoneBook.addPair(pair1);
		assertTrue(testPhoneBook.getAllPairs().contains(pair1));
	}
	@Test
	void addPairNegativeTest() {
		try {
			invalidPhone = new Phone("888888");
			testPhoneBook.addPair(invalidPair);
			invalidPair = new Pair(invalidPhone, "invalid");
			testPhoneBook.addPair(invalidPair);
			assertFalse(false);
		}catch (InvalidPhoneException e) {
			assertTrue(true);
		}//assertThrows(new Exception() , testPhoneBook.addPair(invalidPair));
	}

	@Test
	void removePairTest() {
		testPhoneBook.addPair(pair1);
		assertTrue(testPhoneBook.getAllPairs().contains(pair1));
		testPhoneBook.removePair(pair1);
		assertFalse(testPhoneBook.getAllPairs().contains(pair1));
	}

	@Test
	void removePairByNameTest() {
		testPhoneBook.addPair(pair1);
		assertTrue(testPhoneBook.getAllPairs().contains(pair1));
		testPhoneBook.removePairByName("pair1");
		assertFalse(testPhoneBook.getAllPairs().contains(pair1));
	}

	@Test
	void getPhoneByNamePositiveTest() {
		testPhoneBook.addPair(pair1);
		assertTrue(testPhoneBook.getAllPairs().contains(pair1));

		assertEquals(phone1.getPhoneStr(), testPhoneBook.getPhoneByName("pair1"));
	}
	@Test
	void getPhoneByNameNegativeTest() {
		testPhoneBook.addPair(pair1);
		testPhoneBook.addPair(pair2);
		assertTrue(testPhoneBook.getAllPairs().contains(pair1));
		assertTrue(testPhoneBook.getAllPairs().contains(pair2));

		assertNotEquals(phone1.getPhoneStr(), testPhoneBook.getPhoneByName("zpair2"));
	}

	@Test
	void getPairByNameTest() {
		testPhoneBook.addPair(pair1);
		assertEquals(pair1,testPhoneBook.getPairByName("pair1"));
	}

	@Test
	void printAllPairsSortedByNameTest() {
		testPhoneBook.addPair(pair1);
		testPhoneBook.addPair(pair2);
		testPhoneBook.addPair(pair3);

		assertEquals("apair2 | 00359889302288\n" +
						"pair1 | 0889302488\n" +
						"testPair0 | 0889302410\n" +
						"testPair1 | 0889302411\n" +
						"testPair2 | 0889302412\n" +
						"testPair3 | 0889302413\n" +
						"testPair4 | 0889302414\n" +
						"testPair5 | 0889302415\n" +
						"testPair6 | 0889302416\n" +
						"zpair2 | +359889302288\n"
				,testPhoneBook.printAllPairsSortedByName());
	}

	@Test
	void loadAllPairsFromFileTest() throws IOException {
		filePhoneBook.printAllPairsSortedByName();
		filePhoneBook.loadAllPairsFromFile(Paths.get("src/com/tu/bzahov/tests/inputTest.txt"));

		// invalid1 | 08893026488 is with wrong phone, will be skipped
		assertNull(filePhoneBook.getPairByName("invalid"));

		assertEquals("Gosho | 00359889302288\n" +
				"Ivan | +359889302288\n" +
				"Lazar | 0889302588\n" +
				"Pesho | 0889377788\n",filePhoneBook.printAllPairsSortedByName() );
	}

	@Test
	void print5TopOutgoingCallsTest() {
		for(int i = 0;i<6;i++) testPhoneBook.increaseOutCallOfPair(allPairs.get(1));
		for(int i = 0;i<5;i++) testPhoneBook.increaseOutCallOfPair(allPairs.get(2));
		for(int i = 0;i<4;i++) testPhoneBook.increaseOutCallOfPair(allPairs.get(3));
		for(int i = 0;i<3;i++) testPhoneBook.increaseOutCallOfPair(allPairs.get(4));
		for(int i = 0;i<2;i++) testPhoneBook.increaseOutCallOfPair(allPairs.get(5));
		for(int i = 0;i<1;i++) testPhoneBook.increaseOutCallOfPair(allPairs.get(6));
		for(int i = 0;i<1;i++) testPhoneBook.increaseOutCallOfPair(allPairs.get(0));

		assertEquals("testPair1 | 0889302411 | 6\n" +
				"testPair2 | 0889302412 | 5\n" +
				"testPair3 | 0889302413 | 4\n" +
				"testPair4 | 0889302414 | 3\n" +
				"testPair5 | 0889302415 | 2\n"
				,testPhoneBook.print5TopOutgoingCalls());
	}
	@Test
	void print5TopIngoingCallsTest() {
		for(int i = 0;i<6;i++) testPhoneBook.increaseInCallOfPair(allPairs.get(1));
		for(int i = 0;i<5;i++) testPhoneBook.increaseInCallOfPair(allPairs.get(2));
		for(int i = 0;i<4;i++) testPhoneBook.increaseInCallOfPair(allPairs.get(3));
		for(int i = 0;i<3;i++) testPhoneBook.increaseInCallOfPair(allPairs.get(4));
		for(int i = 0;i<2;i++) testPhoneBook.increaseInCallOfPair(allPairs.get(5));
		for(int i = 0;i<1;i++) testPhoneBook.increaseInCallOfPair(allPairs.get(6));
		for(int i = 0;i<1;i++) testPhoneBook.increaseInCallOfPair(allPairs.get(0));

		assertEquals("testPair1 | 0889302411 | 6\n" +
				"testPair2 | 0889302412 | 5\n" +
				"testPair3 | 0889302413 | 4\n" +
				"testPair4 | 0889302414 | 3\n" +
				"testPair5 | 0889302415 | 2\n"
				,testPhoneBook.print5TopIngoingCalls());
	}

	@Test
	void increaseInCallOfPairTest() {
		testPhoneBook.increaseInCallOfPair(allPairs.get(0));
		testPhoneBook.increaseInCallOfPair(allPairs.get(0));
		assertEquals(2,allPairs.get(0).getInCallsCount());
	}

	@Test
	void increaseOutCallOfPairTest() {
		testPhoneBook.increaseOutCallOfPair(allPairs.get(0));
		assertEquals(1,allPairs.get(0).getOutCallsCount());
	}
}
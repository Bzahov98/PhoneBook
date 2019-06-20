package com.tu.bzahov.interfaces;

import com.tu.bzahov.model.Pair;

import java.io.IOException;
import java.nio.file.Path;

public interface PhoneBookOperations {
	boolean addPair(Pair pair);
	boolean removePair(Pair pair);
	boolean removePairByName(String name);
	Pair getPairByName(String name);
	int increaseInCallOfPair(Pair pair);
	int increaseOutCallOfPair(Pair pair);
	String getPhoneByName(String name);
	String print5TopOutgoingCalls();
	String printAllPairsSortedByName();
	void loadAllPairsFromFile(Path path) throws IOException;
}

package com.tu.bzahov.model;

import com.tu.bzahov.exceptions.InvalidPhoneException;
import com.tu.bzahov.interfaces.PhoneBookOperations;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PhoneBook implements PhoneBookOperations {
	public static final String PAIR_SEPARATOR = " | ";
	private TreeSet<Pair> allPairs;

	public PhoneBook() {
		setAllPairs(null);
	}

	public boolean addPair(Pair pair) {
		if (!allPairs.contains(pair)) {
			allPairs.add(pair);
			System.out.println("New pair was added: " + pair.getPhone() + " " + pair.getName());
			return true;
		}
		System.err.println("ERROR: Pair Already in PhoneBook");
		return false;
	}

	public boolean removePair(Pair pair) {
		if (allPairs.contains(pair)) {
			allPairs.remove(pair);
			return true;
		}
		System.err.println("ERROR: Pair not in PhoneBook");
		return false;
	}

	public boolean removePairByName(String name) {
		Predicate<Pair> removeByNamePredicate = pair -> Objects.equals(name, pair.getName());
		boolean isSuccessful = allPairs.removeIf(removeByNamePredicate);

		if (isSuccessful) {
			System.out.println("Remove of " + name + " was successful");
		} else System.err.println("ERROR: Remove of " + name + " was not successful!!");

		return isSuccessful;
	}

	public String getPhoneByName(String name) {
		Predicate<Pair> findPairByName = pair -> Objects.equals(name, pair.getName());
		Optional<Pair> pair = getPairByPredicate(findPairByName);
		if (pair.isPresent()) {
			return pair.get().getPhone().getPhoneStr();
		} else {
			System.err.println("ERROR: Pair Name not in PhoneBook!");
			return null;
		}
	}

	@Override
	public String print5TopOutgoingCalls() {

		List<Pair> top5 = allPairs.stream().sorted(Comparator.comparing(Pair::getOutCallsCount).reversed()).limit(5).collect(Collectors.toList());
		StringBuilder sb = new StringBuilder();
		top5.forEach(p -> sb
				.append(p.getName())
				.append(PAIR_SEPARATOR)
				.append(p.getPhone())
				.append(PAIR_SEPARATOR)
				.append(p.getOutCallsCount())
				.append("\n"));

		System.out.println(sb);
		return sb.toString();
	}

	public Pair getPairByName(String name) {
		Predicate<Pair> findPairByName = pair -> Objects.equals(name, pair.getName());
		Optional<Pair> any = getPairByPredicate(findPairByName);
		if (any.isPresent()) {
			return any.get();
		} else {
			System.err.println("ERROR: Pair Name not in PhoneBook!");
			return null;
		}
	}

	public String printAllPairsSortedByName() {
		StringBuilder sb = new StringBuilder();
		allPairs.forEach(pair -> {
			sb.append(pair.getName())
					.append(PAIR_SEPARATOR)
					.append(pair.getPhone())
					.append("\n");
		});
		System.out.println(sb.toString());
		return sb.toString();
	}

	public void loadAllPairsFromFile(Path path) throws IOException {
		boolean isExists = Files.exists(path);
		if (isExists) {
			Files.readAllLines(path).forEach(line ->
			{
				String[] split = line.split(PAIR_SEPARATOR);
				String name = split[0];
				String phone = split[2];
				try {
					Pair newPair = new Pair(new Phone(phone), name);
					addPair(newPair);
				} catch (InvalidPhoneException e) {
					System.err.println("Cant fetch record with phone: " + phone + " &name: " + name);
				}
			});
		} else System.err.println("File does not exists in: " + path.getFileName());
	}

	@Override
	public int increaseInCallOfPair(Pair pair) {
		return pair.increaseInCalls();
	}

	@Override
	public int increaseOutCallOfPair(Pair pair) {
		return pair.increaseOutCalls();
	}

	/*public String printAllPairsSortedBy(Predicate<Pair> predicate){
		// TODO
	}*/

	private Optional<Pair> getPairByPredicate(Predicate<Pair> predicate) {
		// allPairs.ceiling(predicate); or that if we use TreeSet
		return allPairs.stream().filter(predicate).findAny();
	}

	public TreeSet<Pair> getAllPairs() {
		return allPairs;
	}

	public void setAllPairs(TreeSet<Pair> allPairs) {
		if (allPairs == null) {
			this.allPairs = new TreeSet<>();
		} else
			this.allPairs = allPairs;
	}
}

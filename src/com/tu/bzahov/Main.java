package com.tu.bzahov;

import com.tu.bzahov.exceptions.InvalidPhoneException;
import com.tu.bzahov.model.Pair;
import com.tu.bzahov.model.Phone;
import com.tu.bzahov.model.PhoneBook;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {

    public static void main(final String[] args) {
        Scanner in = new Scanner(System.in);
        PhoneBook phoneBook = new PhoneBook();
        boolean exit = false;
        String name;
        String phoneStr;

        while (!exit){
            System.out.println("?>----------------------------------------------");
            System.out.printf("%s> %s","1","For read from file:\n");
            System.out.printf("%s> %s","2","For creating new record:\n");
            System.out.printf("%s> %s","3","For delete pair by name:\n");
            System.out.printf("%s> %s","4","For delete pair by name and phone:\n");
            System.out.printf("%s> %s","5","For get Phone By Account Name:\n");
            System.out.printf("%s> %s","6","For print all pairs, sorted by name:\n");
            System.out.printf("%s> %s","7","For print top 5 outgoing calls\n");
            System.out.printf("%s> %s","8","For print top 5 ingoing calls\n");
            System.out.printf("%s> %s","9","For add outgoing call to account name\n");
            System.out.printf("%s>%s","10","For add ingoing call to account name\n");
            System.out.println("?>----------------------------------------------");

            switch (in.nextLine()){
                case "1":
                    //if invalid will use test File in package tests
                    System.out.println("Please write a path(just enter for default):");
                    String pathToDelete = in.nextLine();
                    try {

                        if ("".equals(pathToDelete)){
                            System.err.println("default File loaded");
                            pathToDelete = "src/com/tu/bzahov/tests/inputTest.txt";
                        }
                        phoneBook.loadAllPairsFromFile(Paths.get(pathToDelete));

                    } catch (IOException e) {

                        System.err.println("You can try to use default one:\n");
                        System.err.println("src/com/tu/bzahov/tests/inputTest.txt");
                        break;
                    }

                    break;
                case "2":
                    System.out.println("Please write phone number");
                    phoneStr = in.nextLine();
                    System.out.println("Please write account name");
                    name = in.nextLine();

                    Phone phone;
                    try {
                        phone = new Phone(phoneStr);
                        phoneBook.addPair(new Pair(phone,name));
                    } catch (InvalidPhoneException e) {
                        System.err.println("sss");
                        break;
                    }
                    break;
                case "3":
                    System.out.println("Please write name for remove Pair");
                    name = in.nextLine();

                    phoneBook.removePairByName(name);
                    break;
                case "4":
                    System.out.println("Please write phone number");
                    phoneStr = in.nextLine();
                    System.out.println("Please write account name");
                    name = in.nextLine();

                    try {
                        phone = new Phone(phoneStr);
                        phoneBook.removePair(new Pair(phone,name));
                    } catch (InvalidPhoneException e) {
                        break;
                    }
                    break;
                case "5":
                    System.out.println("Please write account name");
                    name = in.nextLine();
                    System.out.printf("%s's phone is %s",name,phoneBook.getPhoneByName(name));
                    break;
                case "6":
                    phoneBook.printAllPairsSortedByName();
                    break;
                case "7":
                    phoneBook.print5TopOutgoingCalls();
                    break;
                case "8":
                    phoneBook.print5TopIngoingCalls();
                    break;
                case "9":
                    System.out.println("Please write account name");
                    name = in.nextLine();
                    phoneBook.increaseOutCallOfPair(phoneBook.getPairByName(name));
                    break;
                case "10":
                    System.out.println("Please write account name");
                    name = in.nextLine();
                    phoneBook.increaseInCallOfPair(phoneBook.getPairByName(name));
                    break;

                case "0":
                case "Q":
                case "q":
                    exit = true;
                    break;
                default:
                    System.out.println("Please enter right command");
                    break;
            }
        }
    }
}

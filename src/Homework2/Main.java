package Homework2;

import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    private static Pattern patternForNames = Pattern.compile("[a-zA-Z]");
    private static Pattern patternForOperations = Pattern.compile("[1-4]");
    private static Pattern patternForNumbers = Pattern.compile("[0-9]");
    private static Scanner in = new Scanner(System.in);
    private static boolean flag = true;

    public static void main(String[] args) {
        System.out.println("Hello, it is Phone Book.\n");
        PhoneBook.getInstance();
        HashMap<String, User> phoneBook = PhoneBook.getTelephoneBook();
        while(flag) {
            connectWithUser(phoneBook);
        }
    }

    private static void connectWithUser(HashMap<String, User> phoneBook){
        System.out.println("What do you want?\n" +
                "1.See Telephone Book\n" +
                "2.Add new record\n" +
                "3.Exit");

        String input = in.nextLine();

        if (input.length() != 1 || !check(input, patternForOperations)) {
            System.out.println("Incorrect output. Please try again\n");
        } else {
            switch (Integer.parseInt(input)){
                case 1:
                    showTelephoneBook(phoneBook);
                    break;
                case 2:
                    addNewRecord(phoneBook);
                    break;
                case 3:
                    flag = false;
                    System.out.println("Bye-bye");
                    break;
                default:
                    System.out.println("Oops");
                    System.exit(0);
            }
        }
    }

    private static void showTelephoneBook(HashMap<String, User> phoneBook){
        if (phoneBook.isEmpty()) {
            System.out.println("Phone Book is empty\n");
        } else {
            for (User user : phoneBook.values()) {
                System.out.println(user.getName() + " " + user.getSurname() + " " + user.getTelephoneNumber());
            }
            System.out.println();
        }
    }

    private static void addNewRecord(HashMap<String, User> telephoneBook){
        System.out.println("Enter the name");
        String name = in.nextLine();
        if (!check(name, patternForNames)) {
            return;
        }

        System.out.println("Enter the surname");
        String surname = in.nextLine();
        if (!check(surname, patternForNames)) {
            return;
        }

        System.out.println("Enter the telephone number");
        String telephoneNumber = in.nextLine();
        if (!check(telephoneNumber, patternForNumbers)) {
            return;
        }

        String key = name + surname;
        if (!telephoneBook.containsKey(key)){
            telephoneBook.put(key, new User(name, surname, telephoneNumber));
            System.out.println("Successful addition!\n");
        } else {
            System.out.println("The phone book already has a record with that name and surname");
        }
    }
    private static boolean check(String input, Pattern pattern){
        Matcher matcher = pattern.matcher(input);
        for (int i = 0; i < input.length(); i++) {
            if (!matcher.find()) {
                System.out.println("You can use only " + pattern.toString() + " symbols\n");
                return false;
            }
        }
        return true;
    }
}

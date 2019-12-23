package Homework2;

import java.util.HashMap;

public class PhoneBook {
    private static PhoneBook instance;
    private static HashMap<String, User> phoneBook;

    private PhoneBook() {
        phoneBook = new HashMap<>();
    }

    public static PhoneBook getInstance() {
        if (instance == null)
            instance = new PhoneBook();
        return instance;
    }

    public static HashMap<String, User> getTelephoneBook() {
        return phoneBook;
    }

    public static void addRecord(User user) {
        PhoneBook.phoneBook.put(user.getName() + user.getSurname(), user);
    }
}

package Homework1;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FieldOfWonders {
    private static ArrayList<String> words = new ArrayList<>();
    private static String secret;
    private static StringBuilder userAnswer;
    private static char letter;
    private static Pattern pattern = Pattern.compile("[a-z]");

    public static void main(String[] args) {
        addWords();
        secret = words.get(chooseWord());
        createUserAnswer();
        System.out.println("Hello! It's a Field of Wonders! Try to guess the hidden word.\n");

        while (!secret.equals(userAnswer.toString())) {
            outputUserAnswer();
            inputUserAnswer();
            if(check()){
                System.out.println("Yeah! This is letter from hidden word!\n");
            } else{
                System.out.println("Sorry, but you didn't guess the letter.\n");
            }
        }

        outputUserAnswer();
        System.out.println("Congratulations! You won!");
    }

    private static boolean check(){
        boolean flag = false;
        for (int i = 0; i < secret.length(); i++) {
            if(secret.charAt(i) == letter){
                userAnswer.setCharAt(i, letter);
                flag = true;
            }
        }
        return flag;
    }

    private static void inputUserAnswer(){
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the letter");
        String str = in.nextLine();
        Matcher matcher = pattern.matcher(str);
        if (str.length() != 1 || !matcher.find()){
            System.err.println("Incorrect input. You can enter only 'a-z' symbols. Please, try again\n");
            inputUserAnswer();
        } else {
            letter = str.charAt(0);
        }
    }

    private static void outputUserAnswer(){
        for (int i = 0; i < userAnswer.length(); i++) {
            System.out.print(userAnswer.charAt(i) + " ");
        }
        System.out.println("\n");
    }

    private static int chooseWord(){
        return (int)(Math.random() * words.size());
    }

    private static void addWords(){
        words.add("major");
        words.add("wonderland");
        words.add("probability");
        words.add("loop");
        words.add("horror");
    }

    private static void createUserAnswer(){
        userAnswer = new StringBuilder();
        userAnswer.append("_".repeat(secret.length()));
    }
}

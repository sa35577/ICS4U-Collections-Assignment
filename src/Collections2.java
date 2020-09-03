/*
Collections2.java
Sat Arora
Method that takes in String arguments and returns if they are spelled correctly (aka if they are in a dicitonary passed in from a text file)
 */

//importing packages
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Collections2 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner fileScanner = new Scanner(new File("dictionary.txt")); //scanner to read from file
        HashSet<String> dict = new HashSet<>(); //storing the set of words in the dictionary
        while (fileScanner.hasNext()) { //reads every line in the dicitonary
            dict.add(fileScanner.next().toLowerCase()); //adding the lowercase version of the word
        }
        for (String word : args) { //looping through each of the words to test
            String lowerWord = word.toLowerCase(); //allows for "Hello" and "hello" to both be considered correctly spelled
            if (dict.contains(lowerWord)) { //word is in dictionary
                System.out.printf("%s --> CORRECT!\n",word);
            }
            else System.out.printf("%s --> INCORRECT!\n",word);
        }
    }
}

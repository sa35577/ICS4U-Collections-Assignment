/*
Collections1.java
Sat Arora
Takes in a specified text file and prints out the frequencies of all the words
 */

//importing packages
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Collections1 {
    public static void main(String[] args) throws FileNotFoundException {

        HashMap<String, Word> freq = new HashMap<>(); //HashMap that has the word as the key, and the frequency data as the value
        Scanner kb = new Scanner(System.in); //scanner for reading from console
        System.out.println("Enter file name:");
        String fName = kb.next(); //getting file name
        Scanner fileScanner = new Scanner(new File(fName)); //scanner for reading from file
        int tot = 0; //holding the total word count (increments with every word read)
        while (fileScanner.hasNext()) { //reading every word
            String word = fileScanner.next().toLowerCase().replaceAll("\\p{Punct}",""); //removing punctuation

            if (word.length() > 0) { //checking if there wasn't just a bunch of punctuation with no space
                if (freq.containsKey(word)) { //if the word exists, increment its frequency
                    freq.get(word).increment();
                } else freq.put(word, new Word(word)); //create a new word object with the current word
                tot++; //incrementing total word counter
            }
        }
        TreeSet<Word> treeSet = new TreeSet<>(freq.values()); //treeset to store the data in order
        Iterator treeIT = treeSet.iterator(); //iterator for TreeSet
        while (treeIT.hasNext()) {
            Word cur = (Word) treeIT.next(); //getting the data for the next most frequent word
            System.out.printf("%s %f",cur.s,(double)cur.freq/tot * 100);
            System.out.println("%");
        }

    }
}


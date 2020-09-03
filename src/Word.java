/*
Word.java
Sat Arora
Used for Collections1, storing the data of the word and its frequency
 */


public class Word implements Comparable<Word> {
    String s; //storing the word
    int freq; //storing the frequency of the word
    //constructor
    public Word(String s) {
        this.s = s;
        this.freq = 1; //when the word is used for the first time, it appears once
    }
    //incrementing the frequency of the word
    public void increment() {
        this.freq++;
    }

    @Override
    public int compareTo(Word o) {
        if (this.freq != o.freq) {
            return -this.freq + o.freq; //comparing via word frequency
        }
        return this.s.compareTo(o.s); //comparing via lexographic order (String has built in compare to)
    }
}
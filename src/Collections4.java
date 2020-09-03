/*
Collections4.java
Sat Arora
Takes in current data of driving offences, and allows the user to add offences, show offences for a plate, and terminate
 */

//importing packages
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class Collections4 {
    static HashMap<String, TreeMap<String,String>> speeders = new HashMap<>(); //HashMap that handles the licence plate to a TreeMap consisting of dates and supervisors

    public static void main(String[] args) throws FileNotFoundException {
        Scanner fileScanner = new Scanner(new File("cars.txt")); //scanner to read from file
        int tot = fileScanner.nextInt(); //storing the number of different cars
        fileScanner.nextLine(); //dummy prompt
        for (int i = 0; i < tot; i++) {
            String liscencePlate = fileScanner.nextLine();
            if (!speeders.containsKey(liscencePlate)) {
                speeders.put(liscencePlate,new TreeMap<>()); //creating new TreeMap at current key
            }
            int offs = fileScanner.nextInt(); //storing the number of offences for that specific plate
            fileScanner.nextLine(); //dummy prompt
            for (int j = 0; j < offs; j++) {
                speeders.get(liscencePlate).put(fileScanner.nextLine(),fileScanner.next()); //adding offence data
                fileScanner.nextLine(); //dummy prompt
            }
        }
        fileScanner.close();
        Scanner kb = new Scanner(System.in); //scanner for console
        System.out.println("Welcome to the Massey Database!");
        while (true) {
            System.out.println("Enter 1 to show all offences to a plate");
            System.out.println("Enter 2 to add an offence");
            System.out.println("Enter 3 to terminate");
            int choice = kb.nextInt(); //getting choice
            kb.nextLine(); //dummy prompt

            if (choice == 3) {
                System.out.println("BYE BYE!");
                updateFile(); //updating text file
                return; //terminating the program
            }
            if (choice == 2) {
                System.out.println("TIME TO ADD AN OFFENCE!");
                System.out.println("Enter the license plate");
                String lp = kb.nextLine(); //getting licence plate
                System.out.println("Enter the date");
                String date = kb.nextLine(); //getting date
                System.out.println("Enter the initials of the teachers");
                String initials = kb.nextLine(); //getting initials
                addOffense(lp,date,initials); //adding offense
            }
            if (choice == 1) {
                System.out.println("TIME TO GET OFFENCE DATA!");
                System.out.println("Enter the license plate");
                String lp = kb.nextLine(); //getting licence plate
                show(lp); //showing the data
            }
            System.out.println("\n\n\n"); //give some space between queries
        }
    }
    //method that takes a String for the licence plate passed in
    public static void show(String plate) {
        if (speeders.containsKey(plate)) { //checking if there is speeding data from that plate
            for (Map.Entry<String, String> pair : speeders.get(plate).entrySet()) { //going through each entry
                System.out.println(pair.getKey() + ", supervised by " + pair.getValue());
            }
        }
        else { //no data in the map
            System.out.println("This one is quite the cautious driver!");
        }
    }
    //adding an offense with the data, date, and supervisor passed in
    public static void addOffense(String liscencePlate, String date, String supervisor) {
        if (!speeders.containsKey(liscencePlate)) {
            speeders.put(liscencePlate,new TreeMap<>()); //creating a new TreeMap at the licencePlate position if necessary
        }
        speeders.get(liscencePlate).put(date,supervisor); //adding data to the TreeMap of the current plate key
    }
    //method that re-writes data to text file
    public static void updateFile() throws FileNotFoundException {
        PrintWriter printWriter = new PrintWriter("cars.txt"); //printWriter allows for easy writing to file
        Set<String> keys = speeders.keySet(); //getting the set of plates
        printWriter.println(keys.size()); //number of different plates
        for (String k : keys) {
            printWriter.println(k); //writing the license plate
            printWriter.println(speeders.get(k).size()); //writing the size of the TreeMap
            for (Map.Entry<String, String> cur : speeders.get(k).entrySet()) { //getting next entry
                printWriter.println(cur.getKey());
                printWriter.println(cur.getValue());
            }
        }
        printWriter.close(); //closing the printWriter

    }
}

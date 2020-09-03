/*
Collections3.java
Sat Arora
Takes the 'recommended' movie for every person in the school, then orders the movies in order and the students that are watching the movies in order.
 */

//importing packages
import java.io.*;
import java.util.*;

public class Collections3 {
    public static void main(String[] args) throws IOException {
        HashMap<String,TreeSet<String>> res = new HashMap<>(); //storing the resulting data for movie, tree set of people that are recommended to watch it
        Scanner fileScanner = new Scanner(new File("picks.txt"),"latin1"); //scanner to read from file
        PrintStream printStream = new PrintStream("pickResults.txt"); //printStream to write to file
        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine();
            String[] data = line.split(",");
            String nameData = ""; //storing the names in Last, First (other names if applicable)
            String movieTitle = data[data.length-1]; //storing movie title
            nameData += data[data.length-2] + ", "; //putting the Last, portion
            nameData += data[0]; //putting the first name
            if (data.length != 3) { //adding all other names if they are involved
                for (int i = 1; i < data.length - 1; i++) {
                    nameData += " " + data[i];
                }
            }
            if (!res.containsKey(movieTitle)) {
                res.put(movieTitle,new TreeSet<>());
            }
            res.get(movieTitle).add(nameData);
        }
        Iterator<Map.Entry<String, TreeSet<String>>> it = res.entrySet().iterator(); //iterator for HashMap
        while (it.hasNext()) {
            Map.Entry<String,TreeSet<String>> pair = it.next(); //getting the next HashMap entry
            printStream.println(pair.getKey());
            Iterator treeIT = pair.getValue().iterator(); //iterator for TreeSet
            while (treeIT.hasNext()) {
                printStream.printf("\t%s\n",treeIT.next()); //printing name data with a tab
            }
        }
        printStream.close(); //closing the PrintStream
    }

}

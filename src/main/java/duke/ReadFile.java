package duke;

import java.util.ArrayList;

/**
 * Class to read file and process file into a string format.
 */
public class ReadFile {
    /**
     * Reads the data file for loading into the current iteration of the program.
     * 
     * @param list  current iteration of the empty list.
     * @return list containing all the tasks in String format.
     */
    public static String readFile(ArrayList<Task> list) {
        int n = list.size() - 1;
        String s = "";

        while (n >= 0) {
            String d;
            if (list.get(n) instanceof Todo) {
                s += list.get(n).toString() + "\n";
            } else {
                d = list.get(n).date.toString();
                s += d + " " + list.get(n).toString() + "\n";
            }

            n -= 1;
        }

        return s;
    }
}
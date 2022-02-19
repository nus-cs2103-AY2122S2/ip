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
        String data = "";

        while (n >= 0) {
            String date;
            if (list.get(n) instanceof Todo) {
                data += list.get(n).toString() + "\n";
            } else {
                date = list.get(n).date.toString();
                data += date + " " + list.get(n).toString() + "\n";
            }

            n -= 1;
        }

        return data;
    }
}
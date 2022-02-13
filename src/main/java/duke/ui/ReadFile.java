package duke.ui;

import duke.action.Action;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.ArrayList;
import java.util.Scanner; // Import the Scanner class to read text files

/**
 * Credit to: w3schools.com
 * Contains method readFile needed for TaskList class
 */
public class ReadFile {

    /**
     * Reads the data from tasklist from the previous
     * session and then converts the data into its
     * matching Action object etc;Todo, Deadline, Event.
     * Then adds these Action objects into the Arraylist list.
     *
     * @param file file containing date from the last session
     * @param list the taskList where previous data are to be added to
     */
    public static void readFile(File file, ArrayList<Action> list) {
        try {
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                SavedDataParser.parse(data, list);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } finally {
            //do nothing
        }
    }
}


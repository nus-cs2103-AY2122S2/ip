package duke;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;

/**
 * deals with loading tasks from the file and saving tasks in the file
 */
public class Storage {
    private String filePath;

    /**
     * Initializes the location of the file to retrieve data from.
     * @param filePath
     */
    Storage(String filePath){
        this.filePath = filePath;
    }
    /*ArrayList<String> load() {
        ArrayList<String> lines = Collections.emptyList();
        try
        {
            lines = Files.readAllLines(Paths.get(filePath), StandardCharsets.UTF_8);
        }

        catch (IOException e)
        {

            // do something
            e.printStackTrace();
        }
        return lines;
        return Files.readAllLines(Paths.get("sample.txt"));
    }

     */

    /**
     * Writes the values stored within the tasklist to the file called data/tasks.txt
     * @param list
     */
    void save(TaskList list) {
        try {
            FileWriter myWriter = new FileWriter("data/tasks.txt");
            for (int i=0; i<list.size(); i++) {
                myWriter.write(i+1 + ". " + list.get(i));
                myWriter.write(String.format("%n"));

            }
            myWriter.close();
            //System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }
}

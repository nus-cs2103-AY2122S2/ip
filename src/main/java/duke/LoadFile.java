package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Loads the data stored in the disk into the current run of the application.
 */
public class LoadFile {
    public static boolean hasLoaded = false;

    /**
     * Method that loads the data stored in the .txt data file into the current iteration of the program.
     * 
     * @return ArrayList<Task> containing the loaded data.
     * @throws FileNotFoundException if the .txt data file cannot be found in the disk.
     */
    public static ArrayList<Task> load() throws FileNotFoundException {
        File file = new File("data/duke.txt");
        ArrayList<Task> list = new ArrayList<>();

        if (file.exists()) {
            hasLoaded = true;
            try {
                Scanner s = new Scanner(file);
                while (s.hasNextLine()) {
                    String str = s.nextLine();

                    Task task;

                    if (str.contains("[T]")) {
                        task = new Todo(str.substring(7));
                    } else if (str.contains("[E]")) {
                        int startIndex = str.indexOf(" (at: ");
                        task = new Event(str.substring(18, startIndex), LocalDate.parse(str.substring(0, 10)));
                    } else {
                        int startIndex = str.indexOf(" (by: ");
                        task = new Deadline(str.substring(18, startIndex), LocalDate.parse(str.substring(0, 10)));
                    }

                    if (str.contains("[X]")) {
                        task.markAsDone();
                    }

                    list.add(task);

                }

                Collections.reverse(list);
            } catch (IOException e) {
                System.out.println("Oops, I wasn't able to load the previously saved list. :(");
            }
        }
        return list;
    }
}


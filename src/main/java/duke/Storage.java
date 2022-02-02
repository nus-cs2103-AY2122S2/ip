package duke;

import java.io.File;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Contains the file path for a storage object.
 * Provides methods to load and write the task list from and to
 * the file located in the file path respectively.
 */
public class Storage {

    private String filePath;
    private File f;
    private Scanner s;

    /**
     * Stores the file path and creates a new file object.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        this.f = new File(filePath);
    }

    /**
     * Loads the task history from the file stored at the filepath (if any).
     *
     * @return Returns an arraylist containing each line from the file as a separate string.
     */
    public ArrayList<String> load() {
        ArrayList<String> tasksRead = new ArrayList<>();
        try {
            File taskFile = new File(filePath);
            if (taskFile.exists()) {
                this.s = new Scanner(f);
                while (s.hasNext()) {
                    tasksRead.add(s.nextLine());
                }
            } else {
                taskFile.createNewFile();
                this.s = new Scanner(f);
            }
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
        return tasksRead;
    }

    /**
     * Writes the updated task list the file stored at the file path (if any).
     *
     * @param textToAdd Updated task list to be written to the file stored at the file path.
     * @throws IOException Throws exception for file writer object.
     */
    public void write(ArrayList<Task> textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task t: textToAdd) {
            fw.write(t.toString() + System.lineSeparator());
        }
        fw.close();
    }
}

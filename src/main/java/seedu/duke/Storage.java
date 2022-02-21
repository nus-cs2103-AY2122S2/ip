package seedu.duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Scanner;

import seedu.task.Task;

/**
 * Contains the file path for a storage object.
 * Provides methods to load and write the task list from and to the file located in the file path respectively.
 */
public class Storage {
    private final String filePath;
    private File f;
    private Scanner s;

    /**
     * Stores the file path and creates a new file object.
     */
    public Storage(String filePath) {
        assert filePath != null : "Storage->Storage: File path string cannot be null.";

        this.filePath = filePath;
        this.f = new File(filePath);
    }

    /**
     * Loads the task history from the file stored at the filepath (if any),
     * otherwise, creates a new empty file at the filepath.
     *
     * @return Returns an arraylist containing each line (task) from the file as a separate string.
     */
    public ArrayList<String> load() {
        ArrayList<String> tasksRead = new ArrayList<>();

        try {
            File taskFile = new File(filePath);

            if (!taskFile.exists()) {
                taskFile.createNewFile();
            }
            this.s = new Scanner(f);

            while (s.hasNext()) {
                tasksRead.add(s.nextLine());
            }

        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }

        return tasksRead;
    }

    /**
     * Writes the updated task list to the file stored at the file path.
     *
     * @param textToWrite Updated task list to be written to the file stored at the file path.
     * @throws IOException If write is not successfully executed.
     */
    public void write(ArrayList<Task> textToWrite) throws IOException {
        assert textToWrite != null : "Storage->write: Text to be written to file cannot be null.";

        FileWriter fw = new FileWriter(filePath);
        for (Task t : textToWrite) {
            fw.write(t.toWrite() + System.lineSeparator());
        }
        fw.close();
    }
}

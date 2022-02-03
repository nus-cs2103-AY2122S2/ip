package main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Represent a memory storage.
 * @author Fan Jue
 * @version 0.1.0
 * @since 0.1.0
 */
public class Storage {
    /** A file used to read/store memory from/in hard disk */
    protected File file;
    /** A string of the relative path of the file */
    protected String path;
    /** A boolean value of whether the storage has been modified */
    protected boolean updated;

    /**
     * Create a temporary storage to access memory in hard disk.
     *
     * @param path The path to access memory in the hard disk.
     */
    public Storage(String path) {
        this.path = path;
        this.file = new File(path);
        this.updated = false;
    }

    /**
     * Return the list of memory representation of tasks stored in the memory.
     *
     * @return A list of string of the memory representation of tasks.
     * @throws TesseractException If the path is invalid or a new file cannot be created.
     */
    public List<String> getStorage() throws TesseractException {
        List<String> tasks = new ArrayList<String>();
        try {
            Scanner sc = new Scanner(this.file);
            while (sc.hasNext()) {
                tasks.add(sc.nextLine()); // sc.nextLine()).split("@", 4);
            }
        } catch (IOException e) {
            try {
                this.file.createNewFile();
            } catch (IOException err) {
                throw new TesseractException("Not joking but I cannot create a memory for you.. \n"
                        + "You mind changing a laptop?");
            }
        }
        return tasks;
    }

    /**
     * The list of tasks is modified.
     * Memory needs to be updated.
     */
    public void needUpdate() {
        this.updated = true; // only update if there is change (new task added)
    }

    /**
     * Return a boolean representing if the memory needs to be updated.
     * @return True if the memory needs to be updated, false otherwise.
     */
    public boolean isUpdated() {
        return this.updated;
    }

    /**
     * Update the memory in the hard disk by writing information directly to the file.
     *
     * @param taskList The current list of all tasks.
     * @param numOfTasks The current total number of tasks.
     * @throws TesseractException If memory cannot be updated due to errors.
     */
    public void updateStorage(TaskList taskList, int numOfTasks) throws TesseractException {
        try {
            FileWriter fw = new FileWriter(this.path);
            for (int i = 0; i < numOfTasks; i++) {
                fw.write(taskList.get(i).toMemoryString());
                fw.write(System.lineSeparator());
            }
            fw.close();
            this.updated = false; // storage is up-to-date
        } catch (IOException e) {
            throw new TesseractException("Sorry but I cannot upload your list of tasks into memory due to"
                    + "some unforeseen errors :(\n "
                    + "Try command force exit (to be added in later)");
        }
    }
}

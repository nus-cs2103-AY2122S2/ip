package duke.storage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import duke.parser.DukeParser;
import duke.task.Task;

public class Storage {

    private String filePath;

    /**
     * Creates a Storage instance
     * @param p Path to file to be used in Storage methods
     */
    public Storage(String p) {
        this.filePath = p;
    }

    /**
     * Checks if file exists at the relative filePath and
     * parses it using DukeParser to get previously stored List. If
     * not, creates file at the location and returns a new List.
     * @return ArrayList which may contain previously stored tasks.
     */
    public List<Task> load() {
        File data = new File(filePath);
        try {
            if (data.createNewFile()) {
                return new ArrayList<Task>();
            } else {
                return DukeParser.readData(data);
            }
        } catch (IOException e) {
            return new ArrayList<Task>();
        }
    }

    /**
     * Stores tasks in current List into file at filePath
     * @param l List to be stored
     */
    public void store(List<Task> l) {
        try {
            BufferedWriter b = new BufferedWriter(new FileWriter(filePath, false));
            String s = "";
            for (Task t : l) {
                s += t.storeFormat();
            }
            b.write(s);
            b.close();
        } catch (IOException e) {
            System.out.println("Error when storing");
        }
    }

}

package duke;

import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

/**
 * Represents the storage space, together with
 * functions to read and write data from the file path.
 */
public class Storage {
    /**
     * The filePath where data is stored.
     */
    private String filePath;

    /**
     * Constructor for Storage.
     *
     * @param filePath location where the file is to be stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Returns the list of tasks stored if the file already
     * exits or else returns an empty list.
     *
     * @return list containing saved tasks or empty list.
     * @throws Exception If an unexpected error occurs.
     */
    public ArrayList<Task> readData() throws Exception {
        File file = new File(filePath);
        ArrayList<Task> startingList = new ArrayList<>();

        if (file.exists()) {

            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();

            while (line != null) {
                char firstLetter = line.charAt(0);
                String[] in = line.split(":");
                if (firstLetter == 'T') {
                    startingList.add(new Todo(in[2]));
                } else if (firstLetter == 'D') {
                    startingList.add(new Deadline(in[2], in[3]));
                } else if (firstLetter == 'E') {
                    startingList.add(new Event(in[2], in[3]));
                } else {
                    assert (firstLetter == 'T') || (firstLetter == 'D') || (firstLetter == 'E') : "Unknown entry.";
                    throw new DukeException(UI.unKnown);
                }
                line = reader.readLine();
            }
        }
        return startingList;
    }

    /**
     * Writes data entered by the user as a file
     * as per the file path.
     *
     * @param list Updated list of Tasks.
     * @throws Exception If unexpected error occurs.
     */
    public void writeData(ArrayList<Task> list) throws Exception {
        FileWriter fw = new FileWriter(filePath);
        for(int n = 0; n < list.size(); n++) {
            fw.write(list.get(n).toSave() + "\n");
        }
        fw.close();
    }
}

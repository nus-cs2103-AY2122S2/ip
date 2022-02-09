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
                String[] data = line.split(":");

                if (firstLetter == 'T') {
                    startingList.add(new Todo(data[2]));
                } else if (firstLetter == 'D') {
                    startingList.add(new Deadline(data[2], data[3]));
                } else if (firstLetter == 'E') {
                    startingList.add(new Event(data[2], data[3]));
                } else {
                    assert (firstLetter == 'T') || (firstLetter == 'D') || (firstLetter == 'E') : "Unknown entry.";
                    throw new DukeException(UI.ERROR_UNKNOWN);
                }
                line = reader.readLine();
            }
        }

        return startingList;
    }

    /**
     * Writes updated data entered to file as per File path.
     *
     * @param list Updated list of Tasks to be saved.
     * @throws Exception If an unexpected error occurs.
     */
    public void writeData(ArrayList<Task> list) throws Exception {
        FileWriter fileWriter = new FileWriter(filePath);
        for (int n = 0; n < list.size(); n++) {
            fileWriter.write(list.get(n).toSave() + "\n");
        }
        fileWriter.close();
    }
}

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
 * Represents the storage space to read past data from the file and write data to file.
 */
public class Storage {
    /**
     * The filePath where data is stored.
     */
    private String filePath;

    /**
     * Instantiate storage with the given file path.
     *
     * @param filePath Location of file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Returns the list of tasks stored if the file already exists
     * or else returns an empty list.
     *
     * @return List containing saved tasks if any, otherwise empty list.
     * @throws Exception If an error occurs when reading data from saved file.
     */
    public ArrayList<Task> readData() throws Exception {
        try {
            File file = new File(filePath);
            ArrayList<Task> startingList = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();

            while (line != null) {
                char firstLetter = line.charAt(0);
                String[] data = line.split(":");
                if (firstLetter == 'T') {
                    startingList.add(new Todo(data));
                } else if (firstLetter == 'D') {
                    startingList.add(new Deadline(data));
                } else if (firstLetter == 'E') {
                    startingList.add(new Event(data));
                } else {
                    throw new DukeException(UI.ERROR_UNKNOWN);
                }
                line = reader.readLine();
            }

            return startingList;
        } catch (Exception e) {
            throw new DukeException(UI.ERROR_CANNOT_READ);
        }
    }

    /**
     * Stores updated data entered to the file as per file path.
     *
     * @param list Updated list of Tasks to be saved.
     * @throws Exception If an error occurs when storing data.
     */
    public void storeData(ArrayList<Task> list) throws Exception {
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            for (int n = 0; n < list.size(); n++) {
                fileWriter.write(list.get(n).toSave() + "\n");
            }
            fileWriter.close();
        } catch (Exception e) {
            throw new DukeException(UI.ERROR_CANNOT_SAVE);
        }
    }
}

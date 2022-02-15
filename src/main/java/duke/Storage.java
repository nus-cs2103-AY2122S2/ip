package duke;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 * Storage class is used for storing data and loading data
 *
 * @author brandonrhan
 * @version 0.0.0
 */
public class Storage {
    private final File file;
    private ArrayList<Task> list;

    /**
     * Constructs a Storage
     *
     * @param filePath the filepath used to store data and load data
     */
    Storage(Path filePath, Path dirPath) throws DukeException {
        try {
            if (!filePath.toFile().exists()) {
                dirPath.toFile().mkdirs();
                Files.createFile(filePath);
            }
            this.file = filePath.toFile();
            this.list = new ArrayList<Task>();
        } catch (IOException e) {
            throw new DukeException("Loading error");
        }
    }

    /**
     * Loads all data in the filepath
     * Returns a list containing all task saved in the filepath
     *
     * @return ArrayList contains all tasks saved in the filepath
     * @throws DukeException If there is error when reading data
     */
    ArrayList<Task> load() throws DukeException {
        try {
            FileReader reader = new FileReader(this.file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String input = bufferedReader.readLine();
            while (input != null) {
                this.list.add(createTaskByType(input));
                input = bufferedReader.readLine();
            }
            return this.list;
        } catch (Exception e) {
            throw new DukeException("Loading error");
        }
    }

    /**
     * Creates a different task object according user input
     *
     * @param input Details of the task to be created
     * @return A Todo, Event or DeadLine object
     * @throws DukeException If the input is not valid
     */
    private Task createTaskByType(String input) throws DukeException {
        Task newTask;
        String[] task = input.split(" ### ");
        String type = task[0];
        String status = task[1];
        String thing = task[2];
        if (type.equals("T")) {
            newTask = new Todo(thing);
        } else if (type.equals("D")) {
            newTask = new Deadline(thing, task[3]);
        } else if (type.equals("E")) {
            newTask = new Event(thing, task[3]);
        } else {
            throw new DukeException("Loading error");
        }
        if (status.equals("1")) {
            newTask.mark();
        }
        return newTask;
    }


    /**
     * Saves all tasks in the list to the filepath
     *
     * @param saveList the list to be save
     */
    void save(ArrayList<Task> saveList) {
        try {
            FileWriter writer = new FileWriter(this.file);
            BufferedWriter br = new BufferedWriter(writer);
            for (int i = 0; i < saveList.size(); i++) {
                br.write(saveList.get(i).saveFormat());
                br.newLine();
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

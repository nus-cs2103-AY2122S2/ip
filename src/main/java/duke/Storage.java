package duke;

import java.io.*;
import java.util.ArrayList;

/**
 * Storage class is used for storing data and loading data
 */
public class Storage {
    private final String filePath;
    private ArrayList<Task> list;

    /**
     * Constructor of Storage
     * @param filePath the filepath used to store data and load data
     */
    Storage(String filePath) {
        this.filePath = filePath;
        this.list = new ArrayList<Task>();
    }

    /**
     * Load all data in the filepath
     * Return a list containing all task saved in the filepath
     * @return ArrayList contains all tasks saved in the filepath
     * @throws DukeException If there is error when reading data
     */
    ArrayList<Task> load() throws DukeException {
        try {
            FileReader reader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String input = bufferedReader.readLine();
            while (input != null) {
                String[] task = input.split(" ### ");
                String type = task[0];
                String status = task[1];
                String thing = task[2];
                if (type.equals("T")) {
                    Task newTask = new Todo(thing);
                    if (status.equals("1")) {
                        newTask.mark();
                    }
                    this.list.add(newTask);
                } else if (type.equals("D")) {
                    Task newTask = new Deadline(thing, task[3]);
                    if (status.equals("1")) {
                        newTask.mark();
                    }
                    this.list.add(newTask);
                } else if (type.equals("E")) {
                    Task newTask = new Event(thing, task[3]);
                    if (status.equals("1")) {
                        newTask.mark();
                    }
                    this.list.add(newTask);
                }
                input = bufferedReader.readLine();
            }
            return this.list;
        } catch (IOException | ArrayIndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
            throw new DukeException("Loading error");
        }
    }

    /**
     * Save all tasks in the list to the filepath
     * @param saveList the list to be save
     */
    void save(ArrayList<Task> saveList) {
        try {
            FileWriter writer = new FileWriter(filePath);
            BufferedWriter br = new BufferedWriter(writer);
            for (int i = 0; i < saveList.size(); i++) {
                br.write(saveList.get(i).saveFormat());
                br.newLine();
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

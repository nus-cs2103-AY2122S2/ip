package storage;

import exception.DukeException;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;
import task.TaskList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Interface that deals with pushing or pulling tasks from the storage.
 */
public class Storage {

    String path;

    public Storage(String path) {
        this.path = path;
    }

    /**
     * Retrieves tasks from the storage (if any) and adds them to a new task list.
     * @return list of tasks retrieved from the storage, else an empty list.
     * @throws DukeException If data file does not exist.
     */
    public ArrayList<Task> setUpData() throws DukeException {
        try {
            FileReader myObj = new FileReader(this.path);
            BufferedReader br = new BufferedReader(myObj);
            String line;
            ArrayList<Task> tasks = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                String[] lineArray = line.split("/");
                Task task;
                switch (lineArray[0]) {
                    case "T":
                        task = new Todo(lineArray[2]);
                        break;
                    case "E":
                        task = new Event(lineArray[2], lineArray[3]);
                        break;
                    default:
                        task = new Deadline(lineArray[2], lineArray[3]);
                }
                if (lineArray[1].equals("X")) {
                    task.setAsDone();
                }
                tasks.add(task);
            }
            return tasks;
        } catch (IOException e) {
            throw new DukeException("File does not exist.");
        }
    }

    /**
     * Adds or removes tasks from storage according to changes made by user.
     * @param taskList updated list of tasks.
     */
    public void updateData(TaskList taskList) {
        try {
            FileWriter myObj = new FileWriter(this.path);
            myObj.flush();
            for (int i = 0; i < taskList.tasks.size(); i++) {
                myObj.write(taskList.tasks.get(i).symbol() + "/" + taskList.tasks.get(i).getStatusIcon() + "/" +
                        taskList.tasks.get(i).toString() + "\n");
            }
            myObj.close();
        } catch (IOException e) {
            System.out.println("File does not exist.");
        }
    }
}

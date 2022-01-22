package duke.storage;

import duke.task.Task;
import duke.exception.DukeException;
import duke.task.Todo;
import duke.task.Deadline;
import duke.task.Event;
import java.io.*;
import java.util.ArrayList;

/**
 * Deals with loading tasks from the file and saving tasks in the file
 */
public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads list of tasks from file
     *
     * @return list of tasks from file
     * @throws DukeException if task type is invalid or when I0Exception occurs
     */
    public ArrayList<Task> load() throws DukeException {
        try {
            File file = new File(filePath);
            if (file.exists()) {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line;
                ArrayList<Task> tasks = new ArrayList<>();
                while ((line = br.readLine()) != null) {
                    Task task;
                    String[] splitLine  = line.split(" | ");
                    if (splitLine[0].equals("T")) {
                       task = new Todo(splitLine[2]);
                    }
                    else if (splitLine[0].equals("D")) {
                        task = new Deadline(splitLine[2], splitLine[3]);
                    }
                    else if (splitLine[0].equals("E")) {
                        task = new Event(splitLine[2], splitLine[3]);
                    }
                    else {
                        throw new DukeException("OOPS!!! I don't know what task type is that.");
                    }
                    if (splitLine[1].equals("1")) {
                        task.markTaskDone();
                    }
                    tasks.add(task);
                }
                return tasks;
            }
            else {
                return new ArrayList<>();
            }
        }
        catch (IOException e) {
            throw new DukeException("OOPS!!! An I0Exception occurred.");
        }
    }

    /**
     * Save list of tasks into file
     *
     * @param tasks list of tasks
     * @throws DukeException if I0Exception occurs
     */
    public void save(ArrayList<Task> tasks) throws DukeException {
        try {
            File file = new File("./data/duke.txt");
            file.getParentFile().mkdirs();
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            for (Task task: tasks) {
                bw.append(task.getFileFormat());
                bw.append("\n");
            }
            bw.close();
        }
        catch (IOException e) {
            throw new DukeException("OOPS!!! An I0Exception occurred.");
        }
    }
}

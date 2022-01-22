package duke.storage;

import duke.task.Task;
import duke.exception.DukeException;
import duke.task.Todo;
import duke.task.Deadline;
import duke.task.Event;
import java.io.*;
import java.util.ArrayList;

public class Storage {
    protected String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

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
            throw new DukeException(e.getMessage());
        }
    }

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
            throw new DukeException(e.getMessage());
        }
    }
}

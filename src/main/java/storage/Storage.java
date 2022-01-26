package storage;

import exception.JarvisException;
import task.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private String dataFilePath;

    /**
     * Constructs a Storage object.
     *
     * @param dataFilePath Path of the file to store and retrieve data from.
     */
    public Storage(String dataFilePath) {
        this.dataFilePath = dataFilePath;
    }

    /**
     * Loads data from the file path.
     *
     * @return List of tasks stored in the file.
     * @throws JarvisException If file retrieval failed.
     */
    public List<Task> loadData() throws JarvisException {
        try {
            List<Task> lst = new ArrayList<>();
            File dataFile = new File(dataFilePath);
            if (dataFile.exists()) {
                Scanner s = new Scanner(dataFile);
                while (s.hasNext()) {
                    String[] line = s.nextLine().split("\\|");
                    Task task;
                    switch (line[0]) {
                    case "T":
                        task = new Todo(line[2]);
                        break;
                    case "D":
                        task = new Deadline(line[2], LocalDateTime.parse(line[3]));
                        break;
                    case "E":
                        task = new Event(line[2], LocalDateTime.parse(line[3]));
                        break;
                    default:
                        throw new JarvisException("Unexpected task type encountered: " + line[0]);
                    }

                    if (line[1].equals("1")) {
                        task.markAsDone();
                    }
                    lst.add(task);
                }
            } else {
                dataFile.getParentFile().mkdirs();
                dataFile.createNewFile();
            }
            return lst;
        } catch (IOException e) {
            throw new JarvisException("An error has occurred whilst retrieving your tasks.");
        }
    }

    /**
     * Saves the current state of tasks to storage.
     *
     * @param tasks The tasks to save to the file.
     * @throws JarvisException If file saving failed.
     */
    public void saveChanges(TaskList tasks) throws JarvisException {
        try {
            StringBuilder sb = new StringBuilder();
            for (Task t : tasks.getTasks()) {
                sb.append(t.toFileString()).append("\n");
            }
            FileWriter fw = new FileWriter(dataFilePath);
            fw.write(sb.toString());
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new JarvisException("An error has occurred whilst saving your tasks.");
        }
    }
}

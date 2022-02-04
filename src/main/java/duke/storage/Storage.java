package duke.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;
import duke.ui.Ui;

/**
 * Saves files to data/duke.txt and loads saved file.
 */
public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the duke.txt file to get data from previous runs of duke.Duke.
     *
     * @return ArrayList of Tasks ArrayList that is populated with duke.txt save file.
     * @throws FileNotFoundException Exception thrown when file is not found.
     */
    public ArrayList<Task> load() throws FileNotFoundException {
        File file = new File(filePath);
        BufferedReader bufferedReader = null;
        if (!Files.exists(Paths.get(filePath))) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        FileReader fileReader = new FileReader(file);
        bufferedReader = new BufferedReader(fileReader);
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            String line = bufferedReader.readLine();
            while (line != null) {
                if (line.length() == 0) {
                    return taskList;
                } else {
                    String typeAndIsDone = line.substring(0, 6);
                    String type = typeAndIsDone.substring(1, 2); // D, E, or T
                    String isDone = typeAndIsDone.substring(4, 5);
                    if (!type.equals("T")) {
                        String content = "";
                        String dateString = "";
                        if (type.equals("D")) {
                            content = line.substring(7, line.lastIndexOf(" (by: "));
                            dateString = line.substring(line.lastIndexOf("by: ") + 4, line.lastIndexOf(")"));
                            LocalDateTime dateTime = LocalDateTime.parse(dateString, Ui.OUTPUT_FORMATTER);
                            if (isDone.equals("X")) {
                                taskList.add(new Deadline(content, dateTime, true));
                            } else {
                                taskList.add(new Deadline(content, dateTime));
                            }
                        } else {
                            content = line.substring(7, line.lastIndexOf(" (at: "));
                            dateString = line.substring(line.lastIndexOf("at: ") + 4, line.lastIndexOf(")"));
                            LocalDateTime dateTime = LocalDateTime.parse(dateString, Ui.OUTPUT_FORMATTER);
                            if (isDone.equals("X")) {
                                taskList.add(new Event(content, dateTime, true));
                            } else {
                                taskList.add(new Event(content, dateTime));
                            }
                        }
                    } else {
                        if (isDone.equals("X")) {
                            String content = line.substring(7);
                            taskList.add(new ToDo(content, true));
                        } else {
                            String content = line.substring(7);
                            taskList.add(new ToDo(content));
                        }
                    }
                }
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return taskList;
    }

    /**
     * Saves the current tasks into data/duke.txt.
     *
     * @param taskList taskList object that contains all tasks to be saved
     */
    public void save(TaskList taskList) {
        File file = new File(filePath);
        BufferedWriter bufferedWriter = null;
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file);
            bufferedWriter = new BufferedWriter(fileWriter);
            for (Task task : taskList.getTasks()) {
                bufferedWriter.write(task.toString() + "\n");
            }
            bufferedWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("    File error: not found");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("    Error: cannot save file");
        }
    }
}

package com.duke.modules;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import com.duke.tasks.Deadline;
import com.duke.tasks.Event;
import com.duke.tasks.Task;
import com.duke.tasks.Todo;

/**
 * A class responsible for saving/loading a task list into/from a text file.
 */
public class Storage {
    private static Storage STORAGE_INSTANCE;
    private String directoryPath = System.getProperty("user.dir") + "/data/";
    private String listFile = "listData.txt";

    public Storage() {
    }

    public static Storage getInstance() {
        STORAGE_INSTANCE = (STORAGE_INSTANCE == null) ? new Storage() : STORAGE_INSTANCE;
        return STORAGE_INSTANCE;
    }

    /**
     * Saves the task list given to a text file.
     *
     * @param taskList A TaskList object
     * @return A boolean depending on whether the saving process is successful.
     * @throws IOException On failure of read or write operations.
     */
    public boolean saveList(ArrayList<Task> taskList) throws IOException {
        boolean isSuccess = false;
        File file = new File(this.directoryPath + this.listFile);
        file.getParentFile().mkdirs();
        file.createNewFile();

        FileWriter writer = new FileWriter(this.directoryPath + this.listFile);
        BufferedWriter bufferedWriter = new BufferedWriter(writer);

        for (Task t : taskList) {
            bufferedWriter.write(t.getSaveDescription());
            bufferedWriter.newLine();
        }

        bufferedWriter.close();
        isSuccess = true;
        return isSuccess;
    }


    /**
     * Load the task list from a text file stored in the save directory.
     *
     * @param taskList A TaskList object
     * @return A boolean depending on whether the loading process is successful.
     * @throws IOException On failure of read or write operations.
     */
    public boolean loadList(ArrayList<Task> taskList) {
        boolean isSuccess = false;
        try {
            File file = new File(this.directoryPath + this.listFile);
            file.getParentFile().mkdirs();
            file.createNewFile();

            FileReader reader = new FileReader(this.directoryPath + this.listFile);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                String[] task = line.split(" \\| ");
                switch (task[0]) {
                case "Todo":
                    Todo todoTask = new Todo(task[2]);
                    todoTask.setDone((task[1].equals("1") ? true : false));
                    taskList.add(todoTask);
                    break;
                case "Deadline":
                    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_DATE_TIME;
                    LocalDateTime dateTime = LocalDateTime.parse(task[3], dateTimeFormatter);
                    Deadline deadlineTask = new Deadline(task[2], dateTime);
                    deadlineTask.setDone((task[1].equals("1") ? true : false));
                    taskList.add(deadlineTask);
                    break;
                case "Event":
                    Event eventTask = new Event(task[2], task[3]);
                    eventTask.setDone((task[1].equals("1") ? true : false));
                    taskList.add(eventTask);
                    break;
                default:
                    break;
                }

            }

            bufferedReader.close();
        } catch (DateTimeParseException e) {
            System.out.println(
                    "Invalid save file format. Save file may have been edited or corrupted.");
        } catch (IOException e) {
            System.out.println("Unable to load list."
                    + "Please check if you have permission to read from files in the following directory: "
                    + directoryPath);
        }
        return isSuccess;
    }

    public String getDirectoryPath() {
        return directoryPath;
    }
}

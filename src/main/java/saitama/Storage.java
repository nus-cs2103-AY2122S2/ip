package saitama;

import saitama.exceptions.InvalidFormatException;
import saitama.tasks.Deadline;
import saitama.tasks.Event;
import saitama.tasks.Task;
import saitama.tasks.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Helps with the storing and loading of the task list data.
 */
public class Storage {

    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads a task list from the given text file stored in filePath.
     *
     * @return An array list of tasks.
     */
    public ArrayList<Task> load() {
        ArrayList<Task> ls = new ArrayList<Task>();
        File f = new File(filePath);

        try {
            Scanner sc = new Scanner(f);
            while (sc.hasNext()) {
                boolean isDone = false;
                Task newTask;
                String[] substring;

                //split each line in text file into [task type, isDone, task description]
                String[] data = sc.nextLine().split(" ", 3);

                //assign isDone
                switch(data[1]) {
                case "":
                    isDone = false;
                case "1":
                    isDone = true;
                }

                //add task to array list
                switch(data[0]) {
                case "T":
                    ls.add(new ToDo(data[2], isDone));
                    break;
                case "D":
                    substring = data[2].split(" /by ", 2);
                    try {
                        newTask = new Deadline(substring[0], substring[1], isDone);
                        ls.add(newTask);
                    } catch (InvalidFormatException e) {
                        System.out.println("Data corrupted! Returning empty task list...");
                        return new ArrayList<Task>();
                    }
                    break;
                case "E":
                    substring = data[2].split(" /at ", 2);
                    newTask = new Event(substring[0], substring[1], isDone);
                    ls.add(newTask);
                    break;
                }
            }
            return ls;
        } catch (FileNotFoundException e) {
            return ls;
        }
    }

    /**
     * Saves the current task list to the specified filePath.
     *
     * @param taskList The task list to be saved.
     */
    public void save(ArrayList<Task> taskList) {
        File f = new File(filePath);
        String directoryName = filePath.replace(f.getName(), "");
        File directory = new File(directoryName);
        directory.mkdirs();
        try {
            FileWriter fw = new FileWriter(filePath);
            for (Task task : taskList) {
                task.saveTask(fw);
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Task list cannot be saved!");
        }
    }
}

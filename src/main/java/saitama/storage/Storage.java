package saitama.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import saitama.exceptions.FileCorruptException;
import saitama.exceptions.InvalidDateTimeException;
import saitama.exceptions.InvalidFormatException;
import saitama.tasks.Deadline;
import saitama.tasks.Event;
import saitama.tasks.RecursiveTag;
import saitama.tasks.Task;
import saitama.tasks.ToDo;

/**
 * Helps with the storing and loading of the task list data.
 */
public class Storage {

    private String filePath;

    /**
     * Initialises a storage object.
     *
     * @param filePath A file path to a text file where data will be stored.
     */
    public Storage(String filePath) {
        assert filePath.endsWith(".txt") : "File path needs to be a .txt file";
        this.filePath = filePath;
    }

    /**
     * Loads a task list from the given text file stored in filePath.
     *
     * @return An array list of tasks.
     */
    public ArrayList<Task> load() {
        assert filePath.endsWith(".txt") : "File path needs to be a .txt file";

        ArrayList<Task> taskList = new ArrayList<Task>();
        File f = new File(filePath);

        try {
            Scanner sc = new Scanner(f);
            while (sc.hasNext()) {
                boolean isDone;
                Task newTask;
                String[] splitCommandArguments;

                //split each line in text file into [task type, isDone, task description]
                String[] data = sc.nextLine().split(" ", 4);

                if (data.length < 4) {
                    throw new FileCorruptException();
                }

                //assign isDone
                switch(data[1]) {
                case "0":
                    isDone = false;
                    break;
                case "1":
                    isDone = true;
                    break;
                default:
                    throw new FileCorruptException();
                }

                //assign recursiveTag
                RecursiveTag recursiveTag = RecursiveTag.get(data[2]);

                //add task to array list
                switch(data[0]) {
                case "T":
                    taskList.add(new ToDo(data[3], isDone, recursiveTag));
                    break;
                case "D":
                    splitCommandArguments = data[3].split(" /by ", 2);
                    if (splitCommandArguments.length < 2) {
                        throw new FileCorruptException();
                    }
                    try {
                        newTask = new Deadline(splitCommandArguments[0],
                                splitCommandArguments[1], isDone, recursiveTag);
                        taskList.add(newTask);
                    } catch (InvalidFormatException | InvalidDateTimeException e) {
                        throw new FileCorruptException();
                    }
                    break;
                case "E":
                    splitCommandArguments = data[3].split(" /at ", 2);
                    if (splitCommandArguments.length < 2) {
                        throw new FileCorruptException();
                    }
                    newTask = new Event(splitCommandArguments[0],
                            splitCommandArguments[1], isDone, recursiveTag);
                    taskList.add(newTask);
                    break;
                default:
                    throw new FileCorruptException();
                }
            }
            return taskList;
        } catch (FileCorruptException e) {
            System.out.println(e.getMessage());
            return new ArrayList<>();
        } catch (FileNotFoundException e) {
            System.out.printf("***** Filepath %s not found. Creating new list... *****\n", filePath);
            return new ArrayList<>();
        }
    }

    /**
     * Saves the current task list to the specified filePath.
     *
     * @param taskList The task list to be saved.
     */
    public void save(ArrayList<Task> taskList) {
        assert filePath.endsWith(".txt") : "File path needs to be a .txt file";
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

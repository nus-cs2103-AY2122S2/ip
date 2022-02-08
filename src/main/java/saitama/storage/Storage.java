package saitama.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import saitama.exceptions.FileCorruptException;
import saitama.exceptions.InvalidDateTimeException;
import saitama.exceptions.InvalidFormatException;
import saitama.parser.Parser;
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
                //split each line in text file into [task type, isDone, task description]
                String[] data = sc.nextLine().split(" ", 5);

                if (data.length < 5) {
                    throw new FileCorruptException();
                }

                //assign isDone
                boolean isDone;
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
                //assign last reset date
                LocalDate lastResetDate = LocalDate.parse(data[3]);

                //add task to array list
                String taskType = data[0];
                String taskArguments = data[4];
                switch(taskType) {
                case "T":
                    taskList.add(new ToDo(taskArguments, isDone, recursiveTag, lastResetDate));
                    break;
                case "D":
                    String[] splitTaskArguments = taskArguments.split(" /by ", 2);
                    if (splitTaskArguments.length < 2) {
                        throw new FileCorruptException();
                    }

                    String description = splitTaskArguments[0];
                    String deadlineString = splitTaskArguments[1];

                    try {
                        LocalDateTime deadline = Parser.processDateTime(deadlineString);
                        Task newTask = new Deadline(description,
                                deadline, isDone, recursiveTag, lastResetDate);
                        taskList.add(newTask);
                    } catch (InvalidFormatException | InvalidDateTimeException e) {
                        throw new FileCorruptException();
                    }
                    break;
                case "E":
                    splitTaskArguments = taskArguments.split(" /at ", 2);
                    if (splitTaskArguments.length < 2) {
                        throw new FileCorruptException();
                    }

                    description = splitTaskArguments[0];
                    String location = splitTaskArguments[1];
                    Task newTask = new Event(description,
                            location, isDone, recursiveTag, lastResetDate);
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
            System.out.printf("Filepath %s not found. Creating new list...\n", filePath);
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

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
import saitama.tags.RecurFrequency;
import saitama.tasks.Deadline;
import saitama.tasks.Event;
import saitama.tasks.Task;
import saitama.tasks.ToDo;

/**
 * Helps with the storing and loading of the task list data.
 */
public class Storage {

    public static final String TASK_CANNOT_SAVE_MESSAGE = "Task list cannot be saved!";
    private String filePath;

    /**
     * Initialises a storage object.
     *
     * @param filePath A file path to a text file where data will be stored
     */
    public Storage(String filePath) {
        assert filePath.endsWith(".txt") : "File path needs to be a .txt file";
        this.filePath = filePath;
    }

    /**
     * Loads a task list from the given text file stored in filePath.
     *
     * @return An ArrayList of tasks
     */
    public ArrayList<Task> load() {
        assert filePath.endsWith(".txt") : "File path needs to be a .txt file";

        ArrayList<Task> taskList = new ArrayList<>();
        File f = new File(filePath);

        try {
            Scanner sc = new Scanner(f);
            while (sc.hasNext()) {
                //split each line in text file into [task type, isDone, task description]
                String[] data = sc.nextLine().split(" ", 5);

                if (data.length < 5) {
                    throw new FileCorruptException();
                }

                String taskType = data[0];
                boolean isDone = getIsDone(data[1]);
                RecurFrequency recurFrequency = RecurFrequency.get(data[2]);
                LocalDate lastResetDate = Parser.processDate(data[3]);
                String taskArguments = data[4];

                addTaskToList(taskList, taskType, isDone, recurFrequency, lastResetDate, taskArguments);
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
     * Adds a task with the corresponding passed parameters to the given task list.
     *
     * @param taskList The task list which the task is to be added to
     * @param taskType The type of the task to be added
     * @param isDone The isDone status of the task to be added
     * @param recurFrequency The RecurFrequency of the task
     * @param lastResetDate The last reset date of the task
     * @param taskArguments The task's arguments
     * @throws FileCorruptException if the saved task has an invalid format
     */
    private void addTaskToList(ArrayList<Task> taskList, String taskType, boolean isDone, RecurFrequency recurFrequency,
                               LocalDate lastResetDate, String taskArguments) throws FileCorruptException {
        switch(taskType) {
        case "T":
            Task newTask = new ToDo(taskArguments, isDone, recurFrequency, lastResetDate);
            taskList.add(newTask);
            break;
        case "D":
            newTask = createDeadlineTask(isDone, recurFrequency, lastResetDate, taskArguments);
            taskList.add(newTask);
            break;
        case "E":
            newTask = createEventTask(isDone, recurFrequency, lastResetDate, taskArguments);
            taskList.add(newTask);
            break;
        default:
            throw new FileCorruptException();
        }
    }

    /**
     * Creates an event task based on the passed parameters.
     *
     * @param isDone The isDone status of the task
     * @param recurFrequency The RecurFrequency of the task
     * @param lastResetDate The last reset date of the task
     * @param taskArguments The task's arguments
     * @return A new event task based on the corresponding passed parameters
     * @throws FileCorruptException if the format of the saved event is invalid
     */
    private Task createEventTask(boolean isDone, RecurFrequency recurFrequency,
                                 LocalDate lastResetDate, String taskArguments) throws FileCorruptException {
        String[] splitTaskArguments = taskArguments.split(" /at ", 2);
        if (splitTaskArguments.length < 2) {
            throw new FileCorruptException();
        }

        String description = splitTaskArguments[0];
        String location = splitTaskArguments[1];
        Task newTask = new Event(description,
                location, isDone, recurFrequency, lastResetDate);
        return newTask;
    }

    /**
     * Creates a deadline task based on the passed parameters.
     *
     * @param isDone The isDone status of the task
     * @param recurFrequency The RecurFrequency of the task
     * @param lastResetDate The last reset date of the task
     * @param taskArguments The task's arguments
     * @return A new Deadline task based on the corresponding passed parameters
     * @throws FileCorruptException if the format of the saved deadline is invalid
     */
    private Task createDeadlineTask(boolean isDone, RecurFrequency recurFrequency, LocalDate lastResetDate,
                                    String taskArguments) throws FileCorruptException {
        String[] splitTaskArguments = taskArguments.split(" /by ", 2);
        if (splitTaskArguments.length < 2) {
            throw new FileCorruptException();
        }

        String description = splitTaskArguments[0];
        String deadlineString = splitTaskArguments[1];

        try {
            LocalDateTime deadline = Parser.processDateTime(deadlineString);
            Task newTask = new Deadline(description,
                    deadline, isDone, recurFrequency, lastResetDate);
            return newTask;
        } catch (InvalidFormatException | InvalidDateTimeException e) {
            throw new FileCorruptException();
        }
    }

    /**
     * Returns the boolean format of the saved isDone.
     *
     * @param data The isDone status of the task
     * @return The boolean format of the isDone status
     * @throws FileCorruptException if the saved isDone status is neither "true" nor "false"
     */
    private boolean getIsDone(String data) throws FileCorruptException {
        boolean isDone;
        switch(data) {
        case "false":
            isDone = false;
            break;
        case "true":
            isDone = true;
            break;
        default:
            throw new FileCorruptException();
        }
        return isDone;
    }

    /**
     * Saves the current task list to the specified filePath.
     *
     * @param taskList The task list to be saved
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
            System.out.println(TASK_CANNOT_SAVE_MESSAGE);
        }
    }
}

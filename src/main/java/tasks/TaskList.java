package tasks;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Supplier;

import date.time.DateTimeParser;
import exceptions.NoSuchTaskException;
import exceptions.SaveFileModifiedException;

/**
 * This class encapsulates a taskList.
 *
 * @author Ong Han Yang
 */
public class TaskList {
    /** Name of the saved file */
    private static String fileName = "saved-taskList.txt";

    /** Folder path to the saved file */
    private static String filePath = "data";

    /** The container for the items, implemented as an ArrayList */
    private final ArrayList<Task> list;

    /**
     * Constructs a taskList.
     */
    public TaskList() {
        this.list = new ArrayList<Task>();
    }

    /**
     * Adds an item into the taskList.
     *
     * @param item the item to be added.
     */
    public void add(Task item) {
        this.list.add(item);
        this.saveToFile();
    }

    /**
     * Adds multiple items at a time to the taskList.
     *
     * @param items all the items to be added.
     */
    public void add(Task... items) {
        this.list.addAll(List.of(items));
        this.saveToFile();
    }

    /**
     * Removes an item from the list of tasks.
     *
     * @param taskIndex the index of the item to remove.
     * @return the removed task.
     */
    public Task delete(int taskIndex) throws NoSuchTaskException {
        boolean isWithinIndex = taskIndex > -1 && taskIndex < this.list.size();
        if (!isWithinIndex) {
            throw new NoSuchTaskException("There is no task with index " + taskIndex);
        }
        Task output = this.list.remove(taskIndex);
        this.saveToFile();
        return output;
    }

    /**
     * Gets the length of the task list.
     *
     * @return an integer representing the length of the list.
     */
    public int length() {
        return this.list.size();
    }

    /**
     * Marks a task as done or undone.
     *
     * @param taskIndex the index of the task to be marked.
     * @param isDone whether the task is to be done or not.
     * @throws NoSuchTaskException when there is no such task with the index taskNum.
     */
    public void markTask(int taskIndex, boolean isDone) throws NoSuchTaskException {
        boolean isWithinIndex = taskIndex > 0 && taskIndex < this.list.size();
        if (!isWithinIndex) {
            throw new NoSuchTaskException("There is no task with index " + taskIndex);
        }
        this.list.get(taskIndex).markAs(isDone);
        this.saveToFile();
    }

    /**
     * Represents a stored task as a String.
     *
     * @param taskIndex the index of the task to be displayed.
     * @return String representation of the task.
     * @throws NoSuchTaskException when there is no such task with the index taskNum.
     */
    public String displayTask(int taskIndex) throws NoSuchTaskException {
        boolean isWithinIndex = taskIndex > 0 && taskIndex < this.list.size();
        if (!isWithinIndex) {
            throw new NoSuchTaskException("There is no task with index " + taskIndex);
        }
        return this.list.get(taskIndex).toString();
    }

    /**
     * Displays the taskList as a String.
     *
     * @return String representation of the tasks.TaskList.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(String.format("%d. %s\n", i + 1, list.get(i).toString()));
        }
        return sb.toString();
    }

    /**
     * Saves the taskList to the designated file at the filePath and fileName.
     */
    private void saveToFile() {

        try {
            String combinedFilePath = filePath + "/" + fileName;
            // creates the file if not there
            File f = new File(filePath);
            f.mkdir();
            File file = new File(combinedFilePath);
            file.createNewFile();
            // writes to the file the entirety of the toString().
            FileWriter fileWriter = new FileWriter(combinedFilePath);
            fileWriter.write(this.toString());
            fileWriter.close();

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Loads from the designated file to obtain a TaskList that is identical to one that
     * produced the file.
     *
     * @return an identical TaskList to one that produced the saved file.
     * @throws SaveFileModifiedException when the save file contains invalid symbols that could not
     *          have been from the string representation of a task, indicating external modification
     *          to the file.
     */
    public static TaskList loadFromFile() throws SaveFileModifiedException {
        TaskList output = new TaskList();
        try {
            File file = new File(filePath + "/" + fileName);
            Scanner sc = new Scanner(file);
            // parses each line
            while (sc.hasNextLine()) {
                String data = sc.nextLine();
                Task task = TaskList.parseLine(data);
                output.list.add(task);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            // do nothing
        }
        return output;
    }

    /**
     * Parses a String representation of a task to get back the original Task's details.
     *
     *  1. [T][X] sample task
     *  2. [D][ ] task (by: yyyy-mm-dd hh:mm)
     *  3. [E][ ] event (at: yyyy-mm-dd hh:mm, until: yyyy-mm-dd hh:mm)
     *
     *  An assumption is made where the time values are not modified to be  invalid.
     *  TODO: FIX THIS ASSUMPTION, maybe make this method cleaner
     *
     * @param input the String form of the Task.
     * @return the Task that corresponds to the input String.
     * @throws SaveFileModifiedException when the save file contains invalid symbols that could not
     *          have been from the string representation of a task, indicating external modification
     *          to the file.
     */
    private static Task parseLine(String input) throws SaveFileModifiedException {
        Task output;
        String desc;
        int indexAftNumber = input.indexOf("[");
        int indexAftDesc = input.lastIndexOf("(");

        // so that the function does not call immediately because of indexAftDesc
        Supplier<LocalDateTime> deadlineSupplier = () -> {
            String s = input.substring(indexAftDesc + 5, input.length() - 1);
            return DateTimeParser.parse(s);
        };

        // so that the function does not call immediately because of indexAftDesc
        Supplier<String> descSupplier = () -> input.substring
                (indexAftNumber + 7, indexAftDesc - 1);

        switch (input.charAt(indexAftNumber + 1)) { // the type of tasks
        case 'T': // To Do task
            desc = input.substring(indexAftNumber + 7);
            output = ToDoTask.of(desc);
            break;
        case 'D': // Deadline task
            output = DeadlineTask.of(descSupplier.get(), deadlineSupplier.get());
            break;
        case 'E': // Event task
            int indexAtUntil = input.indexOf(", until:");
            // start time
            String start = input.substring(indexAftDesc + 5, indexAtUntil);
            LocalDateTime startTime = DateTimeParser.parse(start);
            // end time
            String end = input.substring(indexAtUntil + 9, input.length() - 1);
            LocalDateTime endTime;

            // if the format is the shortened form of hh:mm
            if (!DateTimeParser.checkValidFormat(end) && end.length() == 5) {
                int hour = Integer.parseInt(end.substring(0, 2));
                int min = Integer.parseInt(end.substring(3, 5));
                endTime = LocalDateTime.of(startTime.toLocalDate(), LocalTime.of(hour, min));
            } else {
                endTime = DateTimeParser.parse(end);
            }

            output = EventTask.of(descSupplier.get(), startTime, endTime);
            break;
        default: //unknown task
            throw new SaveFileModifiedException("Unknown task type detected!");
        }
        switch (input.charAt(indexAftNumber + 4)) { // check if task is done
        case 'X':
            output.markAs(true);
            break;
        case ' ':
            output.markAs(false);
            break;
        default: //unknown state
            throw new SaveFileModifiedException("Unknown task state detected!");
        } // the Task output
        return output;
    }
}

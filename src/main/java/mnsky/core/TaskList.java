package mnsky.core;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Stack;

import mnsky.exceptions.MnskyException;
import mnsky.exceptions.MnskyInvalidParameterException;
import mnsky.task.Deadline;
import mnsky.task.Event;
import mnsky.task.Task;

public class TaskList {
    private static final int CMD_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int PARAMETER_INDEX = 2;
    private static final int MARK_INDEX = 3;
    private ArrayList<Task> tasks;
    private ArrayList<Task> oldTasks;
    private Stack<ArrayList<Task>> undoHistory;
    private Stack<ArrayList<Task>> redoHistory;
    private String storageDataReadError = "";

    /**
     * Creates a TaskList object. Retrieves the storage data if there are no issues with it.
     * @param storage The storage object, to be used for retrieving the storage data.
     */
    public TaskList(Storage storage) {
        tasks = new ArrayList<>();
        undoHistory = new Stack<>();
        redoHistory = new Stack<>();

        try {
            getStorageData(storage);
        } catch (MnskyException e) {
            tasks = new ArrayList<>();
            storageDataReadError = e.getMessage() + "\n";
        }
    }

    /**
     * Creates a new task based on the parameters.
     * @param name The name of the task.
     * @return The new task.
     */
    public Task addTask(String name) {
        Task task = new Task(name);
        tasks.add(task);
        return task;
    }

    /**
     * Creates a new event (a task with an "at" parameter included) based on the parameters.
     * @return The new event.
     */
    public Event addEvent(String name, String at) {
        String[] atSplit = at.split(" ");
        LocalDate atDate = null;
        LocalTime atTime = null;

        // Check to see if there are any dates or times in the first two words after the /by command
        if (atSplit.length >= 1) {
            atDate = parseDate(atSplit[0]);
            if (atDate != null && atSplit.length >= 2) {
                atTime = parseTime(atSplit[1]);
            } else {
                atTime = parseTime(atSplit[0]);
            }
        }

        Event event = new Event(name, at, atDate, atTime);
        tasks.add(event);
        return event;
    }

    /**
     * Creates a new deadline (a task with a "by" parameter included) based on the parameters.
     * @return The new deadline.
     */
    public Deadline addDeadline(String name, String by) {
        String[] bySplit = by.split(" ");
        LocalDate byDate = null;
        LocalTime byTime = null;

        // Check to see if there are any dates or times in the first two words after the /by command
        if (bySplit.length >= 1) {
            byDate = parseDate(bySplit[0]);
            if (byDate != null && bySplit.length >= 2) {
                byTime = parseTime(bySplit[1]);
            } else {
                byTime = parseTime(bySplit[0]);
            }
        }

        Deadline deadline = new Deadline(name, by, byDate, byTime);
        tasks.add(deadline);
        return deadline;
    }

    /**
     * Converts the given index from string to integer.
     * @param command The command that called this function.
     * @param stringIndex The index in string form.
     * @return The index in integer form.
     * @throws MnskyInvalidParameterException If the index is out of bounds or not an integer.
     */
    private int stringToIndex(String command, String stringIndex) throws MnskyInvalidParameterException {
        if (!stringIndex.matches("\\d+")) {
            throw new MnskyInvalidParameterException(command, "index");
        }

        int index = Integer.parseInt(stringIndex) - 1;
        if (index < 0 || index >= tasks.size()) {
            throw new MnskyInvalidParameterException(command, "index");
        }

        return index;
    }

    /**
     * Marks the task corresponding to the given index parameter as done, if it exists.
     * @param stringIndex The index of the task to mark in string format.
     * @return The task that was marked.
     * @throws MnskyInvalidParameterException If stringToIndex throws the exception.
     */
    public Task mark(String stringIndex) throws MnskyInvalidParameterException {
        int index = stringToIndex("mark", stringIndex);
        tasks.get(index).mark();
        return tasks.get(index);
    }

    /**
     * Unmarks the task corresponding to the given index parameter so it is not done, if it exists.
     * @param stringIndex The index of the task to unmark in string format.
     * @return The task that was unmarked.
     * @throws MnskyInvalidParameterException If stringToIndex throws the exception.
     */
    public Task unmark(String stringIndex) throws MnskyInvalidParameterException {
        int index = stringToIndex("unmark", stringIndex);
        tasks.get(index).unmark();
        return tasks.get(index);
    }

    /**
     * Deletes the task corresponding to the given index parameter, if it exists.
     * @param stringIndex The index of the task to delete in string format.
     * @return The task that was deleted.
     * @throws MnskyInvalidParameterException If stringToIndex throws the exception.
     */
    public Task delete(String stringIndex) throws MnskyInvalidParameterException {
        int index = stringToIndex("delete", stringIndex);
        Task deletedTask = tasks.get(index);
        tasks.remove(index);
        return deletedTask;
    }

    /**
     * Creates a LocalDate object based on the input string.
     * @param input The input string to create a LocalDate object from
     * @return The created LocalDate object if the input string is in a valid date format, null otherwise.
     */
    private LocalDate parseDate(String input) {
        try {
            return LocalDate.parse(input);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    /**
     * Creates a LocalTime object based on the input string.
     * @param input The input string to create a LocalTime object from
     * @return The created LocalTime object if the input string is in a valid time format, null otherwise.
     */
    private LocalTime parseTime(String input) {
        try {
            return LocalTime.parse(input);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    /**
     * Adds all the task in the storage data to the task list.
     * @param storage The storage object.
     * @throws MnskyException If there are any issues with converting the parsed storage data into tasks.
     */
    private void getStorageData(Storage storage) throws MnskyException {
        ArrayList<ArrayList<String>> taskList = Parser.parseStorageData(storage.readFromDataFile());
        for (ArrayList<String> task : taskList) {
            Task actualTask;
            assert task.size() > 3;
            switch (task.get(CMD_INDEX)) {
            case "todo":
                actualTask = addTask(task.get(NAME_INDEX));
                break;
            case "event":
                actualTask = addEvent(task.get(NAME_INDEX), task.get(PARAMETER_INDEX));
                break;
            case "deadline":
                actualTask = addDeadline(task.get(NAME_INDEX), task.get(PARAMETER_INDEX));
                break;
            default:
                throw new MnskyException("[MNSKY is having trouble rebuilding from the parsed storage data file...]");
            }

            if (actualTask != null) {
                if (task.get(MARK_INDEX).equals("X")) {
                    actualTask.mark();
                }
            }
        }
    }

    /**
     * Gets the error from reading storage data, if it exists.
     * @return The error from reading storage data.
     */
    public String getStorageDataReadError() {
        return storageDataReadError;
    }

    /**
     * Returns all the tasks which have the search term in their name.
     * @param searchTerm The search term used to find tasks.
     * @return All the tasks which have the search term in their name.
     */
    public ArrayList<String> find(String searchTerm) {
        ArrayList<String> foundTasks = new ArrayList<>();

        for (Task task : tasks) {
            if (task.getName().contains(searchTerm)) {
                foundTasks.add(task.toString());
            }
        }

        if (foundTasks.size() == 0) {
            foundTasks.add("[MNSKY couldn't find any tasks matching the search term.]");
        }

        return foundTasks;
    }

    /**
     * Gets a list of the storage data of all the tasks in the task list.
     * @return The list of storage data of all the tasks.
     */
    public ArrayList<String> getStorageDatas() {
        ArrayList<String> storageDatas = new ArrayList<>();
        for (Task task : tasks) {
            storageDatas.add(task.getStorageData());
        }
        return storageDatas;
    }

    /**
     * Gets the string representation of the list, which differs depending on if the list is empty or not.
     * @return The string representation of the list.
     */
    public ArrayList<String> getListStrings() {
        ArrayList<String> listStrings = new ArrayList<>();
        if (tasks.size() == 0) {
            listStrings.add("[MNSKY presents an empty task list.]");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                listStrings.add(String.format("%d. %s", i + 1, tasks.get(i)));
            }
        }

        return listStrings;
    }

    /**
     * Adds the current task list to the undo history and empties the redo history before updating the task list.
     */
    public void copyToOldTasks() {
        oldTasks = new ArrayList<>();
        for (Task task : tasks) {
            oldTasks.add(task.copy());
        }
    }

    /**
     * Adds the current task list to the undo history and empties the redo history before updating the task list.
     */
    public void addToUndoHistory() {
        undoHistory.push(oldTasks);

        while (!redoHistory.isEmpty()) {
            redoHistory.pop();
        }
    }

    /**
     * Undoes the most recent update done to the task list, if an update exists.
     */
    public void undo() throws MnskyException {
        if (undoHistory.isEmpty()) {
            throw new MnskyException("[MNSKY has nothing to undo!]");
        }

        redoHistory.push(tasks);
        tasks = undoHistory.pop();
    }

    /**
     * Redoes the most recent undoed update, if it exists.
     */
    public void redo() throws MnskyException {
        if (redoHistory.isEmpty()) {
            throw new MnskyException("[MNSKY has nothing to redo!]");
        }

        undoHistory.push(tasks);
        tasks = redoHistory.pop();
    }
}

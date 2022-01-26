package mnsky;

import mnsky.exceptions.MnskyException;
import mnsky.exceptions.MnskyInvalidParameterException;
import mnsky.task.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list;

    /**
     * Creates a TaskList object. Retrieves the storage data if there are no issues with it.
     * @param ui The ui object, to be used for printing an exception.
     * @param storage The storage object, to be used for retrieving the storage data.
     */
    public TaskList(Ui ui, Storage storage) {
        this.list = new ArrayList<>();

        try {
            this.getStorageData(storage);
        } catch (MnskyException e) {
            ui.printException(e);
            this.list = new ArrayList<>();
        }
    }

    /**
     * Creates a new task based on the parameters.
     * @param name The name of the task.
     * @return The new task.
     */
    public Task addTask(String name) {
        Task task = new Task(name);
        this.list.add(task);
        return task;
    }

    /**
     * Creates a new event (a task with an "at" parameter included) based on the parameters.
     * @throws MnskyException If the name or the at parameter is missing.
     * @return The new event.
     */
    public Event addEvent(String name, String at) throws MnskyException {
        String[] atSplit = at.split(" ");
        LocalDate atDate = null;
        LocalTime atTime = null;

        // Check to see if there are any dates or times in the first two words after the /by command
        if (atSplit.length >= 1) {
            atDate = this.parseDate(atSplit[0]);
            if (atDate != null && atSplit.length >= 2) {
                atTime = this.parseTime(atSplit[1]);
            } else {
                atTime = this.parseTime(atSplit[0]);
            }
        }

        Event event = new Event(name, at, atDate, atTime);
        this.list.add(event);
        return event;
    }

    /**
     * Creates a new deadline (a task with a "by" parameter included) based on the parameters.
     * @throws MnskyException If the name or the by parameter is missing.
     * @return The new deadline.
     */
    public Deadline addDeadline(String name, String by) {
        String[] bySplit = by.split(" ");
        LocalDate byDate = null;
        LocalTime byTime = null;

        // Check to see if there are any dates or times in the first two words after the /by command
        if (bySplit.length >= 1) {
            byDate = this.parseDate(bySplit[0]);
            if (byDate != null && bySplit.length >= 2) {
                byTime = this.parseTime(bySplit[1]);
            } else {
                byTime = this.parseTime(bySplit[0]);
            }
        }

        Deadline deadline = new Deadline(name, by, byDate, byTime);
        this.list.add(deadline);
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
        if (index < 0 || index >= this.list.size()) {
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
        int index = this.stringToIndex("mark", stringIndex);
        this.list.get(index).mark();
        return this.list.get(index);
    }

    /**
     * Unmarks the task corresponding to the given index parameter so it is not done, if it exists.
     * @param stringIndex The index of the task to unmark in string format.
     * @return The task that was unmarked.
     * @throws MnskyInvalidParameterException If stringToIndex throws the exception.
     */
    public Task unmark(String stringIndex) throws MnskyInvalidParameterException {
        int index = this.stringToIndex("unmark", stringIndex);
        this.list.get(index).unmark();
        return this.list.get(index);
    }

    /**
     * Deletes the task corresponding to the given index parameter, if it exists.
     * @param stringIndex The index of the task to delete in string format.
     * @return The task that was deleted.
     * @throws MnskyInvalidParameterException If stringToIndex throws the exception.
     */
    public Task delete(String stringIndex) throws MnskyInvalidParameterException  {
        int index = this.stringToIndex("delete", stringIndex);
        Task deletedTask = this.list.get(index);
        this.list.remove(index);
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
            switch (task.get(0)) {
                case "task":
                    actualTask = this.addTask(task.get(1));
                    break;

                case "event":
                    actualTask = this.addEvent(task.get(1), task.get(2));
                    break;

                case "deadline":
                    actualTask = this.addDeadline(task.get(1), task.get(2));
                    break;

                default:
                    throw new MnskyException("????");
            }

            if (actualTask != null) {
                if (task.get(3).equals("X")) {
                    actualTask.mark();
                }
            }
        }
    }

    /**
     * Gets the task list itself.
     * @return The task list.
     */
    public ArrayList<Task> getTaskList() {
        return this.list;
    }

    /**
     * Gets the string representation of the list, which differs depending on if the list is empty or not.
     * @return The string representation of the list.
     */
    @Override
    public String toString() {
        if (this.list.size() == 0) {
            return "[MNSKY presents an empty task list.]";
        } else {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < this.list.size(); i++) {
                sb.append(String.format("%d. %s\n", i + 1, this.list.get(i)));
            }
            return sb.toString();
        }
    }
}

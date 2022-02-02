package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import duke.exception.DukeException;

/**
 * Represents a list of tasks that needs to be done.
 */
public class TaskList {
    private final ArrayList<Task> tasks;
    private int numberOfTasks;

    /**
     * Class constructor.
     */
    public TaskList() {
        tasks = new ArrayList<>();
        numberOfTasks = 0;
    }

    /**
     * Populates this task list with the task data stored in disk.
     * 
     * @param data data read from storage.
     * @throws DukeException upon encountering incorrect format of data record.
     */
    public void populateWith(String[] data) throws DukeException {
        try {
            numberOfTasks = data.length;
            for (int i = 0; i < numberOfTasks; i++) {
                String tokens[] = data[i].split(",");
                if (tokens[0].equals("T")) {
                    tasks.add(new ToDo(tokens[1]));
                } else if (tokens[0].equals("E")) {
                    tasks.add(new Event(tokens[1], tokens[2]));
                } else if (tokens[0].equals("D")) {
                    tasks.add(new Deadline(tokens[1], LocalDate.parse(tokens[2])));
                } else {
                    throw new IllegalArgumentException();
                }
                if (tokens[3].equals("X")) {
                    tasks.get(i).mark();
                }
            }
        } catch (ArrayIndexOutOfBoundsException | IllegalArgumentException | DateTimeParseException e) {
            throw new DukeException("Encounter incorrect file format when loading tasks from storage");
        }
    }

    /**
     * Returns the representation of this task list formatted as the way it is to be stored in disk.
     * 
     * @return the representation of this task list formatted as the way it is to be stored in disk.
     */
    public String[] formatAsFileData() {
        String[] data = new String[numberOfTasks];
        for (int i = 0; i < numberOfTasks; i++) {
            data[i] = tasks.get(i).toFileFormat();
        }
        return data;
    }

    /**
     * Returns the number of tasks in the task list.
     * 
     * @return the number of tasks in the task list.
     */
    public int getNumberOfTasks() {
        return numberOfTasks;
    }

    /**
     * Checks if a task index is valid with respect to the task list.
     * 
     * @param index task index.
     * @return true if <code>index</code> is in the valid range, false otherwise.
     */
    public boolean isValidTaskIndex(int index) {
        return index >= 0 && index < numberOfTasks;
    }

    /**
     * Returns the string representation of a designated task.
     * 
     * @param index task index.
     * @return the string representation of a designated task.
     */
    public String getDescriptionOfTaskAtIndex(int index) {
        return tasks.get(index).toString();
    }

    /**
     * Adds a todo task to the task list.
     *
     * @param description description of the todo task.
     */
    public void addToDoTask(String description) {
        Task toDoTask = new ToDo(description);
        tasks.add(toDoTask);
        numberOfTasks++;
    }

    /**
     * Adds a deadline task to the task list.
     *
     * @param description description of the deadline task.
     * @param date deadline of the task expressed in date.
     */
    public void addDeadlineTask(String description, LocalDate date) {
        Task deadlineTask = new Deadline(description, date);
        tasks.add(deadlineTask);
        numberOfTasks++;
    }

    /**
     * Adds an event task to the task list.
     *
     * @param description description of the event task.
     * @param at the time of the event task.
     */
    public void addEventTask(String description, String at) {
        Task eventTask = new Event(description, at);
        tasks.add(eventTask);
        numberOfTasks++;
    }

    /**
     * Marks a designated task in the task list.
     *
     * @param taskIndex index of the task to be marked.
     * @throws DukeException when <code>taskIndex</code> is invalid.
     */
    public void markTask(int taskIndex) throws DukeException {
        if (!isValidTaskIndex(taskIndex)) {
            throw new DukeException("The task index provided is invalid");
        }
        
        tasks.get(taskIndex).mark();
    }

    /**
     * Unmarks a designated task in the task list.
     *
     * @param taskIndex index of the task to be unmarked.
     * @throws DukeException when <code>taskIndex</code> is invalid.
     */
    public void unmarkTask(int taskIndex) throws DukeException {
        if (!isValidTaskIndex(taskIndex)) {
            throw new DukeException("The task index provided is invalid");
        }
        
        tasks.get(taskIndex).unmark();
    }

    /**
     * Deletes a designated task from the task list.
     * 
     * @param taskIndex index of the task to be deleted.
     * @return the task to be deleted.
     * @throws DukeException when <code>taskIndex</code> is invalid.
     */
    public Task deleteTask(int taskIndex) throws DukeException {
        if (!isValidTaskIndex(taskIndex)) {
            throw new DukeException("The task index provided is invalid");
        }
        
        Task taskToBeDeleted = tasks.get(taskIndex);
        tasks.remove(taskIndex);
        numberOfTasks--;
        
        return taskToBeDeleted;
    }

    /**
     * Removes all tasks from the task list.
     */
    public void clear() {
        while (numberOfTasks != 0) {
            tasks.remove(numberOfTasks - 1);
            numberOfTasks--;
        }
    }
}

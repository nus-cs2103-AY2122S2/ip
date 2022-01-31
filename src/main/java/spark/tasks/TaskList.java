package spark.tasks;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import spark.exceptions.fileexceptions.FileException;
import spark.exceptions.fileexceptions.TaskDecodingException;
import spark.exceptions.taskmodificationexceptions.TaskAlreadyMarked;
import spark.exceptions.taskmodificationexceptions.TaskAlreadyUnMarked;
import spark.exceptions.taskmodificationexceptions.TaskNotFoundException;
import spark.tasks.tasktypes.Deadline;
import spark.tasks.tasktypes.Event;
import spark.tasks.tasktypes.Task;
import spark.tasks.tasktypes.Todo;

/**
 * Encapsulates a list of Tasks.
 *
 * @throws FileException if the save-file on the user's hard-disk cannot be accessed or modified.
 * @throws TaskDecodingException if the encoded data on the save-file cannot be read.
 */
public class TaskList {
    protected List<Task> tasks;
    protected Task lastAddedTask;
    protected Task lastDeletedTask;
    protected Task lastModifiedTask;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a Todo with a non-empty title.
     */
    public void addToDo(String title) {
        Todo toDo = new Todo(title);
        tasks.add(toDo);

        this.lastAddedTask = toDo;
    }

    /**
     * Adds a Deadline with a non-empty title and date.
     */
    public void addDeadline(String title, LocalDateTime by) {
        Deadline deadline = new Deadline(title, by);
        tasks.add(deadline);

        this.lastAddedTask = deadline;
    }

    /**
     * Adds an Event with a non-empty title and date.
     */
    public void addEvent(String title, LocalDateTime at) {
        Event event = new Event(title, at);
        tasks.add(event);

        this.lastAddedTask = event;
    }

    /**
     * Marks an incomplete Task as complete.
     *
     * @throws TaskNotFoundException  if the specified-index does not match any Task.
     * @throws TaskAlreadyMarked      if the Task is already complete.
     */
    public void markTask(int index) throws TaskNotFoundException, TaskAlreadyMarked {
        Task t = getTaskByOneIndex(index);
        t.mark();

        this.lastModifiedTask = t;
    }

    /**
     * Marks a completed Task as incomplete.
     *
     * @throws TaskNotFoundException if the specified-index does not match any Task.
     * @throws TaskAlreadyUnMarked   if the Task is not complete.
     */
    public void unMarkTask(int index) throws TaskNotFoundException, TaskAlreadyUnMarked {

        Task t = getTaskByOneIndex(index);
        t.unMark();

        this.lastModifiedTask = t;
    }

    /**
     * Permanently removes a Task from the user's list by a specified index.
     *
     * @throws TaskNotFoundException if the specified-index does not match any Task.
     */
    public void deleteTask(int index) throws TaskNotFoundException {
        try {
            this.lastDeletedTask = tasks.get(index - 1);
            tasks.remove(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new TaskNotFoundException();
        }
    }

    /**
     * Displays all Tasks in the user's list.
     */
    public String getTaskList() {
        // if there are no tasks, inform the user
        if (tasks.size() == 0) {
            return "No tasks found! (trust me, I've looked everywhere)";
        } else {
            StringBuilder listOfTasks = new StringBuilder();

            listOfTasks.append("\n"
                    + "█▄█ █▀█ █░█ █▀█   ▀█▀ ▄▀█ █▀ █▄▀ █▀\n"
                    + "░█░ █▄█ █▄█ █▀▄   ░█░ █▀█ ▄█ █░█ ▄█\n\n");

            for (int i = 0; i < tasks.size(); i++) {
                listOfTasks.append(String.format("    %d. %s\n", i + 1, tasks.get(i)));
            }

            return listOfTasks.toString();
        }
    }

    /**
     * Returns a list of Tasks with titles that contain the given keyword(s).
     */
    public List<Task> findTask(String searchTerm) {
        List<Task> results = new ArrayList<>();
        for (Task t : tasks) {
            if (t.getTitle().contains(searchTerm)) {
                results.add(t);
            }
        }

        return results;
    }

    /**
     * Returns the last task that was added by the user.
     *
     * @return a reference to the last Task added.
     */
    public Task getLastAddedTask() {
        return this.lastAddedTask;
    }

    /**
     * Returns the last task that was deleted by the user.
     *
     * @return a reference to the last Task deleted.
     */
    public Task getLastDeletedTask() {
        return this.lastDeletedTask;
    }

    /**
     * Returns the last task that was modified by the user.
     *
     * @return a reference to the last Task modified.
     */
    public Task getLastModifiedTask() {
        return this.lastModifiedTask;
    }

    /**
     * Returns an encoded list of Tasks.
     *
     * @return a String containing the encoded version of a list of Tasks.
     */
    public String encodeTasks() {
        StringBuilder encodedTasks = new StringBuilder();

        for (Task t : tasks) {
            encodedTasks.append(t.encodeTask() + System.lineSeparator());
        }

        return encodedTasks.toString();
    }

    private Task getTaskByOneIndex(int index) throws TaskNotFoundException {
        try {
            return tasks.get(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new TaskNotFoundException();
        }
    }
}

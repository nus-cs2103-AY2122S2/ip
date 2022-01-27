package spark.tasks;

import spark.exceptions.fileexceptions.FileException;
import spark.exceptions.fileexceptions.TaskDecodingException;
import spark.exceptions.formatexceptions.EmptyKeywordException;
import spark.exceptions.taskmodificationexceptions.InvalidTaskIdException;
import spark.exceptions.taskmodificationexceptions.TaskAlreadyMarked;
import spark.exceptions.taskmodificationexceptions.TaskAlreadyUnMarked;
import spark.exceptions.taskmodificationexceptions.TaskNotFoundException;
import spark.tasks.tasktypes.Deadline;
import spark.tasks.tasktypes.Event;
import spark.tasks.tasktypes.Task;
import spark.tasks.tasktypes.ToDo;

import java.util.List;
import java.util.ArrayList;

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
     * Adds a ToDo.
     *
     * @throws FileException
     */
    public void addToDo(String title) throws FileException {
        ToDo toDo = new ToDo(title);
        tasks.add(toDo);

        this.lastAddedTask = toDo;
    }

    /**
     * Adds a ToDo with a given deadline.
     *
     *
     */
    public void addDeadline(String title, String by) {
        Deadline deadline = new Deadline(title, by);
        tasks.add(deadline);

        this.lastAddedTask = deadline;
    }

    /**
     * Adds an Event with a specified date and time at which it occurs.
     *
     * @throws FileException
     */
    public void addEvent(String title, String at) {

        Event event = new Event(title, at);
        tasks.add(event);

        this.lastAddedTask = event;
    }

    /**
     * Marks an incomplete Task as complete.
     *
     * @throws TaskNotFoundException
     * @throws TaskAlreadyMarked
     * @throws InvalidTaskIdException
     * @throws FileException
     */
    public void markTask(int index) throws TaskNotFoundException,
            TaskAlreadyMarked, InvalidTaskIdException, FileException {
        Task t = getTaskByOneIndex(index);
        t.mark();

        this.lastModifiedTask = t;
    }

    /**
     * Marks a completed Task as incomplete.
     *
     * @throws TaskNotFoundException
     * @throws TaskAlreadyUnMarked
     * @throws InvalidTaskIdException
     * @throws FileException
     */
    public void unMarkTask(int index) throws TaskNotFoundException,
            TaskAlreadyUnMarked, InvalidTaskIdException, FileException {

        Task t = getTaskByOneIndex(index);
        t.unMark();

        this.lastModifiedTask = t;
    }

    /**
     * Permanently removes a Task from the user's list by a specified index.
     *
     * @throws TaskNotFoundException
     * @throws InvalidTaskIdException
     * @throws FileException
     */
    public void deleteTask(int index) throws TaskNotFoundException,
            InvalidTaskIdException, FileException {

        try {
            this.lastDeletedTask = tasks.get(index-1);
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

            listOfTasks.append("\n" +
                    "█▄█ █▀█ █░█ █▀█   ▀█▀ ▄▀█ █▀ █▄▀ █▀\n" +
                    "░█░ █▄█ █▄█ █▀▄   ░█░ █▀█ ▄█ █░█ ▄█\n\n");

            for (int i = 0; i < tasks.size(); i++) {
                listOfTasks.append(String.format("    %d. %s\n", i + 1, tasks.get(i)));
            }

            return listOfTasks.toString();
        }
    }

    /**
     * Returns a list of Tasks with titles that contain the given keyword(s).
     *
     * @throws EmptyKeywordException
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

    public Task getLastAddedTask() {
        return this.lastAddedTask;
    }

    public Task getLastDeletedTask() {
        return this.lastDeletedTask;
    }

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

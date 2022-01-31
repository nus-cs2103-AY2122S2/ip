package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * TaskList manages the usage of Task.
 */
public class TaskList {
    private ArrayList<Task> tasks;
    private Storage storage;

    /**
     * Takes in a Storage to save or load data.
     * Returns an instance of TaskList.
     */
    public TaskList(Storage storage) {
        this.tasks = new ArrayList<>();
        this.storage = storage;
    }

    /**
     * Loads data from storage.
     */
    public void load() throws DukeException {
        try {
            storage.load(this);
        } catch (FileNotFoundException e) {
            throw new DukeException.DukeIoException();
        }
    }

    /**
     * Saves data to storage.
     */
    public void save() throws DukeException {
        try {
            this.storage.save(tasks);
        } catch (IOException e) {
            throw new DukeException.DukeIoException();
        }
    }

    /**
     * Create task based on task's type.
     *
     * @param description of the task.
     * @param time        of the task for Deadline and Event.
     * @param t           type of task.
     */
    public void addTask(String description, LocalDateTime time, Task.Type t) {
        switch (t) {
        case TODO:
            this.tasks.add(new Todo(description));
            break;
        case DEADLINE:
            this.tasks.add(new Deadline(description, time));
            break;
        case EVENT:
            this.tasks.add(new Event(description, time));
            break;
        default:
            break;
        }
    }

    /**
     * Create task for Todo.
     *
     * @param description of the task.
     * @param t           the type of the task.
     */
    public void addTask(String description, Task.Type t) {
        addTask(description, null, t);
    }

    public String getTaskDescription(int taskId) {
        return this.tasks.get(taskId - 1).toString();
    }

    /**
     * Mark a task as done.
     *
     * @param taskId index of the task on the list.
     */
    public void markTask(int taskId) {
        this.tasks.get(taskId - 1).mark();
    }

    public int size() {
        return this.tasks.size();
    }

    /**
     * UnMark a task as not done.
     *
     * @param taskId index of the task on the list.
     */
    public void unMarkTask(int taskId) {
        this.tasks.get(taskId - 1).unMark();
    }

    /**
     * Delete a task on the list.
     *
     * @param taskId index of the task on the list.
     */
    public void delete(int taskId) {
        this.tasks.remove(taskId - 1);
    }

    /**
     * Find all the tasks that contains a matching description.
     *
     * @param description of the tasks to find.
     * @return string of tasks if found else null.
     */
    public String find(String description) {
        ArrayList<Task> results = new ArrayList<>();

        for (Task task : this.tasks) {
            String text = task.getDescription();
            String[] textArray = text.split("\\s+");
            for (String toMatch : textArray) {
                if (toMatch.equals(description)) {
                    results.add(task);
                }
            }
        }
        return (results.size() == 0) ? null : formatTaskList(results);
    }

    @Override
    public String toString() {
        return formatTaskList(this.tasks);
    }

    private String formatTaskList(ArrayList<Task> tasks) {
        String str = "";
        for (int i = 1; i <= tasks.size(); i++) {
            str += i + "." + tasks.get(i - 1) + "\n";
        }
        return str;
    }
}

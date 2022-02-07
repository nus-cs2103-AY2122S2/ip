package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.stream.Collectors;

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
     * Creates task based on task's type.
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
     * Creates task for Todo.
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
     * Marks a task as done.
     *
     * @param taskId index of the task on the list.
     */
    public void markTask(int taskId) throws DukeException {
        Task task = this.tasks.get(taskId - 1);
        assert task != null : "task must not be null";
        if (task.getStatusIcon().equals("X")) {
            throw new DukeException.DukeMarkedException();
        } else {
            task.mark();
        }
    }

    public int size() {
        return this.tasks.size();
    }

    /**
     * Unmarks a task as not done.
     *
     * @param taskId index of the task on the list.
     */
    public void unmarkTask(int taskId) throws DukeException {
        Task task = this.tasks.get(taskId - 1);
        assert task != null : "task must not be null";
        if (!task.getStatusIcon().equals("X")) {
            throw new DukeException.DukeUnMarkException();
        } else {
            task.unmark();
        }
    }

    /**
     * Deletes a task on the list.
     *
     * @param taskId index of the task on the list.
     */
    public void delete(int taskId) {
        assert tasks.get(taskId - 1) != null : "task must not be null";
        this.tasks.remove(taskId - 1);
    }

    /**
     * Find all the tasks that contains a matching description.
     *
     * @param description of the tasks to find.
     * @return string of tasks if found else null.
     */
    public String find(String description) {
        ArrayList<Task> results = tasks.stream()
                .filter(task -> {
                    assert task != null : "task must not be null";
                    String text = task.getDescription();
                    String[] textArray = text.split("\\s+");
                    for (String toMatch : textArray) {
                        if (toMatch.equals(description)) {
                            return true;
                        }
                    }
                    return false;
                }).collect(Collectors.toCollection(ArrayList::new));
        return (results.size() == 0) ? null : formatTaskList(results);
    }

    @Override
    public String toString() {
        return formatTaskList(this.tasks);
    }

    private String formatTaskList(ArrayList<Task> tasks) {
        assert tasks.size() > 0 : "list should not be empty";
        String str = "";
        for (int i = 1; i <= tasks.size(); i++) {
            assert tasks.get(i - 1) != null : "task must not be null";
            str += i + "." + tasks.get(i - 1) + "\n";
        }
        return str;
    }
}

package seedu.storage;

import java.util.ArrayList;

import seedu.duke.DukeException;
import seedu.task.Task;

public class TaskList {

    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.tasks = new ArrayList<>(taskList);
    }

    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Removes task from list specified by index.
     * @param idx Index of task to be deleted.
     * @return Task to be deleted.
     * @throws DukeException Index not in range of 0 to tasks size.
     */
    public Task remove(int idx) throws DukeException {
        Task t = get(idx);
        tasks.remove(idx);
        return t;
    }

    public Task get(int idx) throws DukeException {
        try {
            return tasks.get(idx);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Task does not exist.");
        }
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Finds all instances where search string is in task description.
     * @param search String to be matched in Task description.
     */
    public String find(String search) {
        StringBuilder s = new StringBuilder("Found:\n");

        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getDescription().contains(search)) {
                s.append("\t\t");
                s.append(i + 1);
                s.append(". ");
                s.append(tasks.get(i).toString());
                s.append("\n");
            }
        }

        return s.toString();
    }

    @Override
    public String toString() {
        if (!tasks.isEmpty()) {
            StringBuilder out = new StringBuilder("This List:\n");
            for (int i = 0; i < tasks.size(); i++) {
                out.append("\t\t");
                out.append(i + 1);
                out.append(". ");
                out.append(tasks.get(i).toString());
                out.append("\n");
            }
            return out.toString().trim();
        } else {
            return "Empty list";
        }
    }
}

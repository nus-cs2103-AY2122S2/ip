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
    public void find(String search) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getDescription().contains(search)) {
                System.out.println(i + 1 + ". " + tasks.get(i).toString());
            }
        }
    }

    @Override
    public String toString() {
        if (!tasks.isEmpty()) {
            StringBuilder out = new StringBuilder();
            for (int i = 0; i < tasks.size(); i++) {
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

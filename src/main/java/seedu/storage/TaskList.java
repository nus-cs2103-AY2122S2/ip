package seedu.storage;

import java.util.ArrayList;
import java.util.Collections;

import seedu.duke.DukeException;
import seedu.task.Task;

public class TaskList {

    private final ArrayList<Task> TASKS;

    public TaskList() {
        this.TASKS = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.TASKS = new ArrayList<>(taskList);
    }

    public void add(Task task) {
        TASKS.add(task);
    }

    public void sort() {
        Collections.sort(TASKS);
    }

    public ArrayList<Task> getTasks() {
        return TASKS;
    }

    public Task remove(int idx) throws DukeException {
        Task t = get(idx);
        TASKS.remove(idx);
        return t;
    }

    public Task get(int idx) throws DukeException {
        try {
            return TASKS.get(idx);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Task does not exist.");
        }
    }

    public String find(String search) {
        StringBuilder s = new StringBuilder("Found:\n");

        for (int i = 0; i < TASKS.size(); i++) {
            if (TASKS.get(i).getDescription().contains(search)) {
                s.append("  ");
                s.append(i + 1);
                s.append(". ");
                s.append(TASKS.get(i).toString());
                s.append("\n");
            }
        }

        return s.toString().trim();
    }

    @Override
    public String toString() {

        if (TASKS.isEmpty()) {
            return "Empty list";
        }

        StringBuilder out = new StringBuilder("Your List:\n");

        for (int i = 0; i < TASKS.size(); i++) {
            out.append("  ");
            out.append(i + 1);
            out.append(". ");
            out.append(TASKS.get(i).toString());
            out.append("\n");
        }

        return out.toString().trim();
    }
}

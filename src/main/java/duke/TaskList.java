package duke;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TaskList {
    protected List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> list) {
        this.tasks = list;
    }

    public Task getTaskAtIndex(int index) throws DukeException {
        try {
            return tasks.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Pardon me, but the provided index does not exist in your list.");
        }
    }

    public void addTask(Task t) {
        this.tasks.add(t);
    }

    public Task removeTask(int index) throws DukeException {
        this.getTaskAtIndex(index); //make sure task exists

        return this.tasks.remove(index);
    }

    /**
     * Finds the tasks that match a given keyword.
     * Note: implementation is subject to change as it is not very efficient.
     * @param keyword the string to compare with
     * @return list of tasks that match the keyword in string form
     */
    public String find(String keyword) {
        StringBuilder foundStrings = new StringBuilder("");
        for (int i = 0; i < tasks.size(); i++) {
            String currDescription = tasks.get(i).getDescription().toLowerCase();

            if (currDescription.contains(keyword)) {
                foundStrings.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
            }
        }
        return foundStrings.toString();
    }

    public int getTasksCount() {
        return tasks.size();
    }

    public List<Task> getList() {
        return this.tasks;
    }

    public String toString() {
        StringBuilder listOfTasks = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            listOfTasks.append(i + 1).append(".").append(tasks.get(i)).append("\n");
        }

        return listOfTasks.toString();
    }
}

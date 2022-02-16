package kenobi.util;

import kenobi.task.Task;

import java.util.ArrayList;


/**
 * The TaskList encapsulates a list of Task objects.
 */
public class TaskList extends ArrayList<Task> {
    /**
     * Marks the task in the specified index as done.
     *
     * @param index The index of the task to be marked as done.
     * @return The task marked as done.
     * @throws IndexOutOfBoundsException if the specified index is out of bounds.
     */
    public Task markTaskAsDone(int index) throws IndexOutOfBoundsException {
        return get(index).markAsDone();
    }

    /**
     * Marks the task in the specified index as undone.
     *
     * @param index The index of the task to be marked as undone.
     * @return The task marked as undone.
     * @throws IndexOutOfBoundsException if the specified index is out of bounds.
     */
    public Task markTaskAsUndone(int index) throws IndexOutOfBoundsException {
        return get(index).markAsUndone();
    }

    /**
     * Searches tasks by filtering out the tasks that do not contain the search term in their name.
     *
      * @param searchTerm The term that will be used to filter the tasks in the TaskList.
     * @return a filtered shallow copy of the TaskList.
     */
    public TaskList search(String searchTerm) {
        TaskList tasks = (TaskList) clone();
        tasks.removeIf(e -> !e.hasWord(searchTerm));

        return tasks;
    }

    /**
     * Returns a base-1 numbered list of the Tasks in this list.
     *
     * @return a string of the numbered list of tasks.
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < super.size(); i++) {
            str.append(String.format("%d. %s\n", i + 1, super.get(i)));
        }

        return str.toString();
    }
}


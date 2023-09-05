package duke.task;

import duke.exception.DukeException;
import java.util.ArrayList;

/**
 * Contains list of tasks and operations to add/delete/mark/unmark tasks in the list
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Gets list of tasks
     *
     * @return list of tasks
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }


    /**
     * Marks task on the list as done
     *
     * @param number task number of task on the list to be marked as done
     */
    public void markTask(int number) {
        assert number >= 0 && number <= tasks.size();
        tasks.get(number).markTaskDone();
    }

    /**
     * Marks task on the list as undone
     *
     * @param number task number of task on the list to be unmarked
     */
    public void unmarkTask(int number) {
        assert number >= 0 && number <= tasks.size();
        tasks.get(number).unmarkTaskDone();
    }

    /**
     * Deletes task from the list
     *
     * @param number task number of task to be deleted from the list
     */
    public void deleteTask(int number) {
        assert number >= 0 && number <= tasks.size();
        tasks.remove(number);
    }

    /**
     * Adds task to the list
     *
     * @param task task to be added to the list
     */
    public void addTask(Task task) {
        assert task != null;
        tasks.add(task);
    }

    /**
     * Returns list of tasks with specified keyword
     *
     * @param keyword keyword to be searched
     * @return list of matching tasks
     */
    public ArrayList<Task> findTasks(String keyword) {
        assert !keyword.isBlank();
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task: tasks) {
            if (task.getDescription().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }

    /**
     * Updates section of task with content specified
     *
     * @param number task number of task to be updated
     * @param section section of task to be updated
     * @param content content to be placed in that section
     * @throws DukeException if section is invalid
     */
    public void updateTask(int number, String section, String content) throws DukeException {
        assert number >= 0 && number <= tasks.size();
        if (section.equals("description")) {
            tasks.get(number).updateDescription(content);
        } else if (section.equals("date")) {
            tasks.get(number).updateDate(content);
        } else {
            throw new DukeException("The section you have inputted is invalid.");
        }
    }
}


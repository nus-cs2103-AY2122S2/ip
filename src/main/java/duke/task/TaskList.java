package duke.task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Encapsulates a list of tasks recorded by Duke.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Initialises an empty task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    private TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to this task list.
     *
     * @param task the task to be added.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Deletes a task, specified by an index,
     * from this task list.
     *
     * @param index the index of the task to be deleted (starting from 1).
     */
    public void deleteTask(int index) {
        this.tasks.remove(index - 1);
    }

    /**
     * Returns a task specified by a given index.
     *
     * @param index the index of the task (starting from 1)
     * @return
     */
    public Task getTask(int index) {
        return this.tasks.get(index - 1);
    }

    /**
     * Gets all tasks that are scheduled within a specified number
     * of days from today, including tasks that are scheduled today.
     *
     * @param daysFromToday the number of days from today.
     * @return a task list containing all such tasks.
     */
    public TaskList getUpcomingTasks(int daysFromToday) {
        LocalDate today = LocalDate.now();
        LocalDate endDate = today.plusDays(daysFromToday + 1);

        ArrayList upcomingTasks = tasks.stream()
                .filter(task -> task instanceof ScheduledTask)
                .map(scheduledTask -> (ScheduledTask) scheduledTask)
                .filter(scheduledTask -> !(scheduledTask.isEarlierThan(today)))
                .filter(scheduledTask -> scheduledTask.isEarlierThan(endDate))
                .sorted()
                .collect(Collectors.toCollection(ArrayList::new));
        return new TaskList(upcomingTasks);
    }

    /**
     * Returns the length of this task list.
     *
     * @return the length of this task list.
     */
    public int getLength() {
        return this.tasks.size();
    }

    /**
     * Returns the string representation of this task list.
     *
     * @return the string representation of this task list.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }
        return tasks.size() == 0 ? "Task list is empty.\n" : sb.toString();
    }
}

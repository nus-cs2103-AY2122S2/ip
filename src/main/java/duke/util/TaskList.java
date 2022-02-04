package duke.util;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Utility class to keep track of list of added task.
 */
public class TaskList {

    private static final String TASK_ADDED = "Task Added, arrgh:\n";
    private final ArrayList<Task> taskList;
    private int taskCount = 0;
    private Ui ui = new Ui();

    /**
     * Creates a new instance of tasklist that creates a new list of tasks.
     */
    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * Creates a new instance of tasklist that loads the saved list of tasks.
     */
    public TaskList(Scanner sc) throws FileNotFoundException, DukeException {
        this.taskList = new ArrayList<>();
        readFromFile(sc);
    }

    /**
     * Loads the saved list of tasks
     */
    private void readFromFile(Scanner sc) throws DukeException {
        while (sc.hasNext()) {
            String[] savedData = sc.nextLine().split(" \\| ");
            String taskType = savedData[0];
            Task task;
            taskCount++;
            switch (taskType) {
            case "T":
                task = new Todo(savedData[2]);
                break;
            case "E":
                if (savedData.length == 3) {
                    task = new Event(savedData[2], savedData[3]);
                } else { // If time is included for the task
                    task = new Event(savedData[2], savedData[3] + " " + savedData[4]);
                }
                break;
            case "D":
                if (savedData.length == 3) {
                    task = new Deadline(savedData[2], savedData[3]);
                } else { // If time is included for the task
                    task = new Deadline(savedData[2], savedData[3] + " " + savedData[4]);
                }
                break;
            default:
                task = new Todo("");
            }
            if (savedData[1].equals("1")) {
                task.markAsDone();
            }
            taskList.add(task);
        }
    }

    /**
     * Adds a task into list of tasks.
     *
     * @param task List of tasks.
     */
    public String add(Task task) {
        StringBuilder str = new StringBuilder();
        if (task != null) {
            this.taskList.add(task);
            this.taskCount++;
            str.append(TASK_ADDED + taskList.get(this.taskCount - 1).toString());
            str.append("\nNow you have " + this.taskCount
                    + " tasks in your task list arrr, better get workin' aye!\n");
            str.append(ui.requestNextCommand());
            return str.toString();
        } else {
            str.append(ui.showError("\tTask is invalid matey :-(, please try again!\n"));
            return str.toString();
        }
    }

    /**
     * Deletes a task from list of tasks.
     *
     * @param taskIndex 0-based index task number to be deleted.
     */
    public String delete(int taskIndex) {
        StringBuilder str = new StringBuilder();
        try {
            Task task = taskList.get(taskIndex);
            this.taskList.remove(taskIndex);
            this.taskCount--;
            str.append(ui.deleteTask() + task+ "\n" + ui.requestNextCommand());
            return str.toString();
        } catch (IndexOutOfBoundsException e) {
            ui.showError("\tAin't nuthin' to be deleted here matey! :-(\n");
            return str.toString();
        }
    }

    /**
     * Finds a task or tasks that matches the given keyword
     *
     * @param keyword keyword to be found in list of tasks
     */
    public String findTaskMatchingKeyword(String keyword) {

        StringBuilder output = new StringBuilder();
        List<Task> matchingTask = new ArrayList<>();
        matchingTask = this.taskList.stream()
                .filter(str -> str.getTaskDescription().trim().contains(keyword))
                .collect(Collectors.toList());

        // To be modified
        output.append(ui.showMatchingTasks());
        for (int i = 0; i < matchingTask.size(); i++) {
            output.append((i + 1) + "." + matchingTask.get(i) + "\n");
        }
        output.append(ui.requestNextCommand());

        return output.toString();
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return Number of tasks in the list.
     */
    public int getCount() {
        return this.taskCount;
    }

    public ArrayList<Task> getCurrentList() {
        return this.taskList;
    }

    /**
     * Returns the tasks at given index
     *
     * @param taskIndex 0-based index of task number.
     * @return Task at given index.
     */
    public String getTask(int taskIndex) {
        return taskList.get(taskIndex).getTaskDescription();
    }

    /**
     * Mark a task in list of tasks as done
     *
     * @param taskIndex 0-based index of task number.
     */
    public String markTask(int taskIndex) {
        StringBuilder str = new StringBuilder();
        Task curr = taskList.get(taskIndex);
        curr.markAsDone();
        str.append(ui.markAsDone() + curr + "\n" + ui.requestNextCommand());

        return str.toString();
    }

    /**
     * Mark a task in list of tasks as not done
     *
     * @param taskIndex 0-based index of task number.
     */
    public String unmarkTask(int taskIndex) {
        StringBuilder str = new StringBuilder();
        Task curr = taskList.get(taskIndex);
        curr.markAsUndone();
        str.append(ui.markAsUndone() + curr + "\n" + ui.requestNextCommand());

        return str.toString();
    }

    /**
     * Prints out the list of outstanding tasks
     */
    public String printTaskList() {
        StringBuilder str = new StringBuilder();
        str.append(ui.printTaskList());
        for (int i = 0; i < this.taskCount; i++) {
            str.append((i + 1) + "." + taskList.get(i).toString() + "\n");
        }
        str.append(ui.requestNextCommand());

        return str.toString();
    }

}

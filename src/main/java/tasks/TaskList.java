package tasks;
import duke.*;

import duke.Storage;

import java.util.ArrayList;

public class TaskList {

    private static ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Instantiates a taskList with a list of tasks
     * @param tasks list of tasks
     *
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Instantiates a taskList
     */
    public TaskList() { }

    /**
     * Method that takes in a taskId and deletes the task from the tasks arraylist
     * @param taskId id of task
     * @throws DukeException if the task cannot be found in the list
     */
    public static void deleteTask(int taskId) throws DukeException {
        Task preview = tasks.get(taskId - 1);
        tasks.remove(taskId - 1);
        Storage.writeToDukeFile();
        System.out.println("Otsukaresamadeshita! You have finally completed one task.\n" + preview);
    }

    /**
     * Method that takes in a taskId and marks it as done in the tasks arraylist
     * @param taskId id of task
     * @param mark boolean indicating whether to mark the task as done
     * @throws DukeException if the task has yet to be done
     */
    public static void markTask(int taskId, boolean mark) throws DukeException {
        Task toSet = tasks.get(taskId - 1);
        if (mark) {
            toSet.markIsDone();
            tasks.set(taskId - 1, toSet);
            Storage.writeToDukeFile();
            System.out.println("Sugoi! I have marked this task as done!\n" + tasks.get(taskId - 1).toString());
        } else {
            if (toSet.isDone) {
                toSet.markUndone();
                tasks.set(taskId - 1, toSet);
                Storage.writeToDukeFile();
                System.out.println("Daijoubu! I have unmarked this task for you!\n" + tasks.get(taskId - 1).toString());
            } else {
                throw new DukeException("This task has yet to be done!");
            }
        }
    }

    /**
     * Method that takes in a task and adds it to tasks
     * @param task task object
     * @throws DukeException if unable to add task to tasks
     */
    public static void addTask(Task task) throws DukeException {
        System.out.println("Added as per your request: " + task);
        tasks.add(task);
        Storage.writeToDukeFile();
        System.out.println("You now have a total of " + tasks.size() + " tasks in your list! Subarashii!");
    }

    /**
     * Method that displays the list of tasks to the console
     */
    public static void listTasks() {
        if (tasks.size() == 0) {
            System.out.println("Empty much!");
        }
        StringBuilder listOfTasks = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            listOfTasks.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }
        System.out.println(listOfTasks);
    }

    /**
     * Method that takes in a list of tasks and displays the list of tasks to the console
     * @param tasks list of tasks
     */
    public static void listTasks(ArrayList<Task> tasks) {
        if (tasks.size() == 0) {
            System.out.println("Empty much!");
        }
        StringBuilder listOfTasks = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            listOfTasks.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }
        System.out.println(listOfTasks);
    }

    /**
     * Method that returns the size/length of tasks
     * @return Integer that indicates the size/length of tasks
     */
    public static Integer getTaskSize() {
        return tasks.size();
    }

}

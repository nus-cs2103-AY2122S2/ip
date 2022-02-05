package tasks;

import java.util.ArrayList;
import java.util.Locale;

import duke.DukeException;
import duke.Storage;


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
    public static String deleteTask(int taskId) throws DukeException {
        Task preview = tasks.get(taskId - 1);
        tasks.remove(taskId - 1);
        Storage.writeToDukeFile();
        return "Otsukaresamadeshita! You have finally completed one task.\n"
                + preview;
    }

    /**
     * Method that takes in a taskId and marks it as done in the tasks arraylist
     * @param taskId id of task
     * @param mark boolean indicating whether to mark the task as done
     * @throws DukeException if the task has yet to be done
     */
    public static String markTask(int taskId, boolean mark) throws DukeException {
        Task toSet = tasks.get(taskId - 1);
        if (mark) {
            toSet.markIsDone();
            tasks.set(taskId - 1, toSet);
            Storage.writeToDukeFile();
            return "Sugoi! I have marked this task as done!\n"
                    + tasks.get(taskId - 1).toString();
        } else {
            if (toSet.isDone) {
                toSet.markUndone();
                tasks.set(taskId - 1, toSet);
                Storage.writeToDukeFile();
                return "Daijoubu! I have unmarked this task for you!\n"
                        + tasks.get(taskId - 1).toString();
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
    public static String addTask(Task task) throws DukeException {
        tasks.add(task);
        Storage.writeToDukeFile();
        return "You now have a total of "
                + tasks.size() + " tasks in your list! Subarashii!";
    }

    /**
     * Method that displays the list of tasks to the console
     */
    public static String listTasks() {
        if (tasks.size() == 0) {
            return "Empty much!";
        }
        StringBuilder listOfTasks = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            listOfTasks.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }
        return listOfTasks.toString();
    }

    /**
     * Method that takes in a list of tasks and displays the list of tasks to the console
     * @param tasks list of tasks
     */
    public static String listTasks(ArrayList<Task> tasks) {
        if (tasks.size() == 0) {
            return "Empty much!";
        }
        StringBuilder listOfTasks = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            listOfTasks.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }
        return listOfTasks.toString();
    }

    /**
     * Method that returns the size/length of tasks
     * @return Integer that indicates the size/length of tasks
     */
    public static Integer getTaskSize() {
        return tasks.size();
    }

    /**
     * Method that takes in a keyword and prints out the list of tasks that contains the keyword
     * @param keyWord task keyword
     */
    public static String findTask(String keyWord) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            String taskDescription = task.getDescription().toLowerCase(Locale.ROOT);
            String keyWordDescription = keyWord.toLowerCase(Locale.ROOT);
            if (taskDescription.matches("\\b" + keyWordDescription + "\\b")) {
                matchingTasks.add(task);
            }
        }
        return "This is what we found! \n" + listTasks(matchingTasks);
    }


}

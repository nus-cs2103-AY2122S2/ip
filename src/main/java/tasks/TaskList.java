package tasks;
import duke.*;

import duke.Storage;

import java.util.ArrayList;
import java.util.Locale;

public class TaskList {

    private static ArrayList<Task> tasks = new ArrayList<>();

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() { }


    public static void deleteTask(int taskId) throws DukeException {
        Task preview = tasks.get(taskId - 1);
        tasks.remove(taskId - 1);
        Storage.writeToDukeFile();
        System.out.println("Otsukaresamadeshita! You have finally completed one task.\n" + preview);
    }


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

    public static void addTask(Task task) throws DukeException {
        System.out.println("Added as per your request: " + task);
        tasks.add(task);
        Storage.writeToDukeFile();
        System.out.println("You now have a total of " + tasks.size() + " tasks in your list! Subarashii!");
    }



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

    public static ArrayList<Task> getTasks() {
        return tasks;
    }

    public static Integer getTaskSize() {
        return tasks.size();
    }

    /**
     * Method that takes in a keyword and prints out the list of tasks that contains the keyword
     * @param keyWord task keyword
     */
    public static void findTask(String keyWord) {
        System.out.println("here");
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            String taskDescription = task.getDescription().toLowerCase(Locale.ROOT);
            String keyWordDescription = keyWord.toLowerCase(Locale.ROOT);
            if (taskDescription.matches("\\b" + keyWordDescription + "\\b")) {
                matchingTasks.add(task);
            }
        }
        System.out.println("This is what we found!");
        listTasks(matchingTasks);
    }


}

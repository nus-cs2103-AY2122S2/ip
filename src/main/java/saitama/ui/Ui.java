package saitama.ui;

import java.util.List;

import saitama.tasks.Task;
import saitama.tasks.TaskList;

public class Ui {
    /**
     * Shows the welcome logo and message.
     */
    public void showWelcome() {
        String logo = "   _____       _ _                        \n"
                + "  / ____|     (_) |                       \n"
                + " | (___   __ _ _| |_ __ _ _ __ ___   __ _ \n"
                + "  \\___ \\ / _` | | __/ _` | '_ ` _ \\ / _` |\n"
                + "  ____) | (_| | | || (_| | | | | | | (_| |\n"
                + " |_____/ \\__,_|_|\\__\\__,_|_| |_| |_|\\__,_|\n";

        System.out.println(logo);
        showLine();
        System.out.println("I'm Saitama, a hero for fun.");
        System.out.println("What can I do for you?");
        showLine();
    }

    /**
     * Prints a line.
     */
    public String showLine() {
        return "____________________________________________________________";
    }

    /**
     * Prints the error message.
     *
     * @param errorMessage The error message to be printed.
     */
    public String showError(String errorMessage) {
        return errorMessage;
    }

    /**
     * Prints the saving message.
     */
    public String showSave() {
        String reply = String.format("OK...\nYour tasks have been saved!");
        return reply;
    }

    public String showAddTask(Task task, TaskList taskList) {
        String reply = String.format("OK...\nThe following task has been added to the list:\n"
                + "%s\nNow you have %d tasks in the list", task, taskList.numOfTasks());
        return reply;
    }

    public String showDeleteTask(Task task, TaskList taskList) {
        String reply = String.format("OK...\nThe following task has been removed from the list:\n"
                + "%s\nNow you have %d tasks in the list", task, taskList.numOfTasks());
        return reply;
    }

    public String showMarkTask(Task task, TaskList taskList) {
        String reply = String.format("OK...\nThe following task has marked:\n%s",
                task, taskList.numOfTasks());
        return reply;
    }

    public String showUnmarkTask(Task task, TaskList taskList) {
        String reply = String.format("OK...\nThe following task has unmarked:\n%s",
                task, taskList.numOfTasks());
        return reply;
    }

    public String showMatchingTasks(List<Task> matchingTasks) {
        String reply = "OK...\n";
        int counter = 1;
        if (matchingTasks.size() == 0) {
            return "There are no matching tasks in your list!";
        } else {
            reply += "Here are the matching tasks in your list:";
            for (Task task : matchingTasks) {
                reply += "\n" + counter + "." + task;
                counter += 1;
            }
        }
        return reply;
    }

    public String showList(TaskList taskList) {
        String reply = "OK...\n";
        if (taskList.numOfTasks() == 0) {
            return "There are no tasks in your list!";
        } else {
            reply += "Here are the tasks in your list:\n";
            reply += taskList;
        }
        return reply;
    }
}


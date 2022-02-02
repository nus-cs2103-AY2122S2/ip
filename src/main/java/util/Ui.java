package util;

import Tasks.Task;
import Tasks.TaskList;

import java.io.*;

/**
 * Ui is a class that deals with user interaction
 */
public class Ui {

    public String showSuccessfulAdd(Task task, int listSize) {
        return String.format("Got it. I've added this task:\n" + " %s\n" +
                "Now you have %s tasks in the list.\n", task.toString(), listSize);
    }

    public String showWelcome() {
        return "Hello! I'm Duke \nWhat can I do for you?\n";
    }

    public String showLine() {
        return "_____________________________\n";
    }

    public String showDeleteReply(Task task, int taskListSize) {
        return String.format("Noted. I've removed this task:\n" + "%s" +
                "\nNow you have %s tasks in the list.\n", task.toString(), taskListSize);
    }

    public String showMarkReply(Task task) {
        return String.format("Nice! I've marked this task as done: \n %s \n", task.toString());
    }

    public String showUnmarkReply(Task task) {
        return String.format("OK, I've marked this task as not done yet: \n %s \n", task.toString());
    }


    public String showTaskList(TaskList taskList) {
        StringBuilder stringBuilder = new StringBuilder("Here are the items in your list: \n");
        for (int i = 0; i < taskList.getSize(); i++) {
            Tasks.Task task = taskList.get(i);

            stringBuilder.append((i + 1) + ".");
            stringBuilder.append(task.toString() + "\n");
        }
        return stringBuilder.toString();
    }

    public String showLoadingError(String message) {
        return String.format("Error loading the file: %s", message);
    }

    public String showEmptyFind() {
        return ("Sorry, there are no tasks corresponding to the keyword entered \n");
    }

    public String showErrorMessage(String err) {
        return String.format("%s\n", err);
    }

    public String showByeMessage() {
        return ("Bye. Hope to see you again soon!");
    }
}

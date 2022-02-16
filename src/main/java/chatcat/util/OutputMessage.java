package chatcat.util;

import java.util.ArrayList;

import chatcat.tasks.Task;

public class OutputMessage {

    public static String deleteMessage(String removed, int size) {
        return "Noted. I've removed this task:\n" + removed + "\n" +
                "Now you have " + size +
                " tasks in the list.";
    }

    public static String filterMessage(ArrayList<Task> filteredList) {
        StringBuffer str = new StringBuffer();

        str.append("Here are the matching tasks in your list:" + "\n");
        for (int i = 0; i < filteredList.size(); i++) {
            str.append((i + 1) + ". " + filteredList.get(i) + "\n");
        }

        return str.toString();
    }

    public static String listTaskMessage(ArrayList<Task> tasks) {
        StringBuffer str = new StringBuffer();

        assert tasks.size() > 0 : "empty list!";

        str.append("Here are the tasks in your list:" + "\n");
        for (int i = 0; i < tasks.size(); i++) {
            str.append((i + 1) + ". " + tasks.get(i) + "\n");
        }

        return str.toString();
    }

    public static String markTaskMessage(String task) {
        return "Nice! I've marked this task as done:\n" + task;
    }

    public static String unmarkTaskMessage(String task) {
        return "OK, I've marked this task as not done yet:\n" + task;
    }

    public static String setTaskMessage(Task task, int size) {
        return "Got it. I've added this task:\n" + task + "\n" +
                "Now you have " + size + " tasks in the list.";
    }

    public static String taskErrorMessage() {
        return "OOPS!!! The description of a task cannot be empty.";
    }

    public static String repeatedTaskErrorMessage() {
        return "OOPS!!! The description of the task cannot be repeated.";
    }

    public static String invalidInputMessage() {
        return "OOPS!!! Invalid description of task.";
    }

    public static String indexErrorMessage() {
        return "Index is larger than task list size";
    }

    public static String emptyListErrorMessage() {
        return "List is Empty!";
    }

    public static String invalidDateMessage() {
        return "sorry, this is not a valid time";
    }

    public static String byeMessage() {
        return "Bye. Hope to see you again soon!";
    }
}

package van;

import java.util.Scanner;
import java.util.ArrayList;

public class Ui {
    StringBuilder builder;

    public Ui() {
        builder = new StringBuilder();
    }

    public void exitMessage() {
        builder.setLength(0);
        builder.append("bye\n");
    }

    public void deleteMessage() {
        builder.setLength(0);
        builder.append("Task deleted\n");
    }

    public void invalidMessage(String message) {
        builder.setLength(0);
        builder.append(message);
    }

    public void tagMessage(boolean isAddTag) {
        builder.setLength(0);
        if (isAddTag) {
            builder.append("tag added\n");
        } else {
            builder.append("tag deleted\n");
        }
    }

    public void taskMessage(Task task, int count) {
        builder.setLength(0);
        builder.append("Task added\n");
        builder.append(" " + task.getStatus() + "\n");
        builder.append(count + " tasks pending\n");
    }

    public void markMessage() {
        builder.setLength(0);
        builder.append("Changed task status\n");
    }

    public void printList(ArrayList<Task> taskList) {
        builder.append("Pending tasks:\n");
        for (int i = 0; i < taskList.size(); i++) {
            builder.append(i + 1 + ". " + taskList.get(i).getStatus() + "\n");
        }
    }

    public void printResults(ArrayList<Task> taskList, String keyword) {
        builder.append("Tasks containing " + keyword + ":\n");
        for (int i = 0; i < taskList.size(); i++) {
            builder.append(i + 1 + ". " + taskList.get(i).getStatus() + "\n");
        }
    }

    public String printString() {
        String output = builder.toString();
        builder.setLength(0);
        return output;
    }
}

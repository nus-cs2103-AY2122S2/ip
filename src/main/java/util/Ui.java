package util;

import Tasks.Task;
import Tasks.TaskList;

import java.io.*;

public class Ui {
    private BufferedReader br = new BufferedReader(new
            InputStreamReader(System.in));
    private PrintWriter pr = new PrintWriter(new
            BufferedWriter((new OutputStreamWriter((System.out)))));

    public void successfulAdd(Task task, int listSize) {
        pr.print("Got it. I've added this task:\n" + task.toString() + "\n" +
                "Now you have " + listSize + " tasks in the list.\n");
        pr.flush();
    }

    public void showWelcome() {
        pr.print("Hello! I'm Duke \nWhat can I do for you?\n");
        pr.flush();
    }

    public String readCommand() throws IOException {
        return br.readLine();
    }

    public void showLine() {
        pr.print("_____________________________\n");
        pr.flush();
    }

    public void deleteReply(Task task, int taskListSize) {
        pr.print("Noted. I've removed this task:\n");
        pr.print(task.toString());
        pr.print("\nNow you have " + taskListSize + " tasks in the list.\n");
        pr.flush();
    }

    public void markReply(Task task) {
        pr.print("Nice! I've marked this task as done:" + "\n");
        pr.print(task.toString() + "\n");
        pr.flush();
    }

    public void unmarkReply(Task task) {
        pr.print("OK, I've marked this task as not done yet:" + "\n");
        pr.print(task.toString() + "\n");
        pr.flush();
    }


    public void printTaskList(TaskList taskList) {
        pr.print("Here are the items in your list: \n");
        for (int i = 0; i < taskList.getSize(); i++) {
            Tasks.Task task = taskList.get(i);

            pr.print((i + 1) + ".");
            pr.print(task.toString());
            pr.print("\n");
        }
        pr.flush();
    }

    public void showLoadingError(String message) {
        pr.print("Error loading the file: " + message);
        pr.flush();
    }

    public void showEmptyFind() {
        pr.print("Sorry, there are no tasks corresponding to the keyword entered \n");
        pr.flush();
    }

    public void showErrorMessage(String err) {
        pr.print(err + "\n");
        pr.flush();
    }

    public void showByeMessage() {
        pr.print("Bye. Hope to see you again soon!");
        pr.flush();
        pr.close();
    }
}

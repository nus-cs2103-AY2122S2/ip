package duke;

import duke.task.Task;
import duke.task.TaskList;

import java.util.Scanner;

public class Ui {

    private Scanner scanner = new Scanner(System.in);

    public Ui() {

    }

    public void showWelcomeMessage() {
        System.out.println("Ello, my name is Ekud, "
                + "your personal task tracking bot.");
    }

    public void showEmptyDescriptionMessage(String taskName) {
        System.out.println("I'm so very sorry, the description of a "
                + taskName + " cannot be empty.");
    }

    public void showLoadingErrorMessage() {
        System.out.println("Oh no, looks like I have some trouble finding your task list.\n"
                + "Fred not, Ekud has created the file for you!");
    }

    public String readCommand() {
        System.out.println("How can Ekud help you with?");
        String command = scanner.nextLine();
        return command;
    }

    public void closeScanner() {
        scanner.close();
    }

    public void showLoadFileMessage(TaskList tasks) {
        System.out.println("duke.task.Task list successfully loaded\n"
                + "You currently have " + tasks.getTaskSize()
                + " task(s) in your list.");
    }

    public void showExitMessage() {
        System.out.println("Goodbye, feel free to use Ekud anytime!");
    }

    public void showAddTaskMessage(TaskList taskList, Task task) {
        System.out.println("Got it. I've added this task: \n"
                + task.toString() + "\nNow you have "
                + taskList.getTaskSize() + " tasks in the list.");
    }

    public void showMarkMessage(Task task) {
        System.out.println("Nice! I've marked this task as done: \n"
                + task.toString());
    }

    public void showUnMarkMessage(Task task) {
        System.out.println("OK, I've marked this task as not done yet: \n"
                + task.toString());
    }

    public void showDeleteMessage(Task task, int taskSize) {
        System.out.println("Noted. I've removed this task:\n"
                + task.toString() + "\nNow you have "
                + taskSize + " tasks in the list.");
    }

    public void showEmptyListMessage() {
        System.out.println("You do not have any tasks in your list!");
    }

    public void showListMessage(TaskList tasks) {
        for (int i = 0; i < tasks.getTaskSize(); i++) {
            System.out.println(i + 1 + "." + tasks.getTask(i + 1).toString());
        }
    }

    public void showClearListConfirmationMessage() {
        System.out.println("Are you sure you want Ekud to clear your task list?\n"
                + "Enter \"y\" if you would like to clear your task list, enter any other "
                + "characters if you would like to cancel this command");
    }

    public void showClearListMessage(String response) {
        if (response.equals("y")) {
            System.out.println("Got it, Ekud has cleared your task list");
        } else {
            System.out.println("Very well");
        }
    }
}


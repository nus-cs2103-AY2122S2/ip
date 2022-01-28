package duke.ui;

import duke.task.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ui {
    public final String STR_PADDING = "      ";
    protected final Scanner sc = new Scanner(System.in);

    protected void showExit() {
        printWithDivider("Pleasure to be of service, see you soon!");
    }

    public void close() {
        this.showExit();
        sc.close();
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void showToggleTask(Task task) {
        List<String> outputs = new ArrayList<>();
        String markedString = "Nice! I've marked this task: ";
        String unmarkedString = "OK, I've unmarked this task: ";
        String outString = task.getIsMarked() ? markedString : unmarkedString;

        outputs.add(outString);
        outputs.add("   " + task);

        printWithDivider(outputs);
    }

    public void showDeletion(int listSize, Task task) {
        List<String> outputs = new ArrayList<>();
        String ack = "Noted, I've removed this task: ";
        String taskString = (listSize == 1) ? "task" : "tasks";
        String size = String.format("Now you have %d %s in the list", listSize, taskString);

        outputs.add(ack);
        outputs.add("   " + task);
        outputs.add(size);

        printWithDivider(outputs);
    }

    public void showAddition(int listSize, Task task) {
        List<String> outputs = new ArrayList<>();
        String taskString = (listSize == 1) ? "task" : "tasks";
        String ack = "Got it, I've added this task: ";
        String size = String.format("Now you have %d %s in the list", listSize, taskString);

        outputs.add(ack);
        outputs.add("   " + task);
        outputs.add(size);

        printWithDivider(outputs);
    }

    public void showError(String errorMessage) {
        this.printWithDivider("Error: " + errorMessage);
    }


    public void showWelcome() {
        String botName = "Baymax";
        String greeting = "Greetings, I am " + botName + ".\n" +
                STR_PADDING + "What can I do you for?";

        this.printWithDivider(greeting);
    }

    protected void printWithDivider(List<String> messages) {
        System.out.println("    ---------------------------------------------");
        for (String s : messages) {
            System.out.println(STR_PADDING + s);
        }
        System.out.println("    ---------------------------------------------");
    }

    public void printWithDivider(StringBuilder message) {
        System.out.println("    ---------------------------------------------");
        System.out.println(message.toString());
        System.out.println("    ---------------------------------------------");
    }

    public void printWithDivider(String message) {
        System.out.println("    ---------------------------------------------");
        System.out.println(STR_PADDING + message);
        System.out.println("    ---------------------------------------------");
    }

    protected void printDivString() {
        System.out.println("    ---------------------------------------------");
    }
}

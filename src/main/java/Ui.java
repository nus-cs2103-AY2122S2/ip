import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ui {
    protected final String strPadding = "      ";
    protected final Scanner sc = new Scanner(System.in);

    protected void showExit() {
        printWithDivider("Pleasure to be of service, see you soon!");
    }

    protected void close() {
        this.showExit();
        sc.close();
    }

    protected String readCommand() {
        return sc.nextLine();
    }

    protected void showToggleTask(Task task) {
        List<String> outputs = new ArrayList<>();
        String markedString = "Nice! I've marked this task: ";
        String unmarkedString = "OK, I've unmarked this task: ";
        String outString = task.isMarked ? markedString : unmarkedString;

        outputs.add(outString);
        outputs.add("   " + task);

        printWithDivider(outputs);
    }

    protected void showDeletion(int listSize, Task task) {
        List<String> outputs = new ArrayList<>();
        String ack = "Noted, I've removed this task: ";
        String taskString = (listSize == 1) ? "task" : "tasks";
        String size = String.format("Now you have %d %s in the list", listSize, taskString);

        outputs.add(ack);
        outputs.add("   " + task);
        outputs.add(size);

        printWithDivider(outputs);
    }

    protected void showAddition(int listSize, Task task) {
        List<String> outputs = new ArrayList<>();
        String taskString = (listSize == 1) ? "task" : "tasks";
        String ack = "Got it, I've added this task: ";
        String size = String.format("Now you have %d %s in the list", listSize, taskString);

        outputs.add(ack);
        outputs.add("   " + task);
        outputs.add(size);

        printWithDivider(outputs);
    }

    protected void showError(String errorMessage) {
        this.printWithDivider("Error: " + errorMessage);
    }


    protected void showWelcome() {
        String botName = "Baymax";
        String greeting = "Greetings, I am " + botName + ".\n" +
                strPadding + "What can I do you for?";

        this.printWithDivider(greeting);
    }

    protected void printWithDivider(List<String> messages) {
        System.out.println("    ---------------------------------------------");
        for (String s : messages) {
            System.out.println(strPadding + s);
        }
        System.out.println("    ---------------------------------------------");
    }

    protected void printWithDivider(StringBuilder message) {
        System.out.println("    ---------------------------------------------");
        System.out.println(message.toString());
        System.out.println("    ---------------------------------------------");
    }

    protected void printWithDivider(String message) {
        System.out.println("    ---------------------------------------------");
        System.out.println(strPadding + message);
        System.out.println("    ---------------------------------------------");
    }

    protected void printDivString() {
        System.out.println("    ---------------------------------------------");
    }
}

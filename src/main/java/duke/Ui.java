package duke;

import duke.task.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Ui {
    /** Padding string used to format the bot's responses*/
    public final String STR_PADDING = "      ";
    private final Scanner sc = new Scanner(System.in);

    /** Print exit message and close {@link #sc}*/
    public void close() {
        printWithDivider("Pleasure to be of service, see you soon!");
        sc.close();
    }

    /**
     * Read the command string from UI's Scanner ({@link #sc}).
     *
     * @return the string
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Show the input task getting marked or unmarked based on its status.
     *
     * @param task the task
     */
    public void showToggleTask(Task task) {
        List<String> outputs = new ArrayList<>();
        String markedString = "Nice! I've marked this task: ";
        String unmarkedString = "OK, I've unmarked this task: ";
        String outString = task.getIsMarked() ? markedString : unmarkedString;

        outputs.add(outString);
        outputs.add("   " + task);

        printWithDivider(outputs);
    }

    /**
     * Show that the input task has been removed from the list.
     *
     * @param listSize the list size
     * @param task     the task
     */
    public void showDeletion(int listSize, Task task) {
        List<String> outputs = new ArrayList<>();
        String ack = "Noted, I've removed the following task: ";
        String taskString = (listSize == 1) ? "task" : "tasks";
        String size = String.format("Now you have %d %s in the list", listSize, taskString);

        outputs.add(ack);
        outputs.add("   " + task);
        outputs.add(size);

        printWithDivider(outputs);
    }

    /**
     * Show that the input task has been added to the list.
     *
     * @param listSize the list size
     * @param task     the task
     */
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

    /**
     * Show an error message containing the input parameter.
     *
     * @param errorMessage the error message
     */
    public void showError(String errorMessage) {
        this.printWithDivider("Error: " + errorMessage);
    }


    /** Show a welcome message with the bot's name. */
    public void showWelcome() {
        String botName = "Duke, the Task Master";
        String greeting = "Greetings, I am " + botName + ".\n" +
                STR_PADDING + "I'm here to help you with your... tasks, obviously!";

        this.printWithDivider(greeting);
    }

    /**
     * Print the content of the input List enclosed by dividers.
     *
     * @param messages the messages
     */
    protected void printWithDivider(List<String> messages) {
        System.out.println("    -----------------------------------------------------------");
        for (String s : messages) {
            System.out.println(STR_PADDING + s);
        }
        System.out.println("    -----------------------------------------------------------");
    }

    /**
     * Print the content of the input StringBuilder enclosed by dividers.
     *
     * @param message the message
     */
    public void printWithDivider(StringBuilder message) {
        System.out.println("    -----------------------------------------------------------");
        System.out.println(message.toString());
        System.out.println("    -----------------------------------------------------------");
    }

    /**
     * Print the content of the input String enclosed by dividers.
     *
     * @param message the message
     */
    public void printWithDivider(String message) {
        System.out.println("    -----------------------------------------------------------");
        System.out.println(STR_PADDING + message);
        System.out.println("    -----------------------------------------------------------");
    }
}

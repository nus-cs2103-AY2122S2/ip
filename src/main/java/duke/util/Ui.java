package duke.util;

import java.io.PrintStream;

import java.util.Scanner;

/**
 * Utility class that provides interactivity with the user.
 */
public class Ui {

    private final static PrintStream OUT = System.out;
    private final static Scanner IN = new Scanner(System.in);

    private final String HELP = "\tA 'bye' command will exit the program, yarr.\n";
    private final String LINE = "\t___________________________________________________________\n";
    private final String REQUEST_NEXT_COMMAND = "\tAye, Aye. Your next command:";
    private final String REQUEST_NEXT_COMMAND_ANGRY = "\tAye Aye, better get it right this time. Your next command:";
    private final String TASK_CALL = "\tAvast ye Matey. Here goes your task list:\n";
    private final String TASK_COMPLETED = "\tTask completed, good job matey!\n";
    private final String TASK_DELETED = "\tAlright matey, task has been deleted good on ya.\n";
    private final String TASK_MATCH = "\tAvast ye Matey. Here goes your matching tasks in your task list:\n";
    private final String TASK_UNCHECKED = "\tAlright matey, hurry up and finish up this task arrr:\n";
    private final String WELCOME_MESSAGE = "\tAhoy! Welcome aboard adventurer, Cap'n Dave at your service.\n";
    private final String WELCOME_QUESTION = "\tWhat can I do for you? Your command:\n";

    /**
     * Reads the user input.
     *
     * @return User input.
     */
    public String readCommand() {
        return IN.nextLine();
    }

    /**
     * Prints a message to inform user that task has been deleted
     */
    public void deleteTask() {
        System.out.println(TASK_DELETED);
    }

    /**
     * Prints a message to inform user that task has been marked as done
     */
    public void markAsDone() {
        OUT.println(TASK_COMPLETED);
    }

    /**
     * Prints a message to inform user that task has been marked as undone
     */
    public void markAsUndone() {
        OUT.println(TASK_UNCHECKED);
    }

    /**
     * Prints a message to inform user of the list of outstanding tasks
     */
    public void printTaskList() {
        OUT.println(TASK_CALL);
    }

    /**
     * Prints a message to request for user to input next command
     */
    public void requestNextCommand() {
        OUT.println(LINE + REQUEST_NEXT_COMMAND);
    }

    public void showExitMessage() {
        OUT.println("\tFair winds adventurer, till we meet next time yarr. Bye for now.\n" + LINE);
    }

    /**
     * Prints a message to inform user of the list of tasks that matches the keyword
     */
    public void showMatchingTasks() {
        OUT.println(TASK_MATCH);
    }

    /**
     * Prints a message to welcome the user
     */
    public void showWelcome() {
        OUT.println(LINE + WELCOME_MESSAGE + HELP + WELCOME_QUESTION);
    }

    /**
     * Prints a line separator.
     */
    public void showLine() {
        OUT.println(LINE);
    }

    /**
     * Prints a message to inform user of their input error
     */
    public void showError(String errorMessage) {
        OUT.println(errorMessage + REQUEST_NEXT_COMMAND_ANGRY);
    }
}

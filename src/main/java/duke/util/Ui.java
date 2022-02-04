package duke.util;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * Utility class that provides interactivity with the user.
 */
public class Ui {

    private static final PrintStream OUT = System.out;
    private static final Scanner IN = new Scanner(System.in);

    private static final String HELP = "A 'bye' command will exit the program, yarr.\n";
    private static final String LINE = "___________________________________________________________\n";
    private static final String REQUEST_NEXT_COMMAND = "Aye, Aye. Your next command:";
    private static final String REQUEST_NEXT_COMMAND_ANGRY
            = "Aye Aye, better get it right this time. Your next command:";
    private static final String TASK_CALL = "Avast ye Matey. Here goes your task list:\n";
    private static final String TASK_COMPLETED = "Task completed, good job matey!\n";
    private static final String TASK_DELETED = "Alright matey, task has been deleted good on ya.\n";
    private static final String TASK_MATCH = "Avast ye Matey. Here goes your matching tasks in your task list:\n";
    private static final String TASK_UNCHECKED = "Alright matey, hurry up and finish up this task arrr:\n";
    private static final String WELCOME_MESSAGE = "Ahoy! Welcome aboard adventurer, Cap'n Dave at your service.\n";
    private static final String WELCOME_QUESTION = "What can I do for you? Your command:\n";

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
    public String deleteTask() {
        return TASK_DELETED;
    }

    /**
     * Prints a message to inform user that task has been marked as done
     */
    public String markAsDone() {
        return TASK_COMPLETED;
    }

    /**
     * Prints a message to inform user that task has been marked as undone
     */
    public String markAsUndone() {
        return TASK_UNCHECKED;
    }

    /**
     * Prints a message to inform user of the list of outstanding tasks
     */
    public String printTaskList() {
        return TASK_CALL;
    }

    /**
     * Prints a message to request for user to input next command
     */
    public String requestNextCommand() {
        return LINE + REQUEST_NEXT_COMMAND;
    }

    public String showExitMessage() {
        return "Fair winds adventurer, till we meet next time yarr. Bye for now.\n" + LINE;
    }

    /**
     * Prints a message to inform user of the list of tasks that matches the keyword
     */
    public String showMatchingTasks() {
        return TASK_MATCH;
    }

    /**
     * Prints a message to welcome the user
     */
    public String showWelcome() {
        return WELCOME_MESSAGE + HELP + WELCOME_QUESTION;
    }

    /**
     * Prints a LINE separator.
     */
    public String showLine() {
        return LINE;
    }

    /**
     * Prints a message to inform user of their input error
     */
    public String showError(String errorMessage) {
        return errorMessage + REQUEST_NEXT_COMMAND_ANGRY;
    }
}

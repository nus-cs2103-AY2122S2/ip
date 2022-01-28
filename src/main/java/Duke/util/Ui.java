package Duke.util;

import java.io.PrintStream;

import java.util.Scanner;

public class Ui {

    private final static PrintStream OUT = System.out;
    private final static Scanner IN = new Scanner(System.in);

    private final String HELP = "\tA 'bye' command will exit the program, yarr.\n";
    private final String WELCOME_MESSAGE = "\tAhoy! Welcome aboard adventurer, Cap'n Dave at your service.\n";
    private final String WELCOME_QUESTION = "\tWhat can I do for you? Your command:\n";
    private final String LINE = "\t___________________________________________________________\n";
    private final String REQUEST_NEXT_COMMAND = "\tAye, Aye. Your next command:";
    private final String REQUEST_NEXT_COMMAND_ANGRY = "\tAye Aye, better get it right this time. Your next command:";
    private final String TASK_CALL = "\tAvast ye Matey. Here goes your task list:\n";
    private final String TASK_COMPLETED = "\tTask completed, good job matey!\n";
    private final String TASK_DELETED = "\tAlright matey, task has been deleted good on ya.\n";
    private final String TASK_UNCHECKED = "\tAlright matey, hurry up and finish up this task arrr:\n";

    public String readCommand() {
        return IN.nextLine();
    }

    public void deleteTask() {
        System.out.println(TASK_DELETED);
    }

    public void markAsDone() {
        OUT.println(TASK_COMPLETED);
    }

    public void markAsUndone() {
        OUT.println(TASK_UNCHECKED);
    }

    public void requestNextCommand() {
        OUT.println(LINE + REQUEST_NEXT_COMMAND);
    }

    public void showExitMessage() {
        OUT.println("\tFair winds adventurer, till we meet next time yarr. Bye for now.\n" + LINE);
    }

    public void printTaskList() {
        OUT.println(TASK_CALL);
    }

    public void showWelcome() {
        OUT.println(LINE + WELCOME_MESSAGE + HELP + WELCOME_QUESTION);
    }

    public void showLine() {
        OUT.println(LINE);
    }

    public void showError(String errorMessage) {
        OUT.println(errorMessage + REQUEST_NEXT_COMMAND_ANGRY);
    }
}

package cleese;

import task.Task;
import task.TasksList;

/**
 * Handles all printing of messages to console for specific commands or situations
 */
public class Ui {

    /** Prints a line denoting the start and end of the block that represents one user input and response */
    public void printLine() {
        System.out.println("--------------------------------------------------");
    }

    /** Prints welcome message shown to user on program startup */
    public void printWelcomeMessage() {
        System.out.println("Good day Sir, this is Cleese, your virtual butler!\n"
                            + "And just how may I help you today?");
    }

    /** Prints goodbye message shown to user on program termination */
    public void printGoodByeMessage() {
        System.out.println("If that'll be all sir, i shall retire for the day.\n"
                            + "I'll be in my quarters if you require me");
    }

    /** Prints acknowledgment message as well as task that user has marked as done */
    public void printMarkedMessage(Task task) {
        System.out.println(String.format("Ok, I've marked this task as done:\n%s",task.toString()));
    }

    /** Prints acknowledgment message as well as task that user has marked as undone */
    public void printUnmarkedMessage(Task task) {
        System.out.println(String.format("Ok, I've marked this task as not done yet:\n%s",task.toString()));
    }

    /** Prints acknowledgment message as well as task that user has added to the TasksList */
    public void printAddedAck(Task task, TasksList taskList) {
        System.out.println(String.format("Got it. I've added this task:\n%s\n"
                                            + "Now you have %d tasks in the list",task.toString(),taskList.size()));
    }

    /** Prints acknowledgment message as well as task that user has removed from the TasksList */
    public void printRemovedAck(Task task, TasksList taskList) {
        System.out.println(String.format("Noted. I've removed this task:\n%s\n"
                                            + "Now you have %d tasks in the list",task.toString(),taskList.size()));
    }

    /** Prints the acknowledgment message that is shown to the user before the results of find() */
    public void printFindMessage() {
        System.out.println(String.format("Here are the matching tasks in your list:"));
    }
}

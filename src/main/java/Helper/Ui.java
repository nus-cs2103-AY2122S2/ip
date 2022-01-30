package Helper;

/**
 * This file contains the implementation of Ui class.
 * @author Saravanan Anuja Harish
 */

import Tasks.*;

public class Ui {

    /**
     * prints introductory greetings.
     */
    public static void printGreeting() {

        String greeting = "Hello There, My name is Conan! \n"
                + "Hope you're doing fine today! (^_^) \n"
                + "I'm a task manager, and I can help you keep up with your tasks.\n"
                + "Now before we start, lets get acquainted! Lets start with our names!";
        System.out.println(greeting);
    }

    /**
     * prints separator, to differentiate between different commands.
     */
    public static void printSeparator() {
        String separator = "------------------------------------------------";
        System.out.println(separator);
    }

    /**
     * prints a farewell message to the user.
     * @param username the name of the user.
     */
    public static void printFarewell(String username) {
        String farewell = "\nHope I helped you complete your tasks!\n"
                + "Have a great day ahead, enjoy ! (^-^)/\n"
                + "Hope to see you next time! ";
        System.out.println("Goodbye, " + username + farewell);
    }

    /**
     * prints an introductory message.
     * @param username the name of the user.
     */
    public static void printSayHello(String username) {
        String str = "Hello, " + username + "!, Nice to meet you! (*^_^*)\n" +
                "So, tell me what would you like to do, " + username + "!";
        System.out.println(str);
    }

    /**
     * prints a message saying that a user under a similar name was found.
     * @param previousUser the previous username.
     */
    public static void printFoundSimilarName(String previousUser) {
        String str = "I have found out that there was a similar user in the past under the name:\n" +
                previousUser + "\n" +
                "If this is you, would you like to continue from the previous tasks ?" +
                "If this isn't you or you don't want to use the previous tasks, please type no";
        System.out.println(str);
    }

    /**
     * prints a message asking if there is anything else the user wants to do.
     * @param username the name of the user.
     */
    public static void printAsk(String username) {
        String ASK = "Please let me know if there's anything else you would like to do, ";
        System.out.println(ASK + username);
    }

    /**
     * asks the user to enter a valid name.
     */
    public static void printAskValidName() {
        System.out.println("Please enter a valid name!");
    }

    /**
     * prints the number of tasks the users has to do.
     * @param num the number of tasks.
     */
    public static void printNumOfTasks(int num) {
        System.out.println("Number of tasks up to now: " + num);
    }

    /**
     * prints that task was added successfully to the list of tasks.
     * @param task the task that was added.
     */
    public static void printAdded(String task) {
        System.out.println("I have added: " + task + ", to your list of tasks");
    }

    /**
     * prints that the task has been removed from the list of tasks.
     * @param task the task that was removed.
     */
    public static void printRemoved(Task task) {
        String str = "The following task has been removed from the list :" + "\n" + task.toString();
        System.out.println(str);
    }

    /**
     * asks the user to try again.
     */
    public static void printTryAgain() {
        System.out.println("Please try again!");
    }

    /**
     * tells the users that the task they have completed is marked.
     * @param task the task that was completed.
     */
    public static void printTaskCompleted(Task task) {
        String str = "Great job, on completing this task! \\(^_^)/\n" + task.toString();
        System.out.println(str);
    }

    /**
     * tells the user that the task is unmarked.
     * @param task the task that was unmarked.
     */
    public static void printUnmarked(Task task) {
        String str = "Sure, I have unmarked this task:\n" + task.toString();
        System.out.println(str);
    }

    /**
     * prints error.
     */
    public static void printError() {
        System.out.println("Error...");
    }

    /**
     * prints that no tasks were found.
     */
    public static void printNoTasksFound(String word) {
        System.out.println("No task containing: " + word + ", was found.");
    }

    /**
     * prints a message for the user.
     * @param message the message to be printed.
     */
    public static void printMessage(String message) {
        System.out.println(message);
    }
}

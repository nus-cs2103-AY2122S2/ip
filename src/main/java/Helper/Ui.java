package helper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import tasks.Task;

/**
 * This file contains the implementation of Ui class.
 * @author Saravanan Anuja Harish
 */

public class Ui {

    // stores the location of previous task file.
    private static final String PATH_TEMP_FILE = "tempStore.txt";

    // stores empty string.
    private static final String EMPTY_STRING = "";

    /**
     * prints introductory greetings.
     */
    public static void printGreeting() {

        String greeting = "Hello There, My name is Conan! \n"
                + "Hope you're doing fine today! (^_^) \n"
                + "I'm a task manager, and I can help you keep up with your tasks.\n"
                + "Now before we start, lets get acquainted! Lets start with our names!";

        try {
            FileWriter writer = new FileWriter(PATH_TEMP_FILE);
            writer.write(greeting);
            writer.close();
        } catch (IOException e) {
            Ui.printError();
            Ui.printMessage(e.toString());
        }
    }


    /**
     * prints a farewell message to the user.
     *
     * @param username the name of the user.
     */
    public static void printFarewell(String username) {
        String farewell = "\nHope I helped you complete your tasks!\n"
                + "Have a great day ahead, enjoy ! (^-^)/\n"
                + "Hope to see you next time! ";

        try {
            FileWriter writer = new FileWriter(PATH_TEMP_FILE);
            writer.write("Goodbye, " + username + farewell);
            writer.close();
        } catch (IOException e) {
            Ui.printError();
            Ui.printMessage(e.toString());
        }
    }

    /**
     * prints an introductory message.
     *
     * @param username the name of the user.
     */
    public static void printSayHello(String username) {
        String str = "Hello, " + username + "!, Nice to meet you! (*^_^*)\n"
                + "So, tell me what would you like to do, " + username + "!";

        try {
            FileWriter writer = new FileWriter(PATH_TEMP_FILE);
            writer.write(str);
            writer.close();
        } catch (IOException e) {
            Ui.printError();
            Ui.printMessage(e.toString());
        }
    }

    /**
     * prints a message saying that a user under a similar name was found.
     *
     * @param previousUser the previous username.
     */
    public static void printFoundSimilarName(String previousUser) {
        String str = "I have found out that there was a similar user in the past under the name:\n"
                + previousUser + "\n"
                + "If this is you, would you like to continue from the previous tasks ?"
                + "If this isn't you or you don't want to use the previous tasks, please type no";
        try {
            FileWriter writer = new FileWriter(PATH_TEMP_FILE);
            writer.write(str);
            writer.close();
        } catch (IOException e) {
            Ui.printError();
            Ui.printMessage(e.toString());
        }
    }

    /**
     * prints a message asking if there is anything else the user wants to do.
     *
     * @param username the name of the user.
     */
    public static void printAsk(String username) {
        String ask = "Please let me know if there's anything else you would like to do, ";

        try {
            FileWriter writer = new FileWriter(PATH_TEMP_FILE);
            writer.write(ask + username);
            writer.close();
        } catch (IOException e) {
            Ui.printError();
            Ui.printMessage(e.toString());
        }
    }

    /**
     * asks the user to enter a valid name.
     */
    public static void printAskValidName() {
        String str = "Please enter a valid name!";
        try {
            FileWriter writer = new FileWriter(PATH_TEMP_FILE);
            writer.write(str);
            writer.close();
        } catch (IOException e) {
            Ui.printError();
            Ui.printMessage(e.toString());
        }
    }

    /**
     * prints the number of tasks the users has to do.
     *
     * @param num the number of tasks.
     */
    public static void printNumOfTasks(int num) {
        String str = "Number of tasks up to now: " + num;
        try {
            FileWriter writer = new FileWriter(PATH_TEMP_FILE);
            writer.write(str);
            writer.close();
        } catch (IOException e) {
            Ui.printError();
            Ui.printMessage(e.toString());
        }
    }

    /**
     * prints that task was added successfully to the list of tasks.
     *
     * @param task the task that was added.
     */
    public static void printAdded(String task) {
        String str = "I have added: " + task + ", to your list of tasks";
        try {
            FileWriter writer = new FileWriter(PATH_TEMP_FILE);
            writer.write(str);
            writer.close();
        } catch (IOException e) {
            Ui.printError();
            Ui.printMessage(e.toString());
        }
    }

    /**
     * prints that the task has been removed from the list of tasks.
     *
     * @param task the task that was removed.
     */
    public static void printRemoved(Task task) {
        String str = "The following task has been removed from the list :" + "\n" + task.toString();
        try {
            FileWriter writer = new FileWriter(PATH_TEMP_FILE);
            writer.write(str);
            writer.close();
        } catch (IOException e) {
            Ui.printError();
            Ui.printMessage(e.toString());
        }
    }

    /**
     * asks the user to try again.
     */
    public static void printTryAgain() {
        String str = "Please try again!";
        try {
            FileWriter writer = new FileWriter(PATH_TEMP_FILE);
            writer.write(str);
            writer.close();
        } catch (IOException e) {
            Ui.printError();
            Ui.printMessage(e.toString());
        }
    }

    /**
     * tells the users that the task they have completed is marked.
     *
     * @param task the task that was completed.
     */
    public static void printTaskCompleted(Task task) {
        String str = "Great job, on completing this task! \\(^_^)/\n" + task.toString();
        try {
            FileWriter writer = new FileWriter(PATH_TEMP_FILE);
            writer.write(str);
            writer.close();
        } catch (IOException e) {
            Ui.printError();
            Ui.printMessage(e.toString());
        }
    }

    /**
     * tells the user that the task is unmarked.
     *
     * @param task the task that was unmarked.
     */
    public static void printUnmarked(Task task) {
        String str = "Sure, I have unmarked this task:\n" + task.toString();
        try {
            FileWriter writer = new FileWriter(PATH_TEMP_FILE);
            writer.write(str);
            writer.close();
        } catch (IOException e) {
            Ui.printError();
            Ui.printMessage(e.toString());
        }
    }

    /**
     * prints error.
     */
    public static void printError() {
        String str = "Error...";
        try {
            FileWriter writer = new FileWriter(PATH_TEMP_FILE);
            writer.write(str);
            writer.close();
        } catch (IOException e) {
            Ui.printError();
            Ui.printMessage(e.toString());
        }
    }

    /**
     * prints that no tasks were found.
     */
    public static void printNoTasksFound(String word) {
        String str = "No task containing: " + word + ", was found.";
        try {
            FileWriter writer = new FileWriter(PATH_TEMP_FILE);
            writer.write(str);
            writer.close();
        } catch (IOException e) {
            Ui.printError();
            Ui.printMessage(e.toString());
        }
    }

    /**
     * prints a message for the user.
     * @param message the message to be printed.
     */
    public static void printMessage(String message) {
        try {
            FileWriter writer = new FileWriter(PATH_TEMP_FILE);
            writer.write(message);
            writer.close();
        } catch (IOException e) {
            Ui.printError();
            Ui.printMessage(e.toString());
        }
    }


    /**
     * reads the text written to the file.
     *
     * @return the String of message written to the file.
     */
    public static String readFile() {

        try {
            File file = new File(PATH_TEMP_FILE);
            Scanner sc = new Scanner(file);
            String str = EMPTY_STRING;
            while (sc.hasNextLine()) {
                str += sc.nextLine() + "\n";
            }
            sc.close();
            return str.trim();
        } catch (FileNotFoundException e) {
            return e.toString();
        }
    }
}

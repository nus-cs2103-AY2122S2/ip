package user;

import exceptions.DukeException;
import tasks.Task;
import tasks.Tasklist;

import java.util.Scanner;

/** A class that handles the interactions with the user. */
public class Ui {

    public Scanner sc = new Scanner(System.in);
    public Tasklist tasklist;
    public static String INDENT = "    ";
    public static String SEPARATOR = "--------------------------------------------";
    public static String LOGO = INDENT + " ____        _        \n"
            + INDENT + "|  _ \\ _   _| | _____ \n"
            + INDENT + "| | | | | | | |/ / _ \\\n"
            + INDENT + "| |_| | |_| |   <  __/\n"
            + INDENT + "|____/ \\__,_|_|\\_\\___|\n";
    public String[] OPENING_MESSAGE = new String[] {"Hello! I'm Duke!", "What can I do for you?"};
    public String CLOSING_MESSAGE = "Bye. Hope to see you again soon!";

    public Parser parser;

    /**
     * Constructor method to create a new tasklist and parser.
     */
    public Ui() {
        tasklist = new Tasklist();
        tasklist.loadTasks();
        parser = new Parser();
    }

    /**
     * Prints a string with indentation before it.
     *
     * @param message The string to be printed with indentation before it.
     */
    public static void printIndent(String message) {
        System.out.println(INDENT + message);
    }

    /**
     * Prints a string with separators before and after it.
     *
     * @param message The string to be printed with separators before and after it.
     */
    public static void prettyPrint(String message) {
        printIndent(SEPARATOR);
        printIndent(message);
        printIndent(SEPARATOR + "\n");
    }

    /**
     * Prints multiple string with separators before and after it.
     *
     * @param messages An array of strings string to be printed with separators before and after them.
     */
    public static void prettyPrint(String[] messages) {
        printIndent(SEPARATOR);
        for (String message : messages) {
            printIndent(message);
        }
        printIndent(SEPARATOR + "\n");
    }

    /**
     * Greets the user with a Duke logo and a welcome message.
     */
    public void greet() {
        System.out.println(LOGO);
        prettyPrint(OPENING_MESSAGE);
    }

    /**
     * Greets the user with a goodbye message.
     */
    public void sayGoodbye() {
        prettyPrint(CLOSING_MESSAGE);
    }

    /**
     * Displays the tasks saved to the user.
     */
    public void displayTasks() {
        printIndent(SEPARATOR);
        if (tasklist.getNumTasks() == 0) {
            printIndent("You have no tasks!");
        } else {
            printIndent("Here are the tasks in your list:");
            for (int i = 0; i < tasklist.getNumTasks(); i++) {
                printIndent(String.format("%d. %s", i+1, tasklist.getTask(i)));
            }
        }
        printIndent(SEPARATOR + "\n");
    }

    /**
     * Reads user input.
     *
     * @return The user's input.
     */
    public String getInput() {
        return sc.nextLine();
    }

    /**
     * Handles the user's input, and performs the corresponding action.
     *
     * @param userInput The user's input
     */
    public void handle(String userInput) {
        try {
             if (userInput.equals("list")) {  // display tasks
                displayTasks();
            } else if (userInput.startsWith("mark ")) {  // mark task as done
                int taskToMark = parser.handleMarkTask(userInput, tasklist.getNumTasks());
                String[] result = tasklist.markTask(taskToMark);
                prettyPrint(result);
            } else if (userInput.startsWith("unmark ")) {  // mark task as undone
                 int taskToUnmark = parser.handleUnmarkTask(userInput, tasklist.getNumTasks());
                 String[] result = tasklist.unmarkTask(taskToUnmark);
                 prettyPrint(result);
            } else if (userInput.startsWith("delete ")) {  // delete task
                 int taskToDelete = parser.handleDeleteTask(userInput, tasklist.getNumTasks());
                 String[] result = tasklist.deleteTask(taskToDelete);
                 prettyPrint(result);
            } else {  // add task
                Task t = parser.addTask(userInput);
                 tasklist.addTask(t);
                 String[] messages = new String[] {
                         "Got it. I've added this task:",
                         t.toString(),
                         "Now you have " + tasklist.getNumTasks() + " tasks in the list."
                 };
                 prettyPrint(messages);
            }
            tasklist.saveTasks();
        } catch (DukeException err) {
            prettyPrint(err.getMessage());
        }
    }
}
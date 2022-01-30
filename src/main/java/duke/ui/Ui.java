package duke.ui;

import duke.task.Task;
import duke.task.TaskList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * A class that handles the interactions with the user.
 */
public class Ui {
    /**
     * Constructor to initialize an instance of Ui class.
     */
    public Ui() {
    }

    /**
     * Reads the command line input from the user.
     *
     * @return The command line that was entered
     */
    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        System.out.println("ENTER COMMAND:");
        System.out.print("\t");
        return sc.nextLine().trim();
    }

    /**
     * Displays the welcome message.
     */
    public void displayWelcome() {
        String logo = "\t" + " ____        _        " + System.lineSeparator()
                + "\t" + "|  _ \\ _   _| | _____" + System.lineSeparator()
                + "\t" + "| | | | | | | |/ / _" + System.lineSeparator()
                + "\t" + "| |_| | |_| |   <  __/" + System.lineSeparator()
                + "\t" + "|____/ \\__,_|_|\\_\\___|" + System.lineSeparator();

        String welcomeMessage = "\t" + "Hello! I'm Duke, your Personal Assistant ChatBot."
                /* + System.lineSeparator()
                + logo */
                + System.lineSeparator()
                + "\t" + "What can I do for you?";

        displayResponse(welcomeMessage);
        displayLine();
    }

    /**
     * Displays the goodbye message on exiting.
     */
    public void displayExit() {
        String exitMessage = "\t" + "Bye. Hope to see you again soon!";
        displayResponse(exitMessage);
    }

    /**
     * Displays the response message with proper formatting.
     *
     * @param message Response message to be displayed
     */
    public void displayResponse(String message) {
        displayLine();
        System.out.println(message);
    }

    /**
     * Displays the error message with proper formatting.
     *
     * @param message Error message to be displayed
     */
    public void displayError(String message) {
        displayLine();
        System.out.println("ERROR MESSAGE:");
        System.out.println("\t" + "☹ " + message);
    }

    /**
     * Displays the filtered tasks message with proper formatting.
     *
     * @param message Filtered tasks message to be displayed
     */
    public void displayFilteredTasks(String message) {
        displayLine();
        System.out.println("[FILTERED TASKS]" + System.lineSeparator());
        System.out.println(message);
    }

    /**
     * Displays a line to separate different parts of a message.
     */
    public void displayLine() {
        String horizontalLine = "____________________________________________________________";
        System.out.println(horizontalLine);
    }

    /**
     * Returns the message when the task is added.
     *
     * @param task Task that is added
     * @return The string representation of the message
     */
    public String taskAddedMessage(Task task) {
        return "\t" + "Got it. I've added this task:"
                + System.lineSeparator() + "\t\t" + task;
    }

    /**
     * Returns the message when the task is marked as done.
     *
     * @param task Task that is marked as done
     * @return The string representation of the message
     */
    public String taskDoneMessage(Task task) {
        return "\t" + "Nice! I've marked this task as done:"
                + System.lineSeparator() + "\t\t" + task;
    }


    /**
     * Returns the message when the task is marked as not done yet.
     *
     * @param task Task that is marked as not done yet
     * @return The string representation of the message
     */
    public String taskNotDoneMessage(Task task) {
        return "\t" + "OK, I've marked this task as not done yet:"
                + System.lineSeparator() + "\t\t" + task;
    }

    /**
     * Returns the message when the task is removed.
     *
     * @param task Task that is removed
     * @return The string representation of the message
     */
    public String taskDeletedMessage(Task task) {
        return "\t" + "Noted. I've removed this task:"
                + System.lineSeparator() + "\t\t" + task;
    }

    /**
     * Returns the message with the number of tasks in the task list.
     *
     * @param taskList Task list containing all the tasks
     * @return The string representation of the message
     */
    public String numOfTasksInListMessage(TaskList taskList) {
        return "\t" + "Now you have " + taskList.getNumOfTasks()
                + (taskList.getNumOfTasks() > 1 ? " tasks" : " task")
                + " in the list.";
    }

    /**
     * Returns the message with all the tasks in the task list.
     *
     * @param taskList Task list containing all the tasks
     * @return The string representation of the message
     */
    public String tasksInListMessage(TaskList taskList) {
        return "\t" + "Here"
                + (taskList.getNumOfTasks() > 1 ? " are the tasks " : " is the task ")
                + "in your list:"
                + System.lineSeparator()
                + "\t" + "[Legend: T = todo, D = deadline, E = event]"
                + System.lineSeparator()
                + System.lineSeparator()
                + taskList;
    }

    /**
     * Returns the message with the tasks in the task list that
     * occurs on the specified date.
     *
     * @param taskList Task list containing all the tasks
     * @param dateStr Specified date
     * @return The string representation of the message
     */
    public String tasksOnDateMessage(TaskList taskList, String dateStr) {
        return "\t" + "Here"
                + (taskList.getNumOfFilteredTasks() > 1 ? " are the tasks " : " is the task ")
                + "on this date (" + processDateStr(dateStr) + "):"
                + System.lineSeparator()
                + "\t" +  "[Legend: T = todo, D = deadline, E = event]"
                + System.lineSeparator()
                + System.lineSeparator()
                + taskList;
    }

    /**
     * Returns the specified date string after processing.
     *
     * If the specified date is a valid date format accepted by
     * the system, return the date in LocalDate (MMM d yyyy) format,
     * otherwise return the original specified date.
     *
     * @param dateStr Specified date
     * @return Specified date string after processing
     */
    private String processDateStr(String dateStr) {
        LocalDate date;
        String processedDateStr;

        try {
            date = LocalDate.parse(dateStr);
            processedDateStr = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch (Exception e) {
            processedDateStr = dateStr;
        }

        return processedDateStr;
    }
}

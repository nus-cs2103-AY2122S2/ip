package seedu.duke.chatbot;

import seedu.duke.task.Task;
import seedu.duke.task.TaskList;

import java.io.File;
import java.util.Scanner;

/**
 * Handles all interactions with the user.
 * Takes in user input using a Scanner object.
 * Specifies the outputs from Duke in different scenarios.
 */
public class Ui {
    private final Scanner sc;

    /**
     * Creates a Ui object that can take in commands from the user.
     */
    Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Used to tell user that Duke is loading tasks saved in database.
     */
    public static String showStartLoading() {
        return "Hold on...I am checking if you have previous tasks saved...";
    }

    /**
     * Introduces Duke to user.
     * @return the name of user to personalise interaction after welcome
     */
    public static String showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        return "Thanks for waiting, let me introduce myself! Hello from\n" + logo;
    }

    /**
     * Shows error message from exceptions raised.
     * @param errorMessage taken from exceptions raised
     * @return errorMessage to be said by chatbot
     */
    public static String showError(String errorMessage) {
        System.out.println(errorMessage);
        return errorMessage;
    }

    /**
     *Shows the result of loading all saved task, which is a {@link TaskList}.
     * @param oldTaskList for {@link TaskList} generated from database
     * @return response by chatbot after converting database information to {@link TaskList}
     */
    public static String showLoadingResult(TaskList oldTaskList) {
        return "Take a look at your previous tasks:\n" + oldTaskList.printTasks();
    }

    /**
     * Used to indicate to user that a file that acts as database has been created.
     * @param myObj which is the file that was created
     */
     public static String showFileCreated(File myObj) {
         return "File Created: " + myObj.getName();
    }

    /**
     * Indicates to user that their command to add a {@link Task} is successful.
     * @param taskList which is the {@link TaskList} that the {@link Task} was added to
     * @param newTask which is the new {@link Task} that was added
     */
    public static String showAddResult(TaskList taskList, Task newTask) {
        return String
                .format("Got it, I've added this task: \n %s\nNow you have %d task in the list",
                        newTask.toString(),
                        taskList.getNumberOfTasks());
    }

    /**
     * Indicates to user that their command to delete a {@link Task} is successful.
     * @param taskList which is the {@link TaskList} that the {@link Task} was deleted from
     * @param taskToRemove which is the {@link Task} that was deleted
     */
    public static String showDeleteResult(TaskList taskList, Task taskToRemove) {
        return String
                .format("Got it, I've deleted this task: \n %s\nNow you have %d task in the list",
                        taskToRemove.toString(),
                        taskList.getNumberOfTasks());
    }

    /**
     * Indicates to user that their command to unmark a {@link Task} is successful.
     * @param unmarkedTask which is the {@link Task} that was unmarked
     * @return response by chatbot after {@link seedu.duke.command.UnmarkCommand}
     */
    public static String showUnmarkedResult(Task unmarkedTask) {
        return String
                .format("OK, I've marked this task as not done yet:\n %s", unmarkedTask.toString());
    }


    /**
     * Indicates to user that their command to mark a {@link Task} is successful.
     * @param markedTask which is the {@link Task} that was marked
     */
    public static String showMarkedResult(Task markedTask) {
        return String
                .format("Nice! I've marked this task as done: \n%s", markedTask.toString());
    }


    /**
     * Indicates to user that the database has been updated after a change to {@link TaskList}.
     */
    public static String showCompleteUpdateOfFile() {
        return "Database has been updated";
    }

    /**
     * Indicates to user that their command to search the {@link TaskList} has been completed.
     * Shows the user the tasks that match their search keyword
     * @param taskList which only contains {@link Task} that contain the search keyword
     */
    public static String showCompletedSearch(TaskList taskList) {
        return "Here are the matched tasks:\n" + taskList.printTasks();
    }
}

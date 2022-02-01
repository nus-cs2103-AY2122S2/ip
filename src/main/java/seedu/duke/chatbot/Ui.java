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
     * Creates a Ui object that can take in commands from the user
     */
    Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Used to tell user that Duke is loading tasks saved in database.
     */
    void showStartLoading() {
        System.out.println("Hold on...I am checking if you have previous tasks saved...");
    }

    /**
     * Introduces Duke to user.
     * @return the name of user to personalise interaction after welcome
     */
    String showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Thanks for waiting, let me introduce myself! Hello from\n" + logo);

        System.out.println("What is your name?");

        Scanner in = new Scanner(System.in);
        String name = in.nextLine();
        System.out.println("Hello " + name);

        return name;
    }

    /**
     * Takes in input from user.
     * @return command from user in string
     */
    String readCommand() {
        return sc.nextLine();
    }

    /**
     * Shows error message from exceptions raised.
     * @param errorMessage taken from exceptions raised
     */
    void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    /**
     *Shows the result of loading all saved task, which is a {@link TaskList}.
     * @param oldTaskList for {@link TaskList} generated from database
     */
    void showLoadingResult(TaskList oldTaskList) {
        System.out.println("Take a look at your previous tasks:");
        oldTaskList.printTasks();
    }

    /**
     * Used to indicate to user that a file that acts as database has been created.
     * @param myObj which is the file that was created
     */
    void showFileCreated(File myObj) {
        System.out.println("File Created: " + myObj.getName());
    }

    /**
     * Indicates to user that their command to add a {@link Task} is successful.
     * @param taskList which is the {@link TaskList} that the {@link Task} was added to
     * @param newTask which is the new {@link Task} that was added
     */
    public void showAddResult(TaskList taskList, Task newTask) {
        System.out.println(String
                .format("Got it, I've added this task: \n %s\nNow you have %d task in the list",
                        newTask.toString(),
                        taskList.getNumberOfTasks()));
    }

    /**
     * Indicates to user that their command to delete a {@link Task} is successful.
     * @param taskList which is the {@link TaskList} that the {@link Task} was deleted from
     * @param taskToRemove which is the {@link Task} that was deleted
     */
    public void showDeleteResult(TaskList taskList, Task taskToRemove) {
        System.out.println(String
                .format("Got it, I've deleted this task: \n %s\nNow you have %d task in the list",
                        taskToRemove.toString(),
                        taskList.getNumberOfTasks()));
    }

    /**
     * Indicates to user that their command to unmark a {@link Task} is successful.
     * @param unmarkedTask which is the {@link Task} that was unmarked
     */
    public static void showUnmarkedResult(Task unmarkedTask) {
        System.out.println(String
                .format("OK, I've marked this task as not done yet:\n %s", unmarkedTask.toString()));
    }


    /**
     * Indicates to user that their command to mark a {@link Task} is successful.
     * @param markedTask which is the {@link Task} that was marked
     */
    public static void showMarkedResult(Task markedTask) {
        System.out.println(String
                .format("Nice! I've marked this task as done: \n%s", markedTask.toString()));
    }


    /**
     * Indicates to user that the database has been updated after a change to {@link TaskList}.
     */
    public void showCompleteUpdateOfFile() {
        System.out.println("Database has been updated");
    }

    /**
     * Indicates to user that their command to search the {@link TaskList} has been completed.
     * Shows the user the tasks that match their search keyword
     * @param taskList which only contains {@link Task} that contain the search keyword
     */
    public void showCompletedSearch(TaskList taskList) {
        System.out.println("Here are the matched tasks:");
        taskList.printTasks();
    }
}

package duke;

import java.io.PrintStream;
import java.util.Scanner;

import duke.tasks.Task;

public class Ui {

    private static final String line =
            "------------------------------------------------";
    private static final String doubleLine =
            "================================================";
    private Scanner scanner = new Scanner(System.in);
    private PrintStream writer = System.out;

    /**
     * Returns a help menu of possible commands.
     *
     * @return Help menu.
     */
    public String showHelpMenu() {
        String helpMenu = "List of available commands:\n\n"
                + "\"list\": View Current Task List\n"
                + "\"list name\": View Current Task List sorted by name\n"
                + "\"list date\": View Current Task List sorted by date\n"
                + "\"todo <name>\": Add a new Task\n"
                + "\"deadline <name> /by <date/time>\": Add a new Deadline\n"
                + "\"event <name> /at <date/time>\": Add a new Event\n"
                + "\"delete <number>\": delete the corresponding entry in your task list\n"
                + "\"mark <number>\": mark the corresponding entry in your task list as done\n"
                + "\"unmark <number>\": mark the corresponding entry in your task list as not done\n"
                + "\"update <number> /date <new date>\": Updates the task number with a new date\n"
                + "\"update <number> /name <new name>\": Updates the task number with a new name ";

        return helpMenu;
    }
    public String showBanner() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String intro = "I am a Personal Assistant Chatbot that helps you to keep track of various things.\n"
                + "Let's get started.";

        return "Hello from" + "\n"
                + logo + "\n"
                + intro + " ";
    }
    public String showBye() {
        String bye = "Bye. Hope to see you again soon!";

        return line + "\n"
                + bye + "\n"
                + line;
    }

    // Error Messages
    public String showInvalidIntegerError() {
        return "Invalid number entered! Please enter an integer";
    }

    // Mark/Unmark Messages
    public String showMarked(Task t) {
        return String.format("    Nice! I've marked this task as done:\n+++ %s", t);
    }
    public String showUnmarked(Task t) {
        return String.format("    OK, I've marked this task as not done yet:\n--- %s", t);
    }
    public String showUnmarkNotNeeded(Task t) {
        return String.format("    This task has not been marked as done yet:\n    %s", t);
    }
    public String showMarkNotNeeded(Task t) {
        return String.format("    This task is already marked as done:\n    %s", t);
    }
    public String showMarkOutOfBounds() {
        return "Invalid number entered! No tasks marked.";
    }
    public String showUnmarkOutOfBounds() {
        return "Invalid number entered! No tasks unmarked.";
    }
    public String showMarkEmptyList() {
        return "There are no tasks in your task list to mark";
    }
    public String showUnmarkEmptyList() {
        return "There are no tasks in your task list to unmark";
    }

    /**
     * Returns a list displaying all tasks.
     *
     * @param tm The TaskManager containing the tasks.
     * @return A string listing the tasks in taskManager.
     */
    public String showList(TaskManager tm) {
        int i = 1;
        String s = doubleLine + "\n";
        if (tm.size() == 0) {
            s += "Your task list is empty.\n";
        } else {

            s += "Here is your task list:\n";
            for (Task t : tm.getTaskList()) {
                s += String.format("%d.%s\n", i, t.toString());
                i++;
            }
        }
        s += doubleLine;
        return s;
    }

    /**
     * Returns a list displaying the results of tasks that was found.
     *
     * @param tm The TaskManager containing the results of the find command.
     * @return A string listing the find results.
     */
    public String showFindResults(TaskManager tm) {
        int i = 1;
        String s = doubleLine + "\n";
        if (tm.size() == 0) {
            s += "No tasks found!\n";

        } else {
            s += "Here are the matching tasks in your list:\n";
            for (Task t : tm.getTaskList()) {
                s += String.format("%d.%s\n", i, t.toString());
                i++;
            }
        }
        s += doubleLine;
        return s;
    }

    // Add/Delete Messages
    /**
     * Returns a string showing the task that was added.
     *
     * @param t The task that was added.
     * @param numberOfTasks The number of tasks left.
     * @return A string describing the added task.
     */
    public String showAddedTask(Task t, int numberOfTasks) {
        return String.format("Got it. I've added this task:\n"
                + "\t%s\n"
                + "Now you have %d tasks in the list.", t.toString(), numberOfTasks);
    }

    /**
     * Returns a string showing the task that was deleted.
     *
     * @param t The task that was deleted.
     * @param numberOfTasks The number of tasks left.
     * @return A string describing the deleted task.
     */
    public String showDeletedTask(Task t, int numberOfTasks) {
        return String.format("Noted. I have removed this task:\n"
                + "\t%s\n"
                + "There are now %d tasks in your task list\n", t, numberOfTasks);
    }
    public String showDeleteEmptyList() {
        return "There are no tasks in your task list to delete.";
    }
    public String showDeleteFailed() {
        return "Delete Task Failed";
    }
    public String showDeleteOutOfBounds(int limit) {
        return String.format("Invalid number entered! The number entered must be between 1 and %s", limit);
    }

    // Update Messages
    public String showNoName() {
        return "No Name entered!";
    }
    public String showUpdateOutOfBounds() {
        return "Invalid number entered! No tasks updated.";
    }
    public String showUpdateSuccess(Task t) {
        return String.format("+++ Nice! I've updated this task:\n... %s", t);
    }
    public String showIncompatibleType() {
        return "No date information is associated with this task.";
    }
    public String showSameDateError() {
        return "Updated Date is the same as the old date!";
    }
    public String showSameNameError() {
        return "Updated name is the same as the old name!";
    }

    // Save/Load Files
    public String showInitializeDefaults() {
        return "...Initializing Defaults...";
    }
    public String showLoadFilePath(String filePath) {
        return "Loading task from: " + filePath;
    }
    public String showFileReadError() {
        return "Error Reading from file!";
    }
    public String showLoadingComplete() {
        return "Loading Completed!";
    }
    public String showSavingComplete() {
        return "Saving Completed!";
    }
    public String showSavingFailed() {
        return "Saving Failed!";
    }

    public String getUserInputLine() {
        writer.print("--> ");
        return scanner.nextLine();
    }
    public String getUserInput() {
        return scanner.next();
    }

    public String showMessage(String s) {
        return s;
    }


}

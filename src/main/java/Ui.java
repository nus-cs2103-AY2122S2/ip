import java.util.List;
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
     * @param tasks List containing all the tasks
     * @return The string representation of the message
     */
    public String numOfTasksInListMessage(List<Task> tasks) {
        return "\t" + "Now you have " + tasks.size()
                + (tasks.size() > 1 ? " tasks" : " task")
                + " in the list.";
    }

    /**
     * Returns the message with all the tasks in the task list.
     *
     * @param tasks List containing all the tasks
     * @return The string representation of the message
     */
    public String tasksInListMessage(List<Task> tasks) {
        StringBuilder sb = new StringBuilder();

        sb.append("\t")
                .append(tasks.size() > 1 ? "Here are the tasks in your list:" : "Here is the task in your list:")
                .append(System.lineSeparator())
                .append("\t")
                .append("[Legend: T = todo, D = deadline, E = event]")
                .append(System.lineSeparator());

        for (int i = 0; i < tasks.size(); i++) {
            if (i != 0) {
                sb.append(System.lineSeparator());
            }

            sb.append("\t").append(i + 1).append(". ").append(tasks.get(i));
        }

        return sb.toString();
    }
}

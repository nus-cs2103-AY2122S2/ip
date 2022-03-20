package duke;

import java.util.Scanner;

import duke.task.Task;

public class Ui {

    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Reads line from command line input.
     *
     * @return Line from command line.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays welcome message when user starts app.
     *
     * @return String to display.
     */
    public String showWelcome() {
        return "Hello from Duke!";
    }

    /**
     * Shows display when a task is added.
     *
     * @return String to display.
     */
    public String showTaskAdded(Task task) {
        return String.format("I've added the task: %s", task.toString());
    }

    /**
     * Shows display when a task is deleted.
     *
     * @return String to display.
     */
    public String showTaskDeleted() {
        return "I've deleted this task.";
    }

    /**
     * Shows display when a task is marked.
     *
     * @return String to display.
     */
    public String showTaskMarked() {
        return "I've marked this task as done.";
    }

    /**
     * Shows display when a task is unmarked.
     *
     * @return String to display.
     */
    public String showTaskUnmarked() {
        return "I've marked this task as not done.";
    }

    /**
     * Shows list of all tasks in specified list
     *
     * @param tasks List of all tasks.
     * @return String to display.
     */
    public String showTasks(TaskList tasks) {
        return String.format("Your todo list:\n\n%s", tasks.toString());
    }

    /**
     * Shows list of all contacts in specified list
     *
     * @param contacts List of all contacts.
     * @return String to display.
     */
    public String showContacts(ContactList contacts) {
        return String.format("Your telegram contacts:\n\n%s", contacts.toString());
    }

    /**
     * Shows display when a contact is added.
     *
     * @return String to display.
     */
    public String showContactAdded(Contact contact) {
        return String.format("I've added the contact: %s", contact.toString());
    }

    /**
     * Shows display when a contact is deleted.
     *
     * @return String to display.
     */
    public String showContactDeleted() {
        return String.format("I've deleted this contact.");
    }

    /**
     * Shows display when user exits app.
     *
     * @return String to display.
     */
    public String showMessage(String str) {
        return str;
    }

    /**
     * Shows display when user exits app.
     *
     * @return String to display.
     */
    public String showExit() {
        return "Goodbye!";
    }

    /**
     * Displays specified error message.
     *
     * @return String to display.
     */
    public String showError(String message) {
        return message;
    }

    /**
     * Shows divider.
     *
     * @return String to display.
     */
    public String showLine() {
        return "_____";
    }

    /**
     * Shows loading error.
     *
     * @return String to display.
     */
    public String showLoadingError() {
        return "Loading error";
    }

}

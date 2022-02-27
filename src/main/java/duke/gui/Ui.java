package duke.gui;

import duke.task.Task;

/**
 * Ui class to handle interactions with User.
 */
public class Ui {

    // variables for indentation
    private String logo = "Jarvis";
    private String dukeResponse;

    /**
     * Basic constructor to initialize the Ui object.
     */
    public Ui() {
        dukeResponse = new String("");
    }

    /**
     * Method to greet & ask for user's name.
     * @return String representing the user's name.
     */
    public String showWelcome() {

        // Jarvis introduces himself
        addText("Hello, I'm " + logo + ", your personal assistant. How should I address you?");

        return generateDukeResponse();
    }

    /**
     * Method to bid goodbye to the user, when the user wants to exit Duke.
     */
    public String showGoodbye() {
        // Jarvis says goodbye & the scanner is closed
        return "Goodbye for now. \n";
    }

    /**
     * Method to print a text to screen.
     * @param text String of text to be printed.
     */
    public void addText(String text) {
        dukeResponse += String.format("%s\n", text);
    }

    public void addTask(Task task) {
        dukeResponse += "Noted. I've added this task: \n" + task.toString();
    }

    public void listTask(int index, Task task) {
        dukeResponse += String.format((index + 1) + ". " + task.toString() + "\n");
    }

    public void deleteTask(Task task) {
        dukeResponse += "Okay, I've deleted this task: \n" + task.toString() + "\n";
    }

    /**
     * Generates Duke's response, based on user's input.
     * @return
     */
    public String generateDukeResponse() {
        String generatedDukeResponse = dukeResponse;
        dukeResponse = new String("");
        return generatedDukeResponse;
    }

    /**
     * Method to show an error on the screen.
     * @param errorMessage String of errorMessage to be printed.
     */
    public String showError(String errorMessage) {
        return String.format("%s\n", errorMessage);
    }
}

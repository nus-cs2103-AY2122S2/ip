package duke.ui;

import duke.tasks.Task;

public class Ui {

    public static void displayError(String message) {
        System.out.println(message);
    }

    public static void showSuccessfulMessage(String mssg) {
        System.out.println(mssg);
    }

    public static void displayTask(Task task) {
        System.out.println(task);
    }

    public void greet() {
        String welcome = "Hi my name is Duke!";
        String assist = "How may I help you today?";
        System.out.println(welcome);
        System.out.println(assist);
    }

    public String endSession() {
        return "Adios! See you soon:)";
    }

    public String addLineBreak() {
        return "---------------------xx-------------------------";
    }

    public void showLoadingError() {
        System.out.println("Oops! I had problem creating/loading logs.txt");
    }

    public void showInvalidCommandError() {
        System.out.println("sorry, this isn't a valid command yet!");
    }
}

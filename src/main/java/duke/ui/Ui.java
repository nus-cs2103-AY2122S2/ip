package duke.ui;

import duke.tasklist.TaskList;

public class Ui {

    public static String welcomeUser() {
        return "Hello! I want to be the best sentient PokePlanner! \nCome plan your day with me! \nType 'help' to access the commands!";
    }

    public static String farewellUser() {
        return "Pika! \nSee you again later, poke-trainer!";
    }

    public static String returnAddTaskRes(String input, Boolean success) {
        if (success) {
            return "Pika, the PokeTask has been successfully registered!\n   " + input;
        }
        return "Pika, we failed to register the PokeTask. \nTry the command again in a while? \nPikapika!";
    }

    public static String returnEditTaskRes(String input, Boolean success) {
        if (success) {
            return "Pika, the PokeTask has been successfully editted!\n   " + input;
        }
        return "Pika, we failed to edit your task. \nTry again in a while pikaaaa?";
    }

    public static String returnDeleteTaskRes(String input, Boolean success) {
        if (success) {
            return "Pika, the PokeTask has been successfully deleted!\n   " + input;
        }
        return "Pika, we failed to delete the PokeTask. \nTry the command again in a while? \nPikapika!";
    }

    public static String returnQueriedTaskRes(String input) {
        return "PIKA! Fetching your lovely tasks: \n" + input;
    }

    public static String returnListTaskRes(String input) {
        return "PIKA! Fetching your lovely tasks: \n" + input;
    }

    public static String returnIndexErrorRes() {
        return "Pika, the task you wanted to edit does not exist. \nWe failed to edit the PokeTask! \nBika";
    }

    public static void showErrorMessage(Exception err, String methodName) {
        System.out.println("Pika, there's an error in " +  methodName + "\n Error: " + err);
    }

}

package duke.ui;

public class Ui {

    /**
     * Welcome the user.
     *
     * @return A String to welcome the user.
     */
    public static String welcomeUser() {
        return "Hello! I want to be the best sentient PokePlanner! "
                + "\nCome plan your day with me! \nView the command in the readme!";
    }

    /**
     * Bid farewell to the user.
     *
     * @return A String to bid farewell to the useer.
     */
    public static String farewellUser() {
        return "Pika! \nSee you again later, poke-trainer!";
    }

    /**
     * Handles the response to the user adding a task
     *
     * @param input A String value of the print format of the task.
     * @param success The success status of adding a task.
     * @return A String value status of the execution of the method.
     */
    public static String returnAddTaskRes(String input, Boolean success) {
        if (success) {
            return "Pika, the PokeTask has been successfully registered!\n   " + input + "\n";
        }
        return "Pika, we failed to register the PokeTask. \nTry the command again in a while? \nPikapika!";
    }

    /**
     * Handles the response to the user editting the task.
     *
     * @param input A String value of the print format of the task
     * @param success The success status of editting a task.
     * @return A String value status of the execution of the method.
     */
    public static String returnEditTaskRes(String input, Boolean success) {
        if (success) {
            return "Pika, the PokeTask has been successfully editted!\n   " + input + "\n";
        }
        return "Pika, we failed to edit your task. \nTry again in a while pikaaaa?";
    }

    /**
     * Handles the response to the user deleting the task.
     *
     * @param input A String value of the print format of the task
     * @param success The success status of deleting a task.
     * @return A String value status of the execution of the method.
     */
    public static String returnDeleteTaskRes(String input, Boolean success) {
        if (success) {
            return "     " + input + " - Success\n";
        }
        return "     " + input + " - Failed\n";
    }

    /**
     * Indicate the status of deleting one task.
     *
     * @param input A String value of the print format of the task and its status.
     * @return A String value indicating the status of attempting to delete the task.
     */
    public static String returnDeleteHandlerRes(String input) {
        return "Pika, status of the tasks deleted:\n" + input + "\n";
    }

    public static String returnDeleteTaskError(int input) {
        return "     PokeTask " + (input + 1) + " does not exist.\n";
    }

    public static String returnQueriedTaskRes(String input) {
        return "PIKA! Fetching your lovely tasks: \n" + input + "\n";
    }

    public static String returnListTaskRes(String input) {
        return "PIKA! Fetching your lovely tasks: \n" + input + "\n";
    }

    public static String returnIndexErrorRes() {
        return "Pika, the task you wanted to edit does not exist. \nWe failed to edit the PokeTask! \nBika";
    }

    /**
     * Let the user know that there is no task in the database.
     *
     * @return A String value indicating there is no task in the database.
     */
    public static String returnNoTaskRes() {
        return "Pika, you do not have any PokeTask. \nAdd some now, pika";
    }

    public static String returnNoTaskQueried() {
        return "Pika, we did not find any relevant PokeTasks.";
    }

    public static String returnIndexRes() {
        return "Pika, the task you wanted to edit does not exist. \nWe failed to edit the PokeTask! \nBika";
    }

    public static String returnWrongFormatRes() {
        return "Pika, make sure you are typing in the correct format...\n Pika";
    }

    public static String returnUnknownErrorRes() {
        return "Pika, make sure you are typing a valid command in the right format...\n Pika";
    }

    public static void showErrorMessage(Exception err, String methodName) {
        System.out.println("Pika, there's an error in " + methodName + "\n Error: " + err);
    }

}

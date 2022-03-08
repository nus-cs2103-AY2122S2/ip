package duke.ui;

/**
 * Deals with interactions with the user.
 */
public class Ui {
    /**
     * Default constructor for Ui
     */
    public Ui() {
    }

    /**
     * Initial greeting for Duke.
     */
    public void startGreeting() {
        String logo = " ____        _        \n" + "|  _ \\ _   _| | _____ \n" + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n" + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        printResponse("Sup! Name's Duke \nHow can I help you today?");
    }

    /* Print in the response format */

    /**
     * Prints an input based on Duke's response format.
     *
     * @param response The response to be printed in the format.
     */
    public void printResponse(String response) {
        System.out.println(
                "\n--------------------------------------------------------------------------------------------");
        System.out.println("Duke Speaking:\n");
        System.out.println(response);
        System.out.println(
                "--------------------------------------------------------------------------------------------\n");
    }

    /**
     * Prints the error statement in the Duke's error format.
     *
     * @param errorStatement The error statement to be printed.
     */
    public void printError(String errorStatement) {
        printResponse("ERROR ERROR! DUKE HAS ERROR: \n \n" + errorStatement);
    }
}

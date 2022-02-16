package duke;

/**
 * Handles text to be returned to the user.
 */
public class Ui {

    /**
     * Welcome text that is to be printed when the user first starts Ducky.
     */
    @SuppressWarnings("checkstyle:OperatorWrap")
    public String welcome() {
        String logo = " ____              _"
                + "\n|  _ \\ _   _ _____| | ____ __\n"
                + "| | | | | | |  ___| |/ /\\ v /\n"
                + "| |_| | |_| | |___|   <  | |\n"
                + "|____/ \\___/\\_____|_|\\_\\ |_|\n";

        return (logo + "Hello! I'm Ducky! :)\n" + "I am a task manager.\n"
                + "Type 'help' for more information on the commands you can give me.\n"
                + "What can I do for you today?\n"
                + "✧･ﾟ: *✧･ﾟ:* |\\__( o)> *:･ﾟ✧*:･ﾟ✧");
    }

    /**
     * New command input should end with this and output should end with this as well.
     */
    public void showLine() {
        System.out.println("✧･ﾟ: *✧･ﾟ:* |\\__( o)> *:･ﾟ✧*:･ﾟ✧");
    }

    /**
     * Shows the error message that was caught.
     * @param errorMessage error message that is thrown in exceptions.
     */
    public String showError(String errorMessage) {
        return errorMessage;
    }

    /**
     * Prints the 'help' response by Ducky. Usually called when the user says "help".
     */
    public String printHelp() {
        String helpResponse = "> Type 'list' to see what you have in your task list\n"
                + "> Type 'todo <message>' to put a todo in your list\n"
                + "> Type 'deadline <message> /by <deadline>' to put a deadline in your list."
                + "\n\t - Deadline must be in 'DD MMMM YYYY' format or given in days."
                + "\n\t - e.g. mon or monday"
                + "\n> Type 'event <message> /at <date>' to put an event in your list"
                + "\n\t - Date must be in 'DD MMMM YYYY' format or given in days."
                + "\n\t - e.g. mon or monday"
                + "\n> Type 'mark <x>' to mark a task in your list"
                + "\n> Type 'unmark <x>' to unmark a task in your list"
                + "\n> Type 'find <x>' to find a task in your list"
                + "\n\t - x is the word in task description to be found";
        ;
        return (helpResponse);
    }



}

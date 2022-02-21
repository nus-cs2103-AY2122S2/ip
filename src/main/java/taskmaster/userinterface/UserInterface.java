package taskmaster.userinterface;

/**
 * This class encapsulates the UserInterface which interacts with
 * the user. The user will communicate with Taskmaster through
 * this class.
 */

public class UserInterface {
    /** Taskmaster's logo. **/
    protected String logo = "  _____\n" + " /     \\\n" + " | () () |\n" + " \\  ^  /\n"
                                    + "   |||||\n" + "   |||||\n";
    /**
     * Prints the opening message when the program runs.
     *
     * @return opening message
     */

    public String getOpeningMessage() {
        return logo + "\nGreetings, I'm Taskmaster, I'm super grumpy 24/7"
                + "\nOkay, what do you want?\n";
    }

    /**
     * returns the bye message displayed when the user exits.
     *
     * @return bye message
     */

    public String getByeMessage() {
        return "   See you around kiddo, I'm an angsty dude but deep down i'm a lonely man";
    }


    /**
     * Display Message when the user enters an invalid command.
     *
     * @param input The input entered by the user
     *
     * @return display the reply from task master when a wrong
     * command is entered.
     */

    public String displayInvalidCommand(String input) {
        return "\n   " + input + "?"
                + "\n   What are you on about? \n Type 'help' if "
                + "you want to know the commands, kid!\n";
    }

    /**
     * Prints the list of commands that are in this program.
     *
     * @return list of commands available in the program.
     */

    public String displayListOfCommand() {
        String result = "";
        result += "Available Commands in the program:\n";
        result += "List                                  -List out all your current tasks\n";
        result += "todo <task>                          -Add a todo task without any deadline"
                            + " specified\n";
        result += "deadline <task> /by <Date><Time>      -Adds a task due by "
                            + "specified deadline\n";
        result += "event <task> /at <Date><Time>         -Adds a task that occurs at the specified "
                            + "time\n";
        result += "mark <task number>                    -Marks task as completed\n";
        result += "unmark <task number>                  -Marks a completed task as uncompleted\n";
        result += "delete <task number>                  -Deletes the task at the specified index\n";
        result += "find <keyword>                        -Retrieves all tasks that contains the "
                + "keyword\n";
        result += "bye                                   -Exits the program\n";
        return result;
    }

    /**
     * Returns the error message.
     *
     * @param errorMsg what the message will be.
     *
     * @return error message in String representation.
     */
    public String displayErrorMessage(String errorMsg) {
        return "   " + errorMsg;
    }
}

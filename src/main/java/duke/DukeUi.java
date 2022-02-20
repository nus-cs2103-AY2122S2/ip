package duke;

/**
 * Represents a class that handles the responses that Duke gives in response to certain commands.
 */
public class DukeUi {

    public DukeUi() {
    }

    /**
     * A method that prints a load error message.
     */
    public String printLoadError() {
        return "_______________________________________________________\n"
                + "* Load to duke.txt failed *\n"
                + "Please try again!\n"
                + "If the problem persists, please contact the administrator!\n"
                + "_______________________________________________________\n";
    }

    /**
     * A method that prints a write error message.
     * @return Error message.
     */
    public String printWriteError() {
        return "_______________________________________________________\n"
                + "* Write to duke.txt failed *\n"
                + "Please try again!\n"
                + "If the problem persists, please contact the administrator!\n"
                + "_______________________________________________________\n";
    }

    /**
     * A method that prints Duke's default greeting.
     */
    public void printGreeting() {
        String logo = "_______________________________________________________\n"
                + " ____        _         _    ____ _   _ \n"
                + "|  _ \\ _   _| | _____ | | /  ___| | | |\n"
                + "| | | | | | | |/ / _ \\| | | |   | |_| |\n"
                + "| |_| | |_| |   <  __/| |_| |___|  _  |\n"
                + "|____/ \\__,_|_|\\_\\___||___|\\____|_| |_|\n"
                + "Hello! I'm DukeLCH\n"
                + "How can I be of service?\n" //Simple Greet
                + "_______________________________________________________\n";
        System.out.println(logo);
    }

    /**
     * A method that prints an error message when arguments are passed to command that do not require them.
     * @return Error message.
     */
    public String printFoundArgumentError() {
        return "_______________________________________________________\n"
                + "* Arguments detected *\n"
                + "This command does not require any arguments.\n"
                + "Are you sure this command is what you mean? If so, please try again!\n"
                + "_______________________________________________________\n";
    }

    /**
     * A method that prints an error message when arguments passed to a command is invalid.
     * @return Error message.
     */
    public String printInvalidArgumentError() {
        return "_______________________________________________________\n"
                + "* Invalid entry detected *\n"
                + "Please provide a valid entry!\n"
                + "_______________________________________________________\n";
    }

    /**
     * A method that prints an error message when there is no argument detected for a command that requires arguments.
     * @param keyword A String representing the specific command used.
     * @return Error message.
     */
    public String printMissingArgumentError(String keyword) {
        return "_______________________________________________________\n"
                + "* No arguments detected *\n"
                + "Please provide a description for your " + keyword + "!\n"
                + "_______________________________________________________\n";
    }

    /**
     * A method that prints an error message when there is no DateTime argument detected or
     * key phrases used to detect DateTime is inputted by the user.
     * @param keyword A String representing the specific command used.
     * @return Error message.
     */
    public String printMissingDateTimeArgumentError(String keyword) {
        switch (keyword) {
        case "deadline": {
            return "_______________________________________________________\n"
                    + "* Time frame not detected *\n"
                    + "Please provide a time frame for your " + keyword + "!\n"
                    + "e.g. deadline return book /by Sunday 7pm\n"
                    + "_______________________________________________________\n";
        }
        case "event": {
            return "_______________________________________________________\n"
                    + "* Time frame not detected *\n"
                    + "Please provide a time frame for your " + keyword + "!\n"
                    + "e.g. event project meeting /at 20/2/2022 1000H-1200H\n"
                    + "_______________________________________________________\n";
        }
        default:
            return "_______________________________________________________\n"
                    + "* Time frame not detected *\n"
                    + "Please provide a time frame for your " + keyword + "!\n"
                    + "_______________________________________________________\n";
        }
    }

    /**
     * A method that prints an error message when there is no label detected for Commands that require a label.
     * @param keyword A String representing the specific command used.
     * @return Error message.
     */
    public String printMissingLabel(String keyword) {
        switch (keyword) {
        case "deadline": {
            return "_______________________________________________________\n"
                    + "* Label not detected *\n"
                    + "Check if you have typed '/by' to indicate the time frame!\n"
                    + "e.g. deadline return book /by Sunday 7pm\n"
                    + "_______________________________________________________\n";
        }
        case "event": {
            return "_______________________________________________________\n"
                    + "* Label not detected *\n"
                    + "Check if you have typed '/at' to indicate the time frame!\n"
                    + "e.g. event project meeting /at 20/2/2022 1000H-1200H\n"
                    + "_______________________________________________________\n";
        }
        default:
            return "_______________________________________________________\n"
                    + "* Label not detected *\n"
                    + "Please provide a Label for your " + keyword + "!\n"
                    + "_______________________________________________________\n";
        }
    }

    /**
     * A method that prints a list of recognized commands if the user inputs an unrecognized command.
     * @return Error message.
     */
    public String printListOfCommands() {
        return "_______________________________________________________\n"
                + "* Unrecognised keyword used *\n"
                + "Please try the keywords provided below:\n"
                + "    1. list\n"
                + "    2. todo [description]\n"
                + "    3. deadline [description] /by [date] [time]\n"
                + "    4. event [description] /at [date] [time]\n"
                + "    5. mark [#entry]\n"
                + "    6. unmark [#entry]\n"
                + "    7. delete [#entry]\n"
                + "    8. update\n"
                + "    9. addcontacts [name] [contactNumber]\n"
                + "   10. listcontacts\n"
                + "   11. deletecontacts [#entry]\n"
                + "   12. bye\n"
                + "_______________________________________________________\n";
    }

    /**
     * A method that prints a response if Duke cannot find any tasks matching a users inputted phrase.
     * @return Error message.
     */
    public String printFoundNothing() {
        return "_______________________________________________________\n"
                + "It appears we could not find anything matching the phrase.\n"
                + "Please try adding the task to Duke!\n"
                + "_______________________________________________________\n";
    }

    /**
     * A method that prints a response if Duke cannot find a task in history when the user tries to edit said task.
     * @return Error message.
     */
    public String printFailedAccess() {
        return "_______________________________________________________\n"
                + "Duke cannot find the task in history.\n"
                + "_______________________________________________________\n";
    }
}

package taskmaster.userinterface;
import taskmaster.util.Storage;
import taskmaster.util.TaskList;


/**
 * This class encapsulates the UserInterface which interacts with
 * the user. The user will communicate with Taskmaster through
 * this class.
 */

public class UserInterface {
    /** Taskmaster's logo. **/
    protected String logo = "  _____\n" + " /     \\\n" + " | () () |\n" + " \\  ^  /\n"
                                    + "   |||||\n" + "   |||||\n";
    protected Storage storage;
    protected TaskList tasklist;

    /**
     * Prints the opening message when the program runs.
     */

    public String displayOpeningMessage() {
        return logo + "\nGreetings, I'm Taskmaster, I'm super grumpy 24/7"
                + "\nOkay, what do you want?\n";
    }

    /**
     * Prints the bye message displayed when the user exits.
     */

    public String displayByeMessage() {
        return "   See you around kiddo, I'm an angsty dude but deep down i'm a lonely man";
    }


    /**
     * Display Message when the user enters an invalid command.
     * @param input The input entered by the user
     */

    public String displayInvalidCommand(String input) {
        return "\n   " + input + "?"
                + "\n   What are you on about? \n Type 'help' if "
                + "you want to know the commands, kid!\n";
    }

    /**
     * Prints the list of commands that are in this program.
     */

    public String displayListOfCommand() {
        String result = "";
        result += "   Commands:\n";
        result += "   List\n     -List out all your current tasks\n";
        result += "   todo <task name>\n     -Add a todo task without any deadline"
                            + " specified\n";
        result += "   deadline <taskname> /by <Date><Time>\n     -Adds a task due by "
                            + "specified deadline\n";
        result += "   event <taskname> /at <Date><Time>\n     -Adds a task that occurs at the specified "
                            + "time\n";
        result += "   mark <task number>\n     -Marks task as completed\n";
        result += "   unmark <task number>\n     -Marks a completed task as uncompleted\n";
        result += "   delete <task number>\n     -Deletes the task at the specified index\n";
        result += "   bye\n     -Exits the program\n";
        result += "   find <keyword>\n     -Retrieves all tasks that contains the "
                            + "keyword\n";
        return result;
    }

    public String displayErrorMessage(String errorMsg) {
        return "   " + errorMsg;
    }
}

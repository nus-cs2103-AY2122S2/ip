package duke.commands;

/**
 * Class of all the valid commands user can input
 */
public class ShowAllCommands extends Command<String> {
    /**
     * Enumeration of all valid commands.
     */
    enum Commands { LIST, TODO, EVENT, DEADLINE, BYE, MARK, UNMARK, DELETE, FIND }
    /**
     * Constructor of this class to display out the valid commands for use.
     */
    public ShowAllCommands() {
        execute();
    }

    /**
     * Outputs the valid commands and format of the commands.
     */
    public String execute() {
        String response = "COMMANDS:\n";
        switch (Commands.LIST) {
        case LIST:
            response = response + "list    | Get a list of tasks you have\n";
            // Fallthrough
        case TODO:
            response += "todo    | One of the 3 tasks type. eg: todo_<yourtask>\n";
            // Fallthrough
        case EVENT:
            response += "event   | One of the 3 tasks type. eg: event_<yourtask>_/at_<YYYY-MM-DD HH:MM>\n";
            // Fallthrough
        case DEADLINE:
            response += "deadline| One of the 3 tasks type. eg: deadline_<yourtask>_/by_<YYYY-MM-DD HH:MM>";
            // Fallthrough
        case BYE:
            response += "bye     | End this phone call\n";
            // Fallthrough
        case UNMARK:
            response += "unmark  | Unmark the task as not done. eg: unmark_<task number>\n";
            // Fallthrough
        case MARK:
            response += "mark    | Mark the task as done. eg: mark_<task number>\n";
            // Fallthrough
        case DELETE:
            response += "delete  | Delete the task from list. eg: delete_<task number>\n";
            // Fallthrough
        case FIND:
            response += "find    | Find the tasks with a keyword from list. eg: find_<keyword>\n";
            // Fallthrough
        default:
            break;
        }
        return response;
    }

    /**
     * Hint to stop the bot from running.
     *
     * @return false to not stop the bot from running
     */
    public boolean isExit() {
        return false;
    }
}

package duke.commands;

import duke.ui.Ui;

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
    private void execute() {
        System.out.print(Ui.getLineDivider() + "COMMANDS:\n");
        switch (Commands.LIST) {
        case LIST:
            System.out.println("list    | Get a list of tasks you have");
            // Fallthrough
        case TODO:
            System.out.println("todo    | One of the 3 tasks type. eg: todo_<yourtask>");
            // Fallthrough
        case EVENT:
            System.out.println("event   | One of the 3 tasks type. eg: event_<yourtask>_/at_<YYYY-MM-DD HH:MM>");
            // Fallthrough
        case DEADLINE:
            System.out.println("deadline| One of the 3 tasks type. eg: deadline_<yourtask>_/by_<YYYY-MM-DD HH:MM>");
            // Fallthrough
        case BYE:
            System.out.println("bye     | End this phone call");
            // Fallthrough
        case UNMARK:
            System.out.println("unmark  | Unmark the task as not done. eg: unmark_<task number>");
            // Fallthrough
        case MARK:
            System.out.println("mark    | Mark the task as done. eg: mark_<task number>");
            // Fallthrough
        case DELETE:
            System.out.println("delete  | Delete the task from list. eg: delete_<task number>");
            // Fallthrough
        case FIND:
            System.out.println("find    | Find the tasks with a keyword from list. eg: find_<keyword>");
            // Fallthrough
        default:
            break;
        }
        System.out.println(Ui.getLineDivider());
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

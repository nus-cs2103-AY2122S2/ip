package duke.commands;

import duke.commands.Command;
import duke.ui.Ui;

/**
 * Class of all the valid commands user can input
 */
public class ShowAllCommands extends Command<String> {
    /**
     * Enumeration of all valid commands.
     */
    enum Commands {LIST, TODO, EVENT, DEADLINE, BYE, MARK, UNMARK, DELETE}

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
        System.out.print(Ui.lineDivider + "COMMANDS:\n");
        switch (Commands.LIST) {
            case LIST:
                System.out.println("list    | Get a list of tasks you have");
            case TODO:
                System.out.println("todo    | One of the 3 tasks type. eg: todo_<yourtask>");
            case EVENT:
                System.out.println("event   | One of the 3 tasks type. eg: event_<yourtask>_/at_<YYYY-MM-DD HH:MM>");
            case DEADLINE:
                System.out.println("deadline| One of the 3 tasks type. eg: deadline_<yourtask>_/by_<YYYY-MM-DD HH:MM>");
            case BYE:
                System.out.println("bye     | End this phone call");
            case UNMARK:
                System.out.println("unmark  | Unmark the task as not done. eg: unmark_<task number>");
            case MARK:
                System.out.println("mark    | Mark the task as done. eg: mark_<task number>");
            case DELETE:
                System.out.println("delete  | Delete the task from list. eg: delete_<task number>");
        }
        System.out.println(Ui.lineDivider);
    }

    /**
     * Hint to stop the bot from running.
     *
     * @return false to not stop the bot from running
     */
    public boolean isExit(){
        return false;
    }
}

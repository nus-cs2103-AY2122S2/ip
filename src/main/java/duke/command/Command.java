package duke.command;
import java.io.IOException;

import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Command represents the instructions interpreted by the parser.
 * It is to be executed by Duke.
 */
public abstract class Command {
    protected enum Dialogue {
        GREETING, FAREWELL, LIST, MARK, UNMARKED, ADDED, DELETE, NUMLEFT, LINE, LOADERROR, FIND, FINDDATE
    }

    /**
     * Execute the action of the command.
     *
     * @param task TaskList stored in Duke.
     * @param storage File storage of Duke.
     * @throws IOException If error in IO operation with Duke storage.
     */
    public abstract String execute(TaskList task, Storage storage) throws IOException;


    /**
     * Returns whether Duke should exit after the command is executed.
     *
     * @return True if Duke should exit afterwards.
     */
    public abstract boolean isExit();

    protected String speak(Dialogue option, Integer num) {
        String reply;
        switch (option) {
            case NUMLEFT: reply = "Now you have " + num.toString() + " task in the list\n";
                break;
            default: reply = "";
        }
        return reply;
    }

    protected String speak(Dialogue option) {
        String reply;
        switch (option) {
            case GREETING:
                String logo = " ____        _        \n"
                        + "|  _ \\ _   _| | _____ \n"
                        + "| | | | | | | |/ / _ \\\n"
                        + "| |_| | |_| |   <  __/\n"
                        + "|____/ \\__,_|_|\\_\\___|\n";
                reply = "Hello from\n" + logo + "\n";
                reply += "Hello! I'm Duke.\nWhat can I do for you?\n";
                break;
            case LOADERROR: reply = "There is no previously saved file to be loaded.\n";
                break;
            case MARK: reply = "Nice! I've marked this task as done:\n";
                break;
            case LINE: reply = "______________________________________\n";
                break;
            case UNMARKED: reply = "OK, I've marked this task as not done yet:\n";
                break;
            case DELETE: reply = "Noted. I've removed this task:\n";
                break;
            case ADDED: reply = "Got it. I've added this task:\n";
                break;
            case FIND: reply = "Here are the matching tasks in your list:\n";
                break;
            case FINDDATE: reply = "Here are the tasks on that date:\n";
                break;
            case LIST: reply = "Here are the tasks in your list:\n";
                break;
            case FAREWELL: reply = "Bye. Hope to see you again soon!\n";
                break;
            default: reply = "Are you finally done?";
                break;
        }
        return reply;
    }
}

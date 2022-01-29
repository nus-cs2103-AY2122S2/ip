package duke;

import java.io.IOException;
import java.text.ParseException;

/**
 * Runs the command for user to show the available commands available.
 */

public class HelpCommand extends Command {

    /**
     * Sends to the user interface the commands available for the user to use.
     *
     * @param taskList The TaskList of the current user.
     * @param ui The user interface to show messages to users.
     * @param storage The file system for reading and writing into the database.
     */
    @Override
    void runCommand(TaskList taskList, Ui ui, Storage storage) throws IOException, ParseException {
        String message = "Hello. You can run a few commands with this machine.\n" +
                "    1. Type todo to create a task at hand. (eg. todo homework today)\n" +
                "    2. Type event to create an event. (eg. event Career Fair /at 26/01/2022 10:00 AM)\n" +
                "    3. Type deadline to create an deadline. (eg. deadline CS2103 Assignement /by 29/01/2022 11:59 PM)\n" +
                "    4. Type list to see what are the tasks on hand.\n";
        ui.outputMessage(message);
    }
}

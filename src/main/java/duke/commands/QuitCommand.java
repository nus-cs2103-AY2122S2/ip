package duke.commands;

import duke.DukeList;
import duke.TextUI;

public class QuitCommand extends Command {
    /**
     * Checks if the command is a terminating one.
     *
     * @return boolean whether to terminate
     */
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Function to execute the command and get the result.
     *
     * @param dukeList dukeList object
     */
    @Override
    public String getResult(DukeList dukeList) {
        return TextUI.getGoodbyeMsg();
    }
}

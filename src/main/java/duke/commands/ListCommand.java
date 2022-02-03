package duke.commands;

import duke.DukeList;

public class ListCommand extends Command {
    /**
     * Function to execute the command and get the result.
     *
     * @param dukeList dukeList object
     */
    @Override
    public String getResult(DukeList dukeList) {
        return dukeList.toString();
    }
}

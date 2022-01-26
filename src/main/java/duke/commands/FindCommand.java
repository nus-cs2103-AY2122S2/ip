package duke.commands;

import duke.DukeList;
import duke.UI;

public class FindCommand extends Command {
    private final String keyword;

    /**
     * Constructor
     *
     * @param keyword index of the task
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Function to execute the command.
     *
     * @param dukeList dukeList object
     * @param ui ui object
     */
    @Override
    public void execute(DukeList dukeList, UI ui) {
        ui.printMsg(dukeList.find(keyword));
    }
}

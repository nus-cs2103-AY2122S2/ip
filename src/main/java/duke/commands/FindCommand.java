package duke.commands;

import duke.tasklist.DukeList;
import duke.ui.Ui;

public class FindCommand extends Command {

    private String toFind;

    public FindCommand(String phrase) {
        this.toFind = phrase;
    }

    @Override
    public void execute(Ui ui, DukeList list) {
        list.findTasks(toFind);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

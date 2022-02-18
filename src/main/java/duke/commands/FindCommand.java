package duke.commands;

import duke.tasklist.DukeList;
import duke.ui.Ui;

public class FindCommand extends Command {

    private String toFind;

    public FindCommand(String phrase) {
        this.toFind = phrase;
    }

    @Override
    public String execute(Ui ui, DukeList list) {
        return list.findTasks(toFind);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

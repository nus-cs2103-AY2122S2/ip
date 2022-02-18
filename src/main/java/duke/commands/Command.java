package duke.commands;

import duke.tasklist.DukeList;
import duke.ui.Ui;

public abstract class Command {

    public abstract String execute(Ui ui, DukeList list);
    public abstract boolean isExit();

}

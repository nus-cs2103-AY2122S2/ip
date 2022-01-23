package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.Storage;

public abstract class Command {

    public enum Keyword {
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, UNRECOGNIZED
    }

    private final Keyword keyword;
    private boolean modifiedTasks;

    public Command(Keyword keyword) {
        this.keyword = keyword;
    }

    public Keyword getKeyword() {
        return keyword;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

}

package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public abstract class Command {
    public enum CommandType {
        BYE,
        LIST,
        MARK,
        UNMARK,
        DELETE,
        DEADLINE,
        EVENT,
        SORT,
        TODO;

        public boolean equals(String input) {
            if (input.equalsIgnoreCase(this.name())) {
                return true;
            } else {
                return false;
            }
        }
    }

    protected String[] commandArray;

    public Command() {
        this.commandArray = new String[0];
    }

    public Command(String[] commandArray) {
        this.commandArray = commandArray;
    }

    public abstract void executeCommand(TaskList taskList, Ui ui, Storage storage);
}

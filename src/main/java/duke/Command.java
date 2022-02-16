package duke;

/**
 * Abstract command class
 */

abstract class Command {
    private final String command;

    Command(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    abstract String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;

    abstract boolean isExit();
}

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

    abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;

    abstract boolean isExit();
}

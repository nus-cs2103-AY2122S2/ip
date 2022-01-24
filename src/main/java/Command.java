public abstract class Command {
    String fullCommand;

    Command(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    abstract boolean isExit();

    abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
}

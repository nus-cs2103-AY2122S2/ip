package van;

public interface Command {
    boolean executeCommand(Ui ui, TaskList taskList, Storage storage);
}

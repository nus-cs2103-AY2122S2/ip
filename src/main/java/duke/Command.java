package duke;

/**
 * Command to handle user input
 *
 * @author brandonrhan
 * @version 0.0.0
 */
public abstract class Command {
    protected Ui ui;
    protected TaskList taskList;
    protected Storage storage;
    protected String detail;

    Command(Ui ui, TaskList taskList, Storage storage, String detail) {
        this.ui = ui;
        this.taskList = taskList;
        this.storage = storage;
        this.detail = detail;
    }

    abstract String runCommand();
}

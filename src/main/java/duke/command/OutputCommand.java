package duke.command;

import duke.Storage;
import duke.task.TaskList;
import duke.Ui;

/**
 * Represents commands which outputs to the user. A MarkCommand
 * object corresponds a valid Ekud command, which can then be executed.
 */
public class OutputCommand implements Command{

    private String fullCommand;
    private String[] splicedFullCommand;
    private String taskType;

    public OutputCommand(String fullCommand) {
        this.fullCommand = fullCommand;
        this.splicedFullCommand = fullCommand.split(" ",2);
        this.taskType = splicedFullCommand[0];
    }

    /**
     * Executes a valid Ekud command that prints all the tasks in the
     * task list. If task list is empty, a message will be shown to inform
     * the user.
     * @param tasks Task object.
     * @param storage Storage object.
     * @param ui Ui object.
     */
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        if (tasks.getTaskSize() == 0) {
            ui.showEmptyListMessage();
        } else {
            ui.showListMessage(tasks);
        }
    }

    /**
     * Returns a boolean value that tells the programme to exit.
     * @return Boolean value false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}

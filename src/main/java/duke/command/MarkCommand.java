package duke.command;

import duke.exception.DukeException;
import duke.function.Storage;
import duke.function.TaskList;
import duke.function.Ui;
import duke.task.Task;

public class MarkCommand extends Command {

    boolean isMark;
    int taskNumber;
    DukeException exception;

    public MarkCommand(String fullCommand) {
        super(fullCommand);
        String[] fullCommandSplit = fullCommand.split(" ");
        String command = fullCommandSplit[0];
        if (command.equals("mark")) {
            this.isMark = true;
        } else if (command.equals("unmark")) {
            this.isMark = false;
        }

        try {
            this.taskNumber = Integer.parseInt(fullCommandSplit[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            this.exception = new DukeException("Please input an item number when deleting (eg. 'mark 1')");
        } catch (NumberFormatException e) {
            this.exception = new DukeException("Please only input integers when deleting tasks (eg. 'mark 1");
        }

    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (this.exception != null) {
            throw this.exception;
        } else if (this.taskNumber <= 0 || this.taskNumber > tasks.size()) {
            throw new DukeException("Please only input integers within the range of your tasks");
        }

        Task task = tasks.getByNumber(this.taskNumber);
        if (this.isMark) {
            task.mark();
            ui.print(String.format("I've marked task %d as done! *quack*", this.taskNumber));
        } else {
            task.unmark();
            ui.print(String.format("I've marked task %d as undone! *quack*", this.taskNumber));
        }
        ui.print(String.format("  %d. %s", this.taskNumber, task.toString()));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

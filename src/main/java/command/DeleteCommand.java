package command;

import exception.DukeException;
import task.Task;
import task.TaskList;
import utility.UI;
import utility.Storage;

/**
 * Delete tasks from list for command class
 */
public class DeleteCommand extends Command {

    int index;
    DukeException dukeException;

    public DeleteCommand(String command){
        super(command);
        String[] commandSplit = command.split(" ");
        try {
            this.index = Integer.parseInt(commandSplit[1]);
        } catch (ArrayIndexOutOfBoundsException e){
            this.dukeException = new DukeException("out of array list");
        }
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        if (this.dukeException != null) {
            throw this.dukeException;
        } else if (this.index <= 0 || this.index > tasks.getSize()) {
            throw new DukeException("only input integers within the range");
        }

        Task removedTask = tasks.deleteByNumber(this.index);
        ui.print(String.format("I've deleted task %d!", this.index));
        ui.print(String.format("  %s", removedTask.toString()));

        storage.save(tasks);
    }




    @Override
    public boolean isExit() {
        return false;
    }

}

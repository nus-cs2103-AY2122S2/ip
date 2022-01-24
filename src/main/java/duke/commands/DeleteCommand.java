package duke.commands;

import java.io.IOException;

import duke.common.DukeException;
import duke.common.Utils;
import duke.constants.Constants;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class DeleteCommand extends Command {
    private String args;

    public DeleteCommand(String args) {
        this.args = args;
    }

    
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        try {
            if (this.args.length() == 0) {
                throw new IllegalArgumentException();
            }

            if (!Utils.isNumeric(this.args, ui)) {
                throw new NumberFormatException();
            }

            int index = Integer.parseInt(this.args);
            int noOfTasks = taskList.size();
            String task = taskList.getTasks().get(index - 1).toString();
            
            taskList.delete(--index);
            storage.writeToFile("", index, true);
            noOfTasks = taskList.size();

            String result = "Noted. I've removed this task:\n";
            String pluralTask = noOfTasks > 1 ? "tasks" : "task";

            result += noOfTasks > 0 ?
                    "  " + task +
                    "\nNow you have " + noOfTasks + " " + pluralTask + " in the list." :
                    "  " + task +
                    "\nNow you have no task left.";

            ui.output(result);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(Constants.INVALID_INDEX_MSG);
        } catch (IllegalArgumentException e) {
            throw new DukeException(Constants.INVALID_DELETE_MSG);
        } catch (IOException e) {
            throw new DukeException(Constants.STORAGE_DELETE_MSG);
        }
    }
}

package duke.command;

import duke.dukeexceptions.DeleteException;
import duke.dukeexceptions.DukeException;
import duke.Storage;
import duke.task.Task;
import duke.TaskList;
import duke.Ui;

import java.io.IOException;

public class DeleteCommand extends Command {
    public DeleteCommand(String input) {
        super(input);
    }

    /**
     * Delete a task in task list and save it to the data file
     *
     * @param taskList currentTaskList
     * @param ui ui class that helps to print suitable command
     * @param storage storage that manage saving and loading data
     * @throws DukeException an error message
     * @throws IOException error saving to the file
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException, IOException {
        String[] parts = input.split(" ");
        try {
            if (parts.length == 1) {
                ui.showWhichTaskIsBeingDeleted();
            }
            if (parts.length > 2) {
                throw new NumberFormatException();
            }
            int index = Integer.parseInt(parts[1]) - 1;
            Task taskToBeDelete = taskList.get(index);
            taskList.remove(index);
            ui.showDeletedTask(taskToBeDelete, taskList);
            storage.saveFile(taskList);
        } catch (IndexOutOfBoundsException e) {
            throw new DeleteException("This task does not exist, there are " + taskList.size() + " tasks now");
        }
        catch (NumberFormatException e) {
            throw new DeleteException("format must be: delete <task number> , other format is not acceptable");
        }
    }
}

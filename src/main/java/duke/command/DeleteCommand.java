package duke.command;

import java.io.IOException;

import duke.dukeexceptions.DukeException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.ui.Ui;
import duke.task.Task;

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
     * @return a response to user input
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException, IOException {
        String result = "";
        String[] parts = input.split(" ");
        try {
            if (parts.length == 1) {
                return ui.showWhichTaskIsBeingDeleted();
            }
            if (parts.length > 2) {
                return "You have entered too many arguments!\n";
            }
            int index = Integer.parseInt(parts[1]) - 1;
            Task taskToBeDelete = taskList.get(index);
            taskList.remove(index);
            result += ui.showDeletedTask(taskToBeDelete, taskList);
            storage.saveFile(taskList);
            return result;
        } catch (IndexOutOfBoundsException e) {
            return "This task does not exist, there are " + taskList.size() + " tasks now\n";
        } catch (NumberFormatException e) {
            return "format must be: delete <task number> , other format is not acceptable\n";
        }
    }
}

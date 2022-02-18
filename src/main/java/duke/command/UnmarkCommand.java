package duke.command;

import java.io.IOException;

import duke.dukeexceptions.DukeException;
import duke.dukeexceptions.UnmarkException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.ui.Ui;
import duke.task.Task;

public class UnmarkCommand extends Command {

    public UnmarkCommand(String input) {
        super(input);
    }
    /**
     * Marks a task as done
     *
     * @param taskList current list of tasks
     * @param ui User interface to print output
     * @param storage storage that helps to save data into the file
     * @return a response to user
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException, IOException {
        String result = "";
        try {
            String[] parts = input.split(" ");
            int index = Integer.parseInt(parts[1]) - 1;
            Task markTask = taskList.get(index);
            if (!markTask.isDone()) {
                result += ui.showMarkTaskAsAlreadyUndone(markTask);
                result += markTask.markAsDone();
            } else {
                result += ui.showMarkTaskAsUndone(markTask);
            }
            storage.saveFile(taskList);
            return result;
        } catch (IndexOutOfBoundsException e) {
            throw new UnmarkException("You have entered invalid task or that task does not exist!");
        }
    }
}

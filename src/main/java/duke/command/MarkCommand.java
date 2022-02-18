package duke.command;

import java.io.IOException;

import duke.dukeexceptions.DukeException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.ui.Ui;
import duke.task.Task;


public class MarkCommand extends Command {


    public MarkCommand(String input) {
        super(input);
    }

    /**
     * Marks a task as done
     *
     * @param taskList current list of tasks
     * @param ui User interface to print output
     * @param storage storage that helps to save data into the file
     * @throws DukeException an error message
     * @throws IOException error saving to the file
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException, IOException {
        String result = "";
        try {
            String[] parts = input.split(" ");
            int index = Integer.parseInt(parts[1]) - 1;
            Task markTask = taskList.get(index);
            if (markTask.isDone()) {
                result += ui.showMarkTaskAsAlreadyDone(markTask);
            } else {
                result += ui.showMarkTaskAsDone(markTask);
            }
            storage.saveFile(taskList);
            return result;
        } catch (IndexOutOfBoundsException e) {
            return "You have entered invalid task or that task does not exist!";
        }
    }
}

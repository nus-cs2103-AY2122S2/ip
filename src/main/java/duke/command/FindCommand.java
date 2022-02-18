package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.IOException;

/**
 * duke.command.Command to find a duke.task.Task based on a keyword.
 */
public class FindCommand extends Command {
    private final String[] inputs;

    /**
     * Constructor to keep track of the keyword used.
     * @param req Used to find a task.
     */
    public FindCommand(String ... req) {

        this.inputs = req;
    }

    /**
     * Goes through the TaskList to search for related Tasks.
     * @param taskList
     * @param ui
     * @param storage
     * @throws IOException
     */
    public String perform(TaskList taskList, Ui ui, Storage storage) throws IOException {

        return ui.printReq(taskList.findTasks(this.inputs));

    }
}
package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

public class UnmarkCommand extends Command {
    private String unmarkId;
    static final String OOB_RESPONSE = "Sorry, I could not find the item \\(T.T)/\n" +
            "Please type 'list' to view your current entries.";

    public UnmarkCommand(String id) {
        unmarkId = id;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Task currentTask = tasks.getTask(Integer.parseInt(unmarkId) - 1);
            currentTask.setNotDone();
            String message = currentTask.getTask();
            System.out.println("Sure, I have unmarked the following task:\n" + message);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(OOB_RESPONSE);
        }
    }
}

package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

public class MarkCommand extends Command {
    private String markId;
    static final String OOB_RESPONSE = "Sorry, I could not find the item \\(T.T)/\n" +
            "Please type 'list' to view your current entries.";

    public MarkCommand(String id) {
        markId = id;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Task currentTask = tasks.getTask(Integer.parseInt(markId) - 1);
            currentTask.setDone();
            String message = currentTask.getTask();
            System.out.println("Ok, I have marked the following task:\n" + message);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(OOB_RESPONSE);
        }
    }
}

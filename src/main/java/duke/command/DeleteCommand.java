package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

public class DeleteCommand extends Command {
    private String deleteId;
    static final String OOB_RESPONSE = "Sorry, I could not find the item \\(T.T)/\n" +
            "Please type 'list' to view your current entries.";

    public DeleteCommand(String id) {
        deleteId = id;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Task currTask = tasks.removeTask(Integer.parseInt(deleteId) - 1);
            System.out.println("I have removed this from your tasks:\n" + currTask.getTask());
            System.out.println("You now have " + tasks.getSize() + " tasks");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(OOB_RESPONSE);
        }
    }
}

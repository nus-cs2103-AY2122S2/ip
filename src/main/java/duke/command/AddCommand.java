package duke.command;

import duke.exception.DukeException;
import duke.exception.ErrorString;
import duke.extract.Extract;
import duke.gui.Ui;
import duke.storage.Storage;
import duke.task.DeadLine;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;

/**
 * AddCommand is a command that handles add instructions.
 *
 * @author yl-ang
 */
public class AddCommand extends Command {

    private boolean isExit = false;
    private Task task;

    /**
     * Constructs AddCommand object with the supplied user input and taskCommand.
     *
     * @param data Full user input.
     * @param taskCommand Task command specified by the user.
     * @throws DukeException If no task description or no date and time is supplied.
     */
    public AddCommand(String data, String taskCommand) throws DukeException {
        Task task;

        String[] temp = data.split(" ");

        if (taskCommand.equals("todo")) {

            if (temp.length == 1) {
                throw new DukeException(ErrorString.ERROR_EMPTY_TODO_DESC.toString());
            }

            String desc = Extract.extractDesc(data, "todo ", null);
            task = new ToDo(desc);

        } else if (taskCommand.equals("deadline")) {

            if ((temp.length == 1) || (temp[1].equals("/by"))) {
                throw new DukeException(ErrorString.ERROR_EMPTY_DEADLINE_DESC.toString());
            }

            if (!data.contains("/by")) {
                throw new DukeException(ErrorString.ERROR_EMPTY_DEADLINE_DATE.toString());
            }

            String desc = Extract.extractDesc(data, "deadline ", "/by");
            String date = Extract.extractTime(data, "/by ", null);

            task = new DeadLine(desc, date);

        } else {

            // Event handling

            if ((temp.length == 1) || (temp[1].equals("/at"))) {
                throw new DukeException(ErrorString.ERROR_EMPTY_EVENT_DESC.toString());
            }

            if (!data.contains("/at")) {
                throw new DukeException(ErrorString.ERROR_EMPTY_EVENT_DATE.toString());
            }

            String desc = Extract.extractDesc(data, "event ", "/at");
            String date = Extract.extractTime(data, "/at ", null);
            task = new Event(desc, date);
        }

        this.task = task;
    }


    /**
     * Executes AddCommand with the objects supplied.
     *
     * @param taskLst TaskLst object containing the current tasks.
     * @param ui Ui object containing the user interface messages and scanner object.
     * @param storage Storage object containing the methods to load and save.
     * @return Formatted response from executing command.
     */
    @Override
    public String execute(TaskList taskLst, Ui ui, Storage storage) {
        taskLst.addToList(this.task);
        storage.save(taskLst.getTaskLst());
        String response = ui.addedTasksResponse(this.task, taskLst.getTaskLst());
        assert response != null : "Add response should not be null";
        return response;
    }

    /**
     * Checks and returns the boolean whether if command is exit.
     *
     * @return Boolean if the command is exit.
     */
    @Override
    public boolean isExit() {
        return this.isExit;
    }
}

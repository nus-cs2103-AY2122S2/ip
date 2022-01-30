package duke.command;

import duke.action.Action;
import duke.ui.Storage;
import duke.ui.TaskList;
import duke.ui.Ui;
import duke.action.Deadline;
import duke.action.Event;
import duke.action.Todo;

/**
 * An extension from Command. AddCommand has the Action
 * variable, action that is specific to it.
 * The AddCommand class deals with the addition
 * of a task to a given taskList.
 */
public class AddCommand extends Command {

    private final Action action;

    /**
     * Constructs a new AddCommand object with
     * String variables, command and details.
     * @param command command etc; deadline ,event, todo
     * @param details details of the task etc; anything after the command
     */
    public AddCommand(String command, String details) {
        if (command.equals("todo")) {
            action = new Todo(details);
        } else {
            if (command.equals("deadline")) {
                String[] thisAction = details.split("/by ");
                String act = thisAction[0];
                String date = thisAction[1];
                action = new Deadline(act, date);
            } else {
                String[] thisAction = details.split("/at ");
                String act = thisAction[0];
                String date = thisAction[1];
                action = new Event(act, date);
            }
        }
    }

    /**
     * Returns a String indicating the result of
     * the addition of a task to the tasklist.
     * Executes the adding of a task onto the taskList
     * as well as saving the contents of the taskList
     * onto the file.
     * @param taskList a list of the current tasks
     * @param ui user interface
     * @param storage file storage
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        //storage must save
        taskList.add(action);
        storage.save(taskList);
        return new StringBuilder().append("Got it. I have added this task:\n  ")
                .append(action).append("\nNow you have ").append(taskList.size())
                .append(" tasks in the list.").toString();
    }

    /**
     * Returns false for non-ExitCommands.
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}

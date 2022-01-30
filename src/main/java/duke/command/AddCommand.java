package java.duke.command;

import java.duke.action.Action;
import java.duke.ui.Storage;
import java.duke.ui.TaskList;
import java.duke.ui.Ui;
import java.duke.action.Deadline;
import java.duke.action.Event;
import java.duke.action.Todo;

public class AddCommand extends Command {

    private final Action action;

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
     * Executes the adding of a task onto the taskList
     * as well as saving the contents of the taskList
     * onto the file.
     * @param taskList a list of the current tasks
     * @param ui user interface
     * @param storage file storage
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        //storage must save
        taskList.add(action);
        System.out.println(new StringBuilder().append("Got it. I have added this task:\n  ")
                .append(action).append("\nNow you have ").append(taskList.size())
                .append(" tasks in the list.").toString());
        storage.save(taskList);
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

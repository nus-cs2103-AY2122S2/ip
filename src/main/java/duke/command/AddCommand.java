package duke.command;
import duke.*;
import duke.action.Action;
import duke.action.Deadline;
import duke.action.Event;
import duke.action.Todo;

public class AddCommand extends Command {
    Action action;

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

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        //storage must save
        taskList.add(action);
        System.out.println("Got it. I have added this task:\n  " + action +
                "\nNow you have " + taskList.size() + " tasks in the list.");
        storage.save(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

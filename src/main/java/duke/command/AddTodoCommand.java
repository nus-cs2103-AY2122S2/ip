package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.TaskList;
import duke.task.Todo;

public class AddTodoCommand extends Command {
    private String description;
    private static final String DESC_RESPONSE = "Oops! \\(@.@)/ You have not keyed in a description for the duke.task!\n" +
            "Let's try again ~(^.^)~\n" +
            "Type 'help' if you need to know how to use this duke.command";

    public AddTodoCommand(String input) {
        description = input;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (description.length() == 0) {
            throw new DukeException(DESC_RESPONSE);
        } else {
            System.out.println("des"+ description);
            Todo entry = new Todo(description);
            tasks.addTask(entry);
            String message = entry.getTask();
            System.out.println("I have added the following todo:\n" + message);
            System.out.println("You now have " + tasks.getSize() + " tasks");
        }
    }
}

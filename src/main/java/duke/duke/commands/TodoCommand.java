package duke.commands;

import duke.info.task.Todo;

public class TodoCommand extends AddCommand {

    public TodoCommand(String input) {
        super(new Todo(input, false));
    };

}

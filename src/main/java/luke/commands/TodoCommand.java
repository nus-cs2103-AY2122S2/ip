package luke.commands;

import luke.data.tasks.Todo;

public class TodoCommand extends AddCommand {

    public static final CommandAction COMMAND_ACTION = CommandAction.TODO;

    public TodoCommand(Todo todo) {
        super(todo);
    }
}

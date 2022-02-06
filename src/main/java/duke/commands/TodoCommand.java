package duke.commands;

import duke.info.task.Todo;

public class TodoCommand extends AddCommand {

    /**
     * Constructs a TodoCommand with the specified input
     * @param input - the input used to create the Todo
     */
    public TodoCommand(String input) {
        super(new Todo(input, false));
    };

}

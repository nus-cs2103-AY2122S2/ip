package duke;

import java.io.IOException;

public class TodoCommand extends Command {

    /**
     * Executes the task based on the responsibility of the todo command.
     *
     * @param taskList current list of task in the hard disk.
     * @param userInputTask user input command to the input.
     * @param userInputs separated words from user input.
     * @param storage the hard disk.
     * @return String description of the execution result.
     * @throws IOException
     */
    @Override
    public String execute(TaskList taskList, String userInputTask, String[] userInputs, Storage storage) {
        // handle error from empty task description
        try {
            return Parser.parserTodo(taskList, userInputTask, storage);
        } catch (DukeException | IOException e) {
            return "OOPS!!! The description of a todo cannot be empty.";
        }
    }
}

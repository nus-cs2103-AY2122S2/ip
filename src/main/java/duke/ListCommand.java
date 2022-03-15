package duke;

import java.io.IOException;

public class ListCommand extends Command {

    /**
     * Executes the task based on the responsibility of the list command.
     *
     * @param taskList current list of task in the hard disk.
     * @param userInputTask user input command to the input.
     * @param userInputs separated words from user input.
     * @param storage the hard disk.
     * @return String description of the execution result.
     */
    @Override
    public String execute(TaskList taskList, String userInputTask, String[] userInputs, Storage storage) {
        return Parser.parserList(taskList);
    }
}

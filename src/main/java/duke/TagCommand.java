package duke;

import java.io.IOException;

public class TagCommand extends Command {

    /**
     * Executes the task based on the responsibility of the tag command.
     *
     * @param taskList current list of task in the hard disk.
     * @param userInputTask user input command to the input.
     * @param userInputs separated words from user input.
     * @param storage the hard disk.
     * @return String description of the execution result.
     * @throws IOException
     */
    @Override
    public String execute(TaskList taskList, String userInputTask,
                          String[] userInputs, Storage storage) throws IOException {
        return Parser.parserTag(taskList, userInputs, storage);
    }
}

package duke;

import java.io.IOException;

public class DeleteCommand extends Command {

    /**
     * Executes the task based on the responsibility of the delete command.
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
        try {
            Parser.parserDeleteValidator(taskList, userInputTask);
        } catch (DukeException e) {
            if (e.getMessage().equals("Delete command must have a specified task number to be deleted.")) {
                return "Delete command must have a specified task number to be deleted.";
            } else {
                return "Invalid task number to be deleted.";
            }
        }
        return Parser.parserDelete(taskList, userInputs, storage);
    }
}

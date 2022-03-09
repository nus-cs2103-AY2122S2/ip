package duke.command;

import duke.exception.InvalidIndexException;
import duke.util.Storage;
import duke.util.TasksList;

public class UnmarkCommand extends Command {
    private String[] instruction;

    public UnmarkCommand(String[] instruction) {
        this.instruction = instruction;
    }

    @Override
    public String execute(TasksList taskList, Storage storage) throws InvalidIndexException {
        int taskNum = Integer.parseInt(instruction[1]);
        //execute the command
        String response = taskList.unmark(taskNum);
        //save to storage
        new SaveCommand().execute(taskList, storage);
        return response;
    }
}

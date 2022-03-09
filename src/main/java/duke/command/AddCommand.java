package duke.command;

import java.util.Arrays;

import duke.util.Storage;
import duke.util.TasksList;
import duke.exception.InvalidArgumentException;

public class AddCommand extends Command {
    private String[] instruction;

    public AddCommand(String[] instruction) {
        this.instruction = instruction;
    }

    @Override
    public String execute(TasksList taskList, Storage storage) throws InvalidArgumentException {
        // execute the command
        String response = taskList.addTask(Arrays.asList(instruction));
        // save to storage
        new SaveCommand().execute(taskList, storage);
        return response;
    }
}

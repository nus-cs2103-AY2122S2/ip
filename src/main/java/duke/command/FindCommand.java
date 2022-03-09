package duke.command;

import java.util.Arrays;

import duke.exception.InvalidArgumentException;
import duke.util.Storage;
import duke.util.TasksList;

public class FindCommand extends Command {
    private String[] instruction;

    public FindCommand(String[] instruction) {
        this.instruction = instruction;
    }

    @Override
    public String execute(TasksList taskList, Storage storage) throws InvalidArgumentException {
        return taskList.findMatchingTasks(Arrays.asList(instruction));
    }
}

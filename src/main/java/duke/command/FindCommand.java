package duke.command;
import duke.TasksList;
import duke.Storage;
import duke.exception.InvalidArgumentException;

import java.util.Arrays;

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

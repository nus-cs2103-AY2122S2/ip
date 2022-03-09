package duke.command;
import duke.TasksList;
import duke.Storage;
import duke.exception.InvalidArgumentException;

import java.util.Arrays;

public class ReminderCommand extends Command {
    private String[] instruction;

    ReminderCommand(String[] instruction) {
        this.instruction = instruction;
    }

    @Override
    public String execute(TasksList taskList, Storage storage) throws InvalidArgumentException {
        return taskList.getTasksUnder(Arrays.asList(instruction));
    }
}

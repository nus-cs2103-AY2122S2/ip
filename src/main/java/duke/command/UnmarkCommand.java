package duke.command;
import duke.TasksList;
import duke.Storage;
import duke.exception.InvalidIndexException;

public class UnmarkCommand extends Command {
    private String[] instruction;

    UnmarkCommand(String[] instruction) {
        this.instruction = instruction;
    }

    @Override
    public String execute(TasksList taskList, Storage storage) throws InvalidIndexException {
        int taskNum = Integer.parseInt(instruction[1]);
        return taskList.unmark(taskNum);
    }
}

package duke.command;
import duke.TasksList;
import duke.Storage;
import duke.exception.InvalidIndexException;

public class DeleteCommand extends Command {
    private String[] instruction;

    public DeleteCommand(String[] instruction) {
        this.instruction = instruction;
    }

    @Override
    public String execute(TasksList taskList, Storage storage) throws InvalidIndexException {
        int taskNum = Integer.parseInt(instruction[1]);
        // execute the command
        String response =  taskList.deleteTask(taskNum);
        // save to storage
        new SaveCommand().execute(taskList, storage);
        return response;
    }
}

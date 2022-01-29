package apollo.commands;

import apollo.exceptions.ApolloOutOfBoundsException;
import apollo.tasks.Task;

public class DeleteCommand extends Command {

    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }
    @Override
    public String execute() throws ApolloOutOfBoundsException {
        if (index < 0 || index >= taskList.taskCount()) {
            throw new ApolloOutOfBoundsException();
        }
        Task deleted = taskList.deleteTask(index);
        return String.format("Alright, I've removed this task. \n"
                + "  %s\nThere's a total of %d tasks now. ", deleted, taskList.taskCount());
    }
}

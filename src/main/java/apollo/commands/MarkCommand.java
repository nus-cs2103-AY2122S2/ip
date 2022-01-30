package apollo.commands;

import apollo.exceptions.ApolloOutOfBoundsException;
import apollo.tasks.Task;

public class MarkCommand extends Command {

    private final boolean isDone;
    private final int index;

    public MarkCommand(int index, boolean isDone) {
        this.isDone = isDone;
        this.index = index;
    }

    @Override
    public String execute() throws ApolloOutOfBoundsException {
        if (index < 0 || index >= taskList.taskCount()) {
            throw new ApolloOutOfBoundsException();
        }

        String doneStatus = isDone ? "done" : "not done";
        Task task = taskList.markTask(index, isDone);
        return String.format("I have marked the following task as %s\n\t%s",
                doneStatus, task);
    }
}

package kenobi.command;

import kenobi.task.Task;

public class DeleteCommand extends Command {
    private int toDeleteIndex;

    public DeleteCommand(int index) {
        toDeleteIndex = index -1;
    }

    @Override
    public String execute() {
        try {
            Task deletedTask = tasks.remove(toDeleteIndex);
            return "I removed the following task:\n" + deletedTask;
        } catch (IndexOutOfBoundsException e) {
            return "The task you are trying to delete is not in the archives.";
        }
    }
}

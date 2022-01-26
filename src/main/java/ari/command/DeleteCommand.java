package main.java.ari.command;

import main.java.ari.tasks.Task;

public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";

    private int indexToDelete;

    private static final String DELETE_MESSAGE = "Yes Master, I have removed this task:\n" + "\t    %s\n"
            + "\tYou have %d task(s) currently";

    public DeleteCommand(int index) {
        this.indexToDelete = index - 1;
    }

    @Override
    public String execute() {
        if (indexToDelete < 0 || indexToDelete >= taskList.getSize()) {
            return "Sorry Master, the item you chose is not in the list";
        }

        Task task = taskList.getTask(indexToDelete);
        taskList.deleteTask(indexToDelete);
        return String.format(DELETE_MESSAGE, task, taskList.getSize());
    }
}
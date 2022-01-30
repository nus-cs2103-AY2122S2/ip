package main.commands;

import main.DukeException;
import main.enums.CommandType;
import main.tasks.Task;

public class CDelete extends Command{
    protected int deleteIndex;

    public CDelete(int deleteIndex) {
        super(CommandType.DELETE);
        this.deleteIndex = deleteIndex;
    }

    public int getDeleteIndex() {
        return this.deleteIndex;
    }

    @Override
    public void runCommand() throws DukeException {
        try {
            Task deleteTask = Task.getTasks().get(this.getDeleteIndex());
            Task.deleteTask(this.getDeleteIndex());
            System.out.printf("Noted. I've removed this task: \n" + "    %s\n"
                    + "%s\n", deleteTask, Task.taskCountToString());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please check that you have entered the correct index.");
        }
    }
}

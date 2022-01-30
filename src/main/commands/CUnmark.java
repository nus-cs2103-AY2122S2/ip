package main.commands;

import main.DukeException;
import main.enums.CommandType;
import main.tasks.Task;

public class CUnmark extends Command{
    protected int unmarkIndex;

    public CUnmark(int unmarkIndex) {
        super(CommandType.UNMARK);
        this.unmarkIndex = unmarkIndex;
    }

    public int getUnmarkIndex() {
        return this.unmarkIndex;
    }

    @Override
    public void runCommand() throws DukeException {
        try {
            Task unmarkTask = Task.getTasks().get(this.getUnmarkIndex());
            unmarkTask.setIsDone(false);
            System.out.printf("Ok, I've marked this task as not done yet: \n"
                    + "    %s\n", unmarkTask);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please check that you have entered the correct index.");
        }
    }
}

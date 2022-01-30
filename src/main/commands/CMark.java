package main.commands;

import main.DukeException;
import main.enums.CommandType;
import main.tasks.Task;

public class CMark extends Command{
    protected int markIndex;

    public CMark(int markIndex) {
        super(CommandType.MARK);
        this.markIndex = markIndex;
    }

    public int getMarkIndex() {
        return this.markIndex;
    }

    @Override
    public void runCommand() throws DukeException {
        try {
            Task markTask = Task.getTasks().get(this.getMarkIndex());
            markTask.setIsDone(true);
            System.out.printf("Nice! I've marked this task as done: \n"
                    + "    %s\n", markTask);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please check that you have entered the correct index.");
        }
    }
}

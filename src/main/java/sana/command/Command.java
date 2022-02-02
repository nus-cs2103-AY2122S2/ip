package sana.command;

import sana.Memory;
import sana.SanaResponse;
import sana.TaskList;

public abstract class Command {
    protected SanaResponse sanaResponse = new SanaResponse();
    protected Memory memory = new Memory();

    public abstract String executeCommand(String[] args, TaskList taskList);

}

package sana.command;

import sana.SanaResponse;
import sana.TaskList;

public class ByeCommand extends Command {

    @Override
    public String executeCommand(String[] args, TaskList taskList) {
        return sanaResponse.bye();
    }
}

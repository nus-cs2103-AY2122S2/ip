package sana.command;

import sana.TaskList;

public class ByeCommand extends Command {
    /**
     * Supports the bye command
     *
     * @param args  the arguments in the command
     * @param taskList  sana's tasklist
     *
     * @return  sana's response to the command
     */
    @Override
    public String executeCommand(String[] args, TaskList taskList) {
        return sanaResponse.bye();
    }
}

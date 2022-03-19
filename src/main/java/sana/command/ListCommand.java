package sana.command;

import sana.TaskList;

public class ListCommand extends Command {
    /**
     * Executes the list command
     *
     * @param args  arguments in the command
     * @param taskList  sana's tasklist
     *
     * @return  sana's response to the command
     */
    @Override
    public String executeCommand (String[] args, TaskList taskList) {
        return sanaResponse.printTaskList(taskList.toList(), false);
    }
}

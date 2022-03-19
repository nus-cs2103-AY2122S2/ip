package sana.command;

import java.util.LinkedList;

import sana.TaskList;
import sana.task.Task;

/**
 * Encapsulates the FindCommand
 */
public class FindCommand extends Command {

    /**
     * Executes the find command
     *
     * @param args  arguments in the command
     * @param taskList  sana's tasklist
     *
     * @return  sana's response to the command
     */
    @Override
    public String executeCommand(String[] args, TaskList taskList) {
        String keyword = args[1];
        LinkedList<Task> matchingTasks = taskList.findMatchingTasks(keyword);
        return sanaResponse.printTaskList(matchingTasks, true);
    }
}

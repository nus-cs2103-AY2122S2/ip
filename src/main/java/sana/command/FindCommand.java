package sana.command;

import sana.TaskList;
import sana.task.Task;

import java.util.LinkedList;

/**
 * Encapsulates the FindCommand
 */
public class FindCommand extends Command {

    /**
     * Executes the find command
     *
     * @param args  user input together with the find command
     * @param taskList  tasklist for the command to act on
     * @return
     */
    @Override
    public String executeCommand(String[] args, TaskList taskList) {
        String keyword = args[1];
        LinkedList<Task> matchingTasks = taskList.findMatchingTasks(keyword);
        return sanaResponse.printTaskList(matchingTasks, true);
    }
}

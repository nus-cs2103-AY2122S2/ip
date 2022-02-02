package sana.command;

import sana.TaskList;
import sana.task.Task;

import java.util.LinkedList;

public class FindCommand extends Command {
    @Override
    public String executeCommand(String[] args, TaskList taskList) {
        String keyword = args[1];
        LinkedList<Task> matchingTasks = taskList.findMatchingTasks(keyword);
        return sanaResponse.printTaskList(matchingTasks, true);
    }
}

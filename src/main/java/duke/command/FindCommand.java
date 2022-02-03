package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.util.Storage;

public class FindCommand extends Command {
    private static final String NO_INPUT = "I can't find the tasks you want if YOU DON'T TELL ME WHAT TO FIND. GIVE ME KEYWORDS!!";

    public FindCommand(String key) {
        super(key);
    }

    @Override
    public String execute(String input, TaskList taskList, Storage storage) throws DukeException {
        String taskDescription = getTaskDescription(input, NO_INPUT);

        TaskList filteredTasks = taskList.getTaskListWithKeyword(taskDescription);
        if (filteredTasks.getTaskListSize() == 0) {
           return "No tasks with the keyword : " 
                    + taskDescription + "\nYou need to put the exact keyword, it is not case sensitive.\n";
        }

        return "Here are the matching tasks in your list base on the keyword " 
                + taskDescription  + ": \n" + filteredTasks.getTaskListStr();
    }
}

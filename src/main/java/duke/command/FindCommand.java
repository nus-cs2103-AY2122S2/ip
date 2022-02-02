package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.util.Storage;
import duke.util.Ui;

public class FindCommand extends Command {
    private static final String NO_INPUT = "I can't find the tasks you want if YOU DON'T TELL ME WHAT TO FIND. GIVE ME KEYWORDS!!";

    public FindCommand(String key) {
        super(key);
    }

    @Override
    public void execute(String input, TaskList taskList, Storage storage, Ui ui) throws DukeException {
        String taskDescription = getTaskDescription(input, NO_INPUT);

        TaskList filteredTasks = taskList.getTaskListWithKeyword(taskDescription);
        if (filteredTasks.getTaskListSize() == 0) {
            ui.printResponse("No tasks with the keyword : " 
                    + taskDescription + "\nYou need to put the exact keyword, it is not case sensitive.\n");
        }

        ui.printResponse(("Here are the matching tasks in your list base on the keyword " 
                + taskDescription  + ": \n" + filteredTasks.getTaskListStr()));
    }
}

package duke.commands;

import duke.ui.Ui;
import duke.tasklist.TaskList;

public class ListCommand extends Command {

    /**
     * Display the current list of tasks
     *
     * @param taskList the list of tasks
     */
    public ListCommand(TaskList taskList) {
        System.out.println("Here are the task(s) in your list:");
        Ui.printTasks(taskList.getTasks());
    }
}

package duke.commands;

import duke.tasklist.TaskList;
import duke.tasks.Task;
import duke.ui.Ui;

import java.util.ArrayList;

public class ListCommand extends Command {

    /**
     * Display the current list of tasks
     *
     * @param taskList the list of tasks
     */
    public ListCommand(TaskList taskList) {
    }

    @Override
    public String execute(TaskList tasks, Ui ui) {
        return "Here are your task(s) in your list: "
                + Ui.printTasks(tasks.getTasks());
    }
}

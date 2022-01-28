package duke.Commands;

import duke.ui.Ui;
import duke.tasklist.TaskList;

public class ListCommand {

    public ListCommand(TaskList taskList) {
        System.out.println("Here are the task(s) in your list:");
        Ui.printTasks(taskList.getTasks());
    }
}

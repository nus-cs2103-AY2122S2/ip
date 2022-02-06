package duke;

import java.util.ArrayList;

/**
 * Marks the indicated task as undone
 */
public class UnmarkCommand extends Command {
    private String input; // Parameters: 1
    public ArrayList<Task> taskList = TaskList.taskList;
    private int index; // Index of target task

    public UnmarkCommand(String i) {
        this.input = i;
        index = Integer.parseInt(input) - 1; // -1 because list starts at 1 while indexes start at 0
    }

    @Override
    public void execute() {
        int index = Integer.parseInt(input) - 1; // -1 because list starts at 1 while indexes start at 0
        Task task = TaskList.taskList.get(index);
        task.unmarkAsDone();

        Ui.printUnmarkTask(task);
    }
}
import java.io.IOException;
import java.util.ArrayList;

/**
 * Deletes the indicated task
 */
public class DeleteCommand extends Command {
    private String input; // Parameters: 1
    public ArrayList<Task> Storage = TaskList.taskList;
    private int index; // Index of target task

    public DeleteCommand(String i) {
        this.input = i;
        index = Integer.parseInt(input) - 1; // -1 because list starts at 1 while indexes start at 0
    }

    @Override
    public void execute() {
        int index = Integer.parseInt(input) - 1; // -1 because list starts at 1 while indexes start at 0
        // Console prints
        Task task = TaskList.taskList.get(index);
        Ui.printDeleteTask(task); // Print before delete
        TaskList.taskList.remove(index);

    }
}
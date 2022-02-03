import java.io.IOException;
import java.util.ArrayList;

/**
 * Deletes the indicated task
 */
public class DeleteCommand extends Command {
    private String input; // Parameters: 1
    public ArrayList<Task> taskList = TaskManager.taskList;
    private int index; // Index of target task

    public DeleteCommand(String i) {
        this.input = i;
        index = Integer.parseInt(input) - 1; // -1 because list starts at 1 while indexes start at 0
    }

    private String formatOutput() {
        String output = "";
        String taskString = taskList.get(index).toString();

        output += Duke.line();
        output += Duke.newLine(Duke.indent("Task(s) deleted: ", 1));
        output += Duke.indent("", 2);
        output += Duke.newLine(taskString);
        output += Duke.line();

        return output;

    }

    @Override
    public void execute() {
        int index = Integer.parseInt(input) - 1; // -1 because list starts at 1 while indexes start at 0
        // Console prints
        String output = formatOutput();
        System.out.println(output);

        try {
            TaskManager.removeTask(index);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
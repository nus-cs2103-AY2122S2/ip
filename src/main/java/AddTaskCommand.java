import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Generates the correct type of task and adds it to the task list
 */

public class AddTaskCommand extends Command {

    private String input; // Details of the task
    private String type; // Type of task
    private boolean isComplete = false;

    public AddTaskCommand(String input, String type) {
        this.input = input;
        this.type = type;
    }

    public AddTaskCommand(String input, String type, boolean isComplete) {
        this.input = input;
        this.type = type;
        this.isComplete = isComplete;
    }

    /**
     * Formats the echo for the task that was added to the user as confirmation
     *
     * @return Formatted echo
     */
    private String formatOutput(Task newTask) {
        String response = "added: " + newTask.toString();
        String formattedString = Duke.indent(response, 1);
        String finalFormatted = Duke.formatLines(formattedString);

        return finalFormatted;
    }

    @Override
    public void execute() {
        Task newTask;
        if (this.type.equals("todo")) {
            newTask = new TodoTask(this.input);
        } else if (this.type.equals("deadline")) {
            newTask = new DeadlineTask(this.input);
        } else if (this.type.equals("event")) {
            newTask = new EventTask(this.input);
        } else {
            newTask = null;
        }

        // Set indicated completion state
        newTask.isComplete = this.isComplete;

        // Adding task to TaskManager
        TaskManager.taskList.add(newTask);

        Ui.printAddTask(newTask);
    }
}

package duke.command;

import duke.task.Task;
import duke.ui.Ui;

public class DeleteCommand extends Command {
    private String userInput;

    public DeleteCommand(String userInput) {
        super("delete");
        this.userInput = userInput;
    }

    /** Executes the delete command
     * @return a String containing the status message after delete the given task
     */
    public String execute() {
        String[] input = userInput.split(" ");
        int id = Integer.parseInt(input[1]) - 1;
        assert id >= 0 : "invalid";
        Task task = Task.getTaskList()[id];
        task.deleteTask(task);

        String output = Ui.createLine()
                + "       OK I have delete the following task! :D \n"
                + "       " + task + "\n"
                + "       " + "Now you have " + Task.getCounter() + " tasks in your list.\n"
                + Ui.createLine();
        System.out.println(output);

        return output;
    }

    @Override
    public String toString() {
        return "delete";
    }
}

package duke.command;

import duke.task.Task;
import duke.ui.Ui;

public class UnmarkCommand extends Command {
    private String userInput;

    public UnmarkCommand(String userInput) {
        super("unmark");
        this.userInput = userInput;
    }

    /** Executes the unmark command
     * @return a String containing the status message after unmarking the given task
     */
    public String execute() {
        String[] input = userInput.split(" ");
        int id = Integer.parseInt(input[1]) - 1;
        assert id >= 0 : "invalid";
        Task task =  Task.getTaskList()[id];
        task.markNotDone();

        String output = Ui.createLine()
                + "       OK I have marked the following task as not done yet! :D \n"
                + "       " + task + "\n"
                + Ui.createLine();
        System.out.println(output);

        return output;
    }

    @Override
    public String toString() {
        return "unmark";
    }
}

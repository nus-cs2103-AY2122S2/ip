package duke.command;

import duke.task.Task;
import duke.ui.Ui;

public class MarkCommand extends Command {
    private String userInput;

    public MarkCommand(String userInput) {
        super("mark");
        this.userInput = userInput;
    }

    /** Executes the mark command
     * @return a String containing the status message after marking the given task
     */
    public String execute() {
        String[] input = this.userInput.split(" ");
        int id = Integer.parseInt(input[1]) - 1;
        assert id >= 0 : "invalid";
        Task task =  Task.getTaskList()[id];
        task.markDone();

        String output = Ui.createLine()
                + "       I have marked the following task as done! :D \n"
                + "       " + task + "\n"
                + Ui.createLine();

        System.out.println(output);
        return output;
    }

    @Override
    public String toString(){
        return "mark";
    }
}

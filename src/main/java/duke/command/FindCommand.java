package duke.command;

import duke.task.Task;
import duke.ui.Ui;

public class FindCommand extends Command {
    private String userInput;

    public FindCommand(String userInput) {
        super("find");
        this.userInput = userInput;
    }

    /**
     * Executes the find command
     *
     * @return a String containing the status message after find the given keyword
     */
    public String execute() {

        String[] input = userInput.split(" ");
        Integer[] foundTask = Task.findTask(userInput.substring(5));

        if (input.length > 1) {

            String output = Ui.createLine()
                    + "       OK I have found the following task! :D \n"
                    + Task.printTaskList(foundTask) + "\n"
                    + Ui.createLine();
            System.out.println(output);
            return output;

        } else {
            String errorMsg = Ui.createLine()
                    + "       You have entered the find command.\n"
                    + "       What do you want to find?\n"
                    + Ui.createLine();
            System.out.println(errorMsg);
            return errorMsg;

        }
    }

    @Override
    public String toString() {
        return "find";
    }
}

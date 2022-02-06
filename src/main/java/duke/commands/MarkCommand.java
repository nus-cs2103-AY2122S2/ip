package duke.commands;

import duke.tasklist.TaskList;
import duke.tasks.Task;
import duke.ui.Ui;

public class MarkCommand extends Command {

    Task currTask;

    /**
     * Mark a task as complete
     *
     * @param taskList the list of tasks
     * @param userInput the input from user
     */
    public MarkCommand(TaskList taskList, String userInput) {
        try {
            int index = Integer.parseInt(userInput.split(" ")[1]) - 1;
            Task task = taskList.getTasks().get(index);
            task.setComplete();
            currTask = task;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Please enter a number of the item in the list you wish to mark!");
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            System.out.println("Please enter index within range 1 to " + taskList.getTasks().size());
        }
    }

    @Override
    public String execute(TaskList tasks, Ui ui) {
        return Ui.printMarkSuccess(currTask);
    }

}

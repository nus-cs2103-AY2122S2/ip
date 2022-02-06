package duke.commands;

import duke.tasklist.TaskList;
import duke.tasks.Task;
import duke.ui.Ui;

public class RemoveCommand extends Command {

    String currTask;
    /**
     * Removes a task from task list
     *
     * @param taskList the list of tasks
     * @param userInput the input from user
     */
    public RemoveCommand(TaskList taskList, String userInput) {
        try {
            int index = Integer.parseInt(userInput.split(" ")[1]) - 1;
            Task removedTask = taskList.getTasks().get(index);
            taskList.getTasks().remove(index);
            currTask = removedTask.toString();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Please enter index of task to be removed");
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            System.out.println("Please enter index within range 1 to " + taskList.getTasks().size());
        }
    }

    @Override
    public String execute(TaskList tasks, Ui ui) {
        return Ui.printRemoveTask(currTask);
    }
}

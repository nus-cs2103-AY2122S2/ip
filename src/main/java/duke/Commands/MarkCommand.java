package duke.Commands;

import duke.tasklist.TaskList;
import duke.tasks.Task;

public class MarkCommand extends Command {

    public MarkCommand(TaskList taskList, String userInput) {
        try {
            int index = Integer.parseInt(userInput.split(" ")[1]) - 1;
            Task task = taskList.getTasks().get(index);
            task.setComplete();
            System.out.println("Nice! I've marked this task as complete:\n  " + task);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Please enter a number of the item in the list you wish to mark!");
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            System.out.println("Please enter index within range 1 to " + taskList.getTasks().size());
        }
    }
}

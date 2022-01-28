import TaskList.TaskList;
import tasks.Task;

public class RemoveCommand extends Command {

    public RemoveCommand(TaskList taskList, String userInput) {
        try {
            int index = Integer.parseInt(userInput.split(" ")[1]) - 1;
            Task removedTask = taskList.getTasks().get(index);
            taskList.getTasks().remove(index);
            System.out.println("Noted. I've removed this task:\n  " + removedTask.toString());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Please enter index of task to be removed");
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            System.out.println("Please enter index within range 1 to " + taskList.getTasks().size());
        }
    }
}

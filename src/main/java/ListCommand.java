/**
 * Prints the current list of tasks
 */
public class ListCommand extends Command {
    @Override
    public void execute() {
        System.out.println(TaskManager.taskList);
    }

}

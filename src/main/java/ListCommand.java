import java.util.ArrayList;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        int length = taskList.size();
        StringBuilder sb = new StringBuilder();

        if (length == 0) {
            ui.output("No tasks found! Quit lazing around!");
            return;
        }

        ArrayList<Task> allTasks = taskList.getTasks();

        sb.append("Here are the tasks in your list:\n");

        for (int i = 0; i < length; ++i) {
            sb.append(i + 1 + ". " + allTasks.get(i).toString());

            if (i + 1 != length) {
                sb.append("\n");
            }
        }

        ui.output(sb.toString());
    }
}

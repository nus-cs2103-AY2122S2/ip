import java.util.ArrayList;
import java.util.List;

public class ListCommand extends Command {

    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.isEmpty()) {
            ui.printWithDivider("Your list is empty!");
        } else {
            StringBuilder sb = new StringBuilder(ui.strPadding + "Here are the tasks in your list: \n");

            for (int i = 0; i < tasks.size(); i++) {
                Task currentTask = tasks.get(i);
                sb.append(String.format(ui.strPadding + "  %d. " + currentTask.toString(), i + 1));

                if (i != tasks.size() - 1) {
                    sb.append("\n");
                }
            }
            ui.printWithDivider(sb);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

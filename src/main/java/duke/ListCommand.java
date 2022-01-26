package main.java.duke;
import java.util.ArrayList;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks,Ui ui, Storage storage) {
        ArrayList<Task> t = tasks.getTaskArr();
        if (t.size() == 0) {
            ui.showEmptyTask();
        }

        for (int i = 0; i < t.size(); i++ ) {
            ui.showTask(i + 1, t.get(i));
        }
    }

    @Override
    public boolean isEnd() {
        return false;
    }
}

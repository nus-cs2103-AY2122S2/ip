package main.java.duke;

import java.util.ArrayList;

public class FindCommand extends Command {

    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showKeywords();
        ArrayList<Task> t = tasks.getTaskArr();
        for (int i = 0; i < t.size(); i++) {
            if (tasks.getTask(i).toString().contains(keyword)) {
                ui.showTask(i + 1, tasks.getTask(i));
            }
        }
    }

    @Override
    public boolean isEnd() {
        return false;
    }
}

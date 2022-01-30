package saitama.commands;

import java.util.List;

import saitama.Storage;
import saitama.TaskList;
import saitama.Ui;
import saitama.tasks.Task;

public class FindCommand extends Command {
    private String query;

    public FindCommand(String query) {
        this.query = query;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        System.out.println("OK...");
        List<Task> matchingTasks = taskList.search(query);
        int counter = 1;
        if (matchingTasks.size() == 0) {
            System.out.println("There are no matching tasks in your list!");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (Task task : matchingTasks) {
                System.out.println(counter + "." + task);
                counter += 1;
            }
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

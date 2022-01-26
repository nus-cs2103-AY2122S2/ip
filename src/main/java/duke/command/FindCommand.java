package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents commands which find tasks in the task list. A FindCommand
 * object corresponds a valid Ekud command, which can then be executed.
 */

public class FindCommand implements Command {

    private String fullCommand;
    private String[] splitFullCommand;
    private String taskType;

    public FindCommand(String fullCommand) {
        this.fullCommand = fullCommand;
        this.splitFullCommand = fullCommand.split(" ", 2);
        this.taskType = splitFullCommand[0];
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws Exception {
        String find = splitFullCommand[1];
        List<Task> taskList = tasks.getTaskList();
        List<Task> l = new ArrayList<>();
        for (Task t : taskList) {
            if (t.toString().contains(find)) {
                l.add(t);
            }
        }
        if (l.isEmpty()) {
            System.out.println("No match found!");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < l.size(); i++) {
                System.out.println(i + 1 + "." + l.get(i).toString());
            }
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

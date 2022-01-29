package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;

import java.util.ArrayList;

/**
 * FindCommand class
 */
public class FindCommand extends Command<String> {

    private String command;
    private TaskList list;

    /**
     * Constructor for find command
     * @param command command to format
     * @param list list of task to find
     */
    public FindCommand(String command, TaskList list) {
        this.command = command;
        this.list = list;
        runCommand();
    }

    @Override
    public void runCommand() {
        String searchText = command.substring(5);
        ArrayList<Task> freshList = new ArrayList<>();
        for (int i = 0; i < list.getSize(); i++) {
            Task currTask = list.getTask(i);
            if (currTask.toString().contains(searchText)) {
                freshList.add(currTask);
            }
        }
        int counter = 1;
        System.out.println("Here are the matching tasks in your list");
        for (Task s : freshList) {
            System.out.println("  " + counter + "." + s);
            counter++;
        }
    }
}

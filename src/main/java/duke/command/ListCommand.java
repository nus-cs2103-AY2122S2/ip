package duke.command;

import duke.Duke;
import duke.tasklist.TaskList;

/**
 * list class
 */
public class ListCommand extends Command<String> {

    private TaskList taskList;

    /**
     * Constructor for list object
     * @param list task list to display
     */
    public ListCommand(TaskList list) {
        this.taskList = list;
        runCommand();
    }

    /**
     * display tasks in task list
     */
    @Override
    public void runCommand() {
        System.out.println("Here are the tasks in your list:\n");
        int counter = Duke.COUNTER;
        for (int i = 0; i < taskList.getSize(); i++) {
            System.out.println("  " + counter + "." + taskList.getTask(i));
            counter++;
        }
    }
}

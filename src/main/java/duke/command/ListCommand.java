package duke.command;

import duke.Duke;
import duke.tasklist.TaskList;

/**
 * list class
 */
public class ListCommand extends Command<String>{

    private TaskList list;

    /**
     * Constructor for list object
     * @param list task list to display
     */
    public ListCommand(TaskList list) {
        this.list = list;
        runCommand();
    }

    /**
     * display tasks in task list
     */
    @Override
    public void runCommand() {
        System.out.println("Here are the tasks in your list:\n");
        int counter = Duke.COUNTER;
        for (int i = 0; i < list.getSize(); i++) {
            System.out.println("  " + counter + "." + list.getTask(i));
            counter++;
        }
    }
}

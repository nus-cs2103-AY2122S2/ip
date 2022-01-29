package duke.command;

import duke.Duke;
import duke.tasklist.TaskList;

public class ListCommand extends Command<String>{

    private TaskList list;
    public ListCommand(TaskList list) {
        this.list = list;
        runCommand();
    }

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

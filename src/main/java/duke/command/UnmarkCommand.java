package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;

/**
 * UnmarkCommand class
 */
public class UnmarkCommand extends Command<String>{

    private String text;
    private TaskList list;
    private Storage storage;

    /**
     * Constructor for UnmarkCommand object
     * @param text string containing index of task to unmark
     * @param list task list to unmark specified task
     * @param storage storage of where this task will be unmarked
     */
    public UnmarkCommand(String text, TaskList list, Storage storage) {
        this.text = text;
        this.list = list;
        this.storage = storage;
        runCommand();
    }

    /**
     * method to unmark task
     */
    @Override
    public void runCommand() {
        int taskNumber = intSearch(text) - 1;
        if (taskNumber < list.getSize() && taskNumber >= 0) {
            Task intendedTask = list.getTask(taskNumber);
            intendedTask.setDone(false);
            storage.writeToFile(list);
            System.out.println("  " + "AIYO! I've marked this task as not done yet:\n"
                    + "    " + intendedTask);
        } else {
            System.out.println("EH HULLO!! Task does not exist! Check again hehe");
        }
    }

    /**
     * Format string to obtain index
     * @param text unformatted string
     * @return index of task to mark
     */
    public int intSearch(String text) {
        String[] splicedString = text.split(" ");
        return Integer.parseInt(splicedString[1]);
    }
}

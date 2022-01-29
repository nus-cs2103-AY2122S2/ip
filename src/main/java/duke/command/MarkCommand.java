package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;

/**
 * MarkCommand class
 */
public class MarkCommand extends Command<String>{

    private String text;
    private TaskList list;
    private Storage storage;

    /**
     * Constructor for MarkCommand object
     * @param text string containing index of task to mark
     * @param list task list to mark specified task
     * @param storage storage of where this task will be marked
     */
    public MarkCommand(String text, TaskList list, Storage storage) {
        this.text = text;
        this.list = list;
        this.storage = storage;
        runCommand();
    }

    /**
     * Mark task
     */
    @Override
    public void runCommand() {
        int taskNumber = intSearch(text) - 1;
        if (taskNumber < list.getSize() && taskNumber >= 0) {
            Task intendedTask = list.getTask(taskNumber);
            intendedTask.setDone(true);
            storage.writeToFile(list);
            System.out.println("  " + "SOLID! I've marked this task as done:\n"
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

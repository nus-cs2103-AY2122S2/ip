package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;

/**
 * MarkCommand class
 */
public class MarkCommand extends Command<String> {

    private String inputText;
    private TaskList taskList;
    private Storage storage;

    /**
     * Constructor for MarkCommand object
     * @param text string containing index of task to mark
     * @param list task list to mark specified task
     * @param storage storage of where this task will be marked
     */
    public MarkCommand(String text, TaskList list, Storage storage) {
        this.inputText = text;
        this.taskList = list;
        this.storage = storage;
        runCommand();
    }

    /**
     * Mark task
     */
    @Override
    public void runCommand() {
        int taskNumber = intSearch(inputText) - 1;
        if (taskNumber < taskList.getSize() && taskNumber >= 0) {
            assert taskNumber < taskList.getSize() : "condition falsely returning true";
            assert taskNumber >= 0 : "condition falsely returning true";
            Task intendedTask = taskList.getTask(taskNumber);
            intendedTask.setDone(true);
            assert intendedTask.getDone() == true : "Method not working";
            storage.writeToFile(taskList);
            System.out.println("  " + "SOLID! I've marked this task as done:\n"
                    + "    " + intendedTask);
        } else {
            System.out.println("EH HULLO!! Task does not exist! Check again hehe");
        }
    }

    /**
     * Format string to obtain index
     * @param inputText unformatted string
     * @return index of task to mark
     */
    public int intSearch(String inputText) {
        String[] splicedString = inputText.split(" ");
        return Integer.parseInt(splicedString[1]);
    }
}

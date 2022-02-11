package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;

/**
 * DeleteCommand class
 */
public class DeleteCommand extends Command<String> {

    private String inputText;
    private TaskList taskList;
    private Storage storage;

    /**
     *
     * @param text unformatted string for deadline
     * @param list task list to add this task to
     * @param storage storage of where this task will be added to
     */
    public DeleteCommand(String text, TaskList list, Storage storage) {
        this.inputText = text;
        this.taskList = list;
        this.storage = storage;
        runCommand();
    }

    /**
     * Splice the string and delete from list and display to user
     */
    @Override
    public void runCommand() {
        int indexToDelete = intSearch(inputText) - 1;
        if (indexToDelete < 0 || indexToDelete >= taskList.getSize()) {
            System.out.println("EH HULLO!! Task does not exist! Check again hehe");
        } else {
            Task deleteTask = taskList.getTask(indexToDelete);
            taskList.deleteTask(indexToDelete);
            storage.writeToFile(taskList);
            System.out.println("   " + "ALRIGHTY. I've removed this task:\n"
                    + "    " + deleteTask + "\n" + "   Now you have " + taskList.getSize() + " tasks in the list.");
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

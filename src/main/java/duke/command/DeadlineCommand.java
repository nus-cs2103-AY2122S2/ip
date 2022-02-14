package duke.command;

import duke.storage.Storage;
import duke.task.Deadline;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Deadline Command class
 */
public class DeadlineCommand extends Command<String> {

    private TaskList taskList;
    private String inputText;
    private Storage storage;
    private Ui ui;

    /**
     * Constructor for DeadlineCommand object
     * @param text unformatted string for deadline
     * @param list task list to add this task to
     * @param storage storage of where this task will be added to
     * @param ui ui of subsequent system out to user
     */
    public DeadlineCommand(String text, TaskList list, Storage storage, Ui ui) {
        this.taskList = list;
        this.inputText = text;
        this.storage = storage;
        this.ui = ui;
        runCommand();
    }

    /**
     * Splice the string and add to list and display to user
     */
    @Override
    public void runCommand() {
        Deadline freshDeadline = deadlineCreator();
        taskList.addTask(freshDeadline);
        storage.writeToFile(taskList);
        ui.showAddDeadline(freshDeadline, taskList);
    }

    public Deadline deadlineCreator() {
        String[] splicedString = inputText.split(" /by ");
        String splicedDescription = splicedString[0].substring(9);
        if (inputText.contains("/pri")) {
            String[] splicedDetails = splicedString[1].split(" /pri ");
            String dueDate = splicedDetails[0];
            String priorityLevel = splicedDetails[1];
            Deadline freshDeadline = new Deadline(splicedDescription, dueDate, priorityLevel, false);
            return freshDeadline;
        } else {
            String dueDate = splicedString[1];
            Deadline freshDeadline = new Deadline(splicedDescription, dueDate, "", false);
            return freshDeadline;
        }
    }
}



package dazz.command;

import java.time.LocalDateTime;

import dazz.Storage;
import dazz.TaskList;
import dazz.Ui;
import dazz.task.Deadline;

/**
 * Represents the logic of adding a <code>Deadline</code>.
 */
public class DeadlineCommand extends Command {
    private Deadline deadline;

    /**
     * Constructs a <code>DeadlineCommand</code> based on the description and
     * datetime of the <code>Deadline</code>.
     * @param description The description of the <code>Deadline</code>.
     * @param dateTime The dateTime of the <code>Deadline</code>.
     */
    public DeadlineCommand(String description, LocalDateTime dateTime) {
        this.deadline = new Deadline(description, dateTime);
    }

    /**
     * Executes the adding of a <code>Deadline</code>,
     * updates the text file and returns the execution message.
     * @param taskList The <code>TaskList</code> that the <code>Deadline</code> is added to.
     * @param ui The <code>Ui</code> of Dazz.
     * @param storage The <code>Storage</code> of Dazz.
     * @return The execution message.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.add(this.deadline);
        //ui.showAdd(deadline, taskList);
        String message = ui.messageForAdd(deadline, taskList);
        storage.updateList(taskList);
        return message;
    }

}

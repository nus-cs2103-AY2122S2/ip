package duke.command;

import java.time.LocalDate;

import duke.Storage;
import duke.exception.BingChillingException;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.MessageUi;

/**
 * Represents commands which postpones a task in the task list. A PostponeCommand
 * object corresponds a valid Ekud command, which can then be executed.
 */
public class PostponeCommand implements Command {

    private String fullCommand;
    private String[] splicedFullCommand;
    private int position;
    private LocalDate localDate;

    /**
     * Constructor for this class.
     *
     * @param fullCommand User's input.
     */
    public PostponeCommand(String fullCommand) {
        this.fullCommand = fullCommand;
        this.splicedFullCommand = fullCommand.split(" ");
        this.position = Integer.parseInt(splicedFullCommand[1]);
        this.localDate = LocalDate.parse(splicedFullCommand[2], Task.getInputDateFormat());
    }

    @Override
    public String execute(TaskList tasks, Storage storage, MessageUi ui) throws BingChillingException {
        if (position < 1 || position > tasks.getTaskSize()) {
            throw new BingChillingException("Task do not exist!");
        } else {
            Task task = tasks.getTask(position);
            assert task.getType().equals("deadline") || task.getType().equals("event");
            task.setDate(localDate);
            storage.setInFile(position, task.taskDescriptionForFile());
            return ui.showPostponeMessage(task);
        }
    }
}

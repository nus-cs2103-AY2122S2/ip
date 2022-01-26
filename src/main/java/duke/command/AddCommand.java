package duke.command;

import duke.datetime.DateTable;
import duke.exception.BotException;
import duke.task.Task;
import duke.task.TaskList;
import duke.util.BotStoring;
import duke.util.Ui;

import java.io.IOException;

/**
 * Adds task to the respective storage command
 */
public class AddCommand extends Command {
    private final String description;
    private final String fullInput;
    private final String type;
    private final BotException exception = new BotException();

    public AddCommand(String fullInput, String description, String type) {
        this.description = description;
        this.fullInput = fullInput;
        this.type = type;
    }

    /**
     * Adds date and task into <code>DateTable</code> and appends the new task into the database
     *
     * @param dateTable Reference of the <code>DateTable</code> object
     * @param botStorage Reference of the <code>BotStorage</code> object
     * @param task The task need to be added
     * @throws IOException If an I/O error occurs
     */
    private void printTask(DateTable dateTable, BotStoring botStorage, Task task) throws IOException {
        if (!task.getType().equals("T")) {
            dateTable.addDate(task);
        }
        botStorage.addTaskToText(task);
    }

    /**
     * Creates the <code>Task</code> Object and adds to list of tasks and database respectively,
     * also prints out the task content and the number of tasks after added
     *
     * @param taskList Reference of the <code>TaskList</code> object
     * @param ui Reference of the <code>Ui</code> object
     * @param botStorage Reference of the <code>BotStorage</code> object
     * @param dateTable Reference of the <code>DateTable</code> object
     * @throws IOException If an I/O error occurs
     */
    @Override
    public void execute(TaskList taskList, Ui ui, BotStoring botStorage, DateTable dateTable)
            throws IOException {
        if (type.equals("T") && fullInput.length() == 4) {
            exception.emptyDescription("todo");
        } else if (type.equals("D") && fullInput.length() == 8) {
            exception.emptyDescription("deadline");
        } else if (type.equals("E") && fullInput.length() == 5) {
            exception.emptyDescription("event");
        } else {
            Task task = new Task(description, type);
            taskList.addTask(task);
            ui.showTask(task, taskList.getTotalTask());
            printTask(dateTable, botStorage, task);
        }
    }

    /**
     * Checks if the command is the exit command
     *
     * @return False as this is not an exit command
     */
    @Override
    public boolean isExit() {
        return false;
    }
}

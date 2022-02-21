package duke.command;


import duke.main.Storage;
import duke.main.Ui;
import duke.exception.DukeException;
import duke.task.TaskList;

/**
 * Represents update command
 */
public class UpdateCommand extends Command {
    private int number;
    private String section;
    private String content;

    public UpdateCommand(int number, String section, String content) {
        this.number = number - 1;
        this.section = section;
        this.content = content;
    }

    /**
     * Update details of a specific task on the list
     *
     * @param tasks contains list of tasks
     * @param ui interact with user
     * @param storage save tasks to file
     * @throws DukeException if I0Exception occurs
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (number >= 0 && number < tasks.getTasks().size()) {
            tasks.updateTask(number, section, content);
            ui.showUpdateTask(tasks.getTasks().get(number));
            storage.save(tasks.getTasks());
        } else {
            throw new DukeException("Invalid number");
        }
    }
}
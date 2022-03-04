package duke.command;

import java.time.LocalDate;

import duke.DukeException;
import duke.managers.FileManager;
import duke.managers.TaskList;


public class RemindCommand extends Command {
    private LocalDate reminderEndDate;

    /**
     * RemindCommand Constructor
     * @param userTaskString
     * @param reminderEndDate end date input by user in String
     */
    public RemindCommand(String userTaskString, String reminderEndDate) {
        super(userTaskString);
        this.reminderEndDate = LocalDate.parse(reminderEndDate);
    }

    /**
     * Executes printing of tasks that falls between current date and reminder end date set by user
     *
     * @param taskList
     * @param fileManager
     */
    @Override
    public void executeTask(TaskList taskList, FileManager fileManager) throws DukeException {
        taskList.remindUserTasks(this.reminderEndDate);
    }

}

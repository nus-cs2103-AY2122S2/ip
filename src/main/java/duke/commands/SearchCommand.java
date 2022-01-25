package duke.commands;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.util.ArrayList;

import duke.common.DukeException;
import duke.constants.Constants;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Searches for deadline or event tasks based on given date by user.
 */
public class SearchCommand extends Command {
    private String args;

    /**
     * Creates new SearchCommand object that receives user input.
     * @param args args holds the date specified by user.
     */
    public SearchCommand(String args) {
        this.args = args;
    }

    /**
     * Executes the search command.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        try {
            if (this.args.length() == 0) {
                throw new IllegalArgumentException();
            }

            LocalDate date = LocalDate.parse(this.args);
            int index = 0;
            ArrayList<Task> allTasks = taskList.getTasks();
            int length = allTasks.size();
            StringBuilder sb = new StringBuilder();

            if (length == 0) {
                ui.output("No tasks found based on given date! Also, quit lazing around!");
                return;
            }

            sb.append("Here are the tasks with date, "
                    + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
                    + ", in your list:\n");

            for (int i = 0; i < length; ++i) {
                boolean shouldAppend = false;
                Task task = allTasks.get(i);

                if (task.getType() == 'D') {
                    Deadline deadline = (Deadline) task;

                    if (deadline.getDate().isEqual(date)) {
                        sb.append(++index + ". " + deadline.toString());
                        shouldAppend = true;
                    }
                } else if (task.getType() == 'E') {
                    Event event = (Event) task;

                    if (event.getDate().isEqual(date)) {
                        sb.append(++index + ". " + event.toString());
                        shouldAppend = true;
                    }
                }

                if (shouldAppend) {
                    sb.append("\n");
                }
            }

            ui.output((index > 0) ? sb.toString() : Constants.NO_TASK_SEARCH_MSG);
        } catch (Exception e) {
            throw new DukeException(Constants.INVALID_DATE_MSG);
        }
    }
}

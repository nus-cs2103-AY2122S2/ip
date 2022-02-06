package duke.command;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;

/**
 * An instance of ViewScheduleCommand.
 */
public class ViewScheduleCommand extends Command {

    private final Date dateCheck;

    /**
     * Instantiates a new View schedule command.
     *
     * @param dateString the date string
     */
    public ViewScheduleCommand(String dateString) throws DukeException {
        try {
            this.dateCheck = new SimpleDateFormat("dd/MM/yyyy").parse(dateString);
        } catch (ParseException e) {
            throw new DukeException("Please enter the date in \"dd/MM/yyyy\" format\"");
        }
    }

    /**
     * Displays a list of events that match the date given by the user.
     *
     * @param tasks   the tasks in `TaskList`
     * @param ui      the UI that the user interacts with
     * @param storage the storage that is used to read/write to the local file
     * @return list of events that match the date given by the user, if any
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        List<Task> filteredTask = tasks.getTasks().stream()
                .filter(this::isDatedTask)
                .filter(task -> isSameDate(getDate(task)))
                .collect(Collectors.toList());

        return ui.showSchedule(dateCheck, filteredTask);
    }

    private Date getDate(Task datedTask) {
        return (datedTask instanceof Event)
                ? ((Event) datedTask).getDate()
                : ((Deadline) datedTask).getDate();
    }

    private boolean isSameDate(Date current) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(current).equals(sdf.format(dateCheck));
    }

    private boolean isDatedTask(Task task) {
        return task instanceof Event
                || task instanceof Deadline;
    }
}

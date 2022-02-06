package duke.command;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;
import duke.util.TaskList;

public class ScheduleCommand extends Command {
    private LocalDate dateToSearch;

    public ScheduleCommand(LocalDate dateToSearch) {
        this.dateToSearch = dateToSearch;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        return ui.showTasks(tasks.getTasksWithDate(dateToSearch), "schedule",
                dateToSearch.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}

package Duke.Commands;

import Duke.DukeException.DukeException;
import Duke.System.Storage;
import Duke.System.TaskList;
import Duke.System.Ui;
import Duke.Tasks.Task;
import Duke.Tasks.TaskEvent;
import Duke.Time.ManagerDate;
import Duke.Time.ManagerTime;

public class CommandEvent extends Command {
    private final String name;
    private final String date;
    private final String time;

    public CommandEvent(String name, String date, String time) {
        this.name = name;
        this.date = date;
        this.time = time;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ManagerDate md = new ManagerDate(date);
        ManagerTime mt = new ManagerTime(time);

        if (md.isDateValid() && mt.isTimeValid()) {
            Task task = new TaskEvent(this.name, this.date, this.time);
            tasks.add(task);
            storage.save(tasks);
            ui.showTaskAdded();
        } else {
            throw new DukeException("Invalid date and time!");
        }
    }
}

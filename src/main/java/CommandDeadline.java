public class CommandDeadline extends Command {
    private final String name;
    private final String date;
    private final String time;

    public CommandDeadline(String name, String date, String time) {
        this.name = name;
        this.date = date;
        this.time = time;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ManagerDate md = new ManagerDate(date);
        ManagerTime mt = new ManagerTime(time);

        if (md.isDateValid() && mt.isTimeValid()) {
            Task task = new TaskDeadline(this.name, this.date, this.time);
            tasks.add(task);
            storage.save(tasks);
            ui.showTaskAdded();
        } else {
            throw new DukeException("Invalid date and time!");
        }
    }
}

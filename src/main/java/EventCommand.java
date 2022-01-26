
public class EventCommand extends Command {
    protected Task event;

    public EventCommand(String description, String at) {
        this.event = new Event(description, at);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.add(event);
            storage.store(tasks);
            ui.showResponseMessage("event");
            ui.showTaskMessage(event);
            ui.printTasksCountMessage(tasks);
        } catch (DukeException e) {
            // e.printStackTrace();
        }
    }

}

public class EventCommand extends Command {
    private final Event event;

    public EventCommand(String description, String at) {
        this.event = new Event(description, at);
    }

    public void execute(Storage storage, TaskList tasks, Ui ui) {
        tasks.add(event);
        ui.showEvent(this.event, tasks);
    }

    public boolean isExit() {
        return false;
    }
}

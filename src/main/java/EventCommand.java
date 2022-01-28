public class EventCommand extends Command {
    private Event event;

    public EventCommand(String description, String at) {
        event = new Event(description, at);
    }

    public boolean isExit() {
        return false;
    }

    public void execute(Storage storage, TaskList tasks, Ui ui) {
        tasks.add(event);
        ui.showTaskAdded(event);
        ui.showNumberOfTasks(tasks);
    }
}

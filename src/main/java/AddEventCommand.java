public class AddEventCommand extends Command{
    private String task;
    private String at;

    public AddEventCommand(String task, String at) {
        super();
        this.task = task;
        this.at = at;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Event event = new Event(task,at);
        taskList.add(event);
        ui.print("Got it. I've added this task:");
        ui.print(event.toString());
        ui.print("Now you have " + taskList.tasks.size() +  " tasks in the list.");
        try {
            storage.save(taskList);
        } catch (Exception e) {
            throw new DukeException(e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
public class AddCommand extends Command {
    private String description;
    private String type;
    private Task task;

    public AddCommand(String type, String description) {
        this.type = type;
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        switch (type) {
            case "T":
                task = new ToDos(description);
                break;
            case "D":
                task = new Deadlines(description);
                break;
            case "E":
                task = new Events(description);
                break;
            default:
                break;
        }

        tasks.add(task);
        storage.updateAfterAdd(task);
        ui.addMessage(task, tasks.length());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

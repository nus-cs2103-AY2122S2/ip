public class AddToDoCommand extends Command{
    private String task;

    public AddToDoCommand(String task) {
        super();
        this.task = task;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ToDo todo = new ToDo(task);
        taskList.add(todo);
        ui.print("Got it. I've added this task:");
        ui.print(todo.toString());
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
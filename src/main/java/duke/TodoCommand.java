package duke;

public class TodoCommand extends Command {

    TodoCommand(Ui ui, TaskList taskList, Storage storage, String detail) {
        super(ui, taskList, storage, detail);
    }

    @Override
    String runCommand() {
        String[] detailArray = detail.split(" ", 2);
        String task = detailArray[1];
        Task newTask = new Todo(task);
        this.taskList.addToList(newTask);
        return ui.echo("Task added: " + newTask.toString() + "\n"
                + "Now you have " + this.taskList.getListSize() + " tasks in the list");
    }
}

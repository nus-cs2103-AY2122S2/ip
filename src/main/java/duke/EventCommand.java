package duke;

public class EventCommand extends Command {

    EventCommand(Ui ui, TaskList taskList, Storage storage, String detail) {
        super(ui, taskList, storage, detail);
    }

    @Override
    String runCommand() {
        String[] detailArray = detail.split(" ", 2);
        String taskAndTime = detailArray[1];
        String[] arr = taskAndTime.split("/at");
        String task = arr[0];
        String time = arr[1];
        Task newTask = new Event(task, time);
        this.taskList.addToList(newTask);
        return ui.echo("Task added:" + newTask.toString() + "\n"
                + "Now you have " + this.taskList.getListSize() + " tasks in the list");
    }
}

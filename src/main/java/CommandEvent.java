import java.time.LocalDate;
class CommandEvent extends Command {
    TaskList taskList;
    LocalDate time;
    String eventContent;

    public CommandEvent(TaskList taskList, String eventContent, LocalDate time) {
        this.eventContent = eventContent;
        this.taskList = taskList;
        this.time = time;
    }

    @Override
    public void execute() {
        Event  newTask = new Event(eventContent, time);
        taskList.addTask(newTask);
        Ui.wrapPrint(Response.RESPONSE_ADDED + "\n" + newTask.toString() + "\n");
    }
}

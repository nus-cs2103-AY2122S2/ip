import java.time.LocalDate;
class CommandDeadline extends Command {
    String deadlineContent;
    LocalDate date;
    TaskList taskList;
    public CommandDeadline(TaskList taskList, String deadlineContent, LocalDate date) {
        this.deadlineContent = deadlineContent;
        this.date = date;
        this.taskList = taskList;
    }

    @Override
    public void execute() {
        Deadline newTask = new Deadline(deadlineContent, date);
        taskList.addTask(newTask);
        Ui.wrapPrint(Response.RESPONSE_ADDED + "\n" + newTask.toString() + "\n"
                     + Response.taskNo(taskList.size()));
    }
}

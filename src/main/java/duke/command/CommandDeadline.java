package duke.command;

import java.time.LocalDate;

import duke.Deadline;
import duke.Response;
import duke.TaskList;
import duke.Ui;

public class CommandDeadline extends Command {
    private final String deadlineContent;
    private final LocalDate date;
    private final TaskList taskList;



    public CommandDeadline(TaskList taskList, String deadlineContent, LocalDate date) {
        this.deadlineContent = deadlineContent;
        this.date = date;
        this.taskList = taskList;
    }

    @Override
    public String execute() {
        Deadline newTask = new Deadline(deadlineContent, date);
        taskList.addTask(newTask);
        return Response.RESPONSE_ADDED + "\n" + newTask + "\n" + Response.taskNo(taskList.size());
    }
}

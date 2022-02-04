package duke.command;

import java.time.LocalDate;

import duke.Event;
import duke.Response;
import duke.TaskList;
import duke.Ui;

public class CommandEvent extends Command {
    private TaskList taskList;
    private LocalDate time;
    private String eventContent;

    public CommandEvent(TaskList taskList, String eventContent, LocalDate time) {
        this.eventContent = eventContent;
        this.taskList = taskList;
        this.time = time;
    }

    @Override
    public String execute() {
        Event newTask = new Event(eventContent, time);
        taskList.addTask(newTask);
        return Response.RESPONSE_ADDED + "\n" + newTask + "\n";
    }
}

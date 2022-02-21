package duke.command;

import java.time.LocalDate;

import duke.Event;
import duke.Response;
import duke.TaskList;

public class CommandEvent extends Command {
    private String response;
    private TaskList taskList;
    private LocalDate time;
    private String eventContent;

    public CommandEvent(TaskList taskList, String eventContent, LocalDate time) {
        assert taskList != null;
        assert eventContent != null;
        assert time != null;
        this.eventContent = eventContent;
        this.taskList = taskList;
        this.time = time;
    }

    @Override
    public void execute() {
        Event newTask = new Event(eventContent, time);
        taskList.addTask(newTask);
        response = Response.RESPONSE_ADDED + "\n" + newTask + "\n";
    }

    @Override
    public String getResponse() {
        return response;
    }
}

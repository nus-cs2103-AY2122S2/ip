package duke.commands;

import duke.tasks.Task;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Todo;
import java.time.LocalDateTime;

public class AddCommand extends Command{
    private String taskType;
    private String content;
    private LocalDateTime datetime;

    public AddCommand(String taskType, String content, LocalDateTime datetime) {
        this.taskType = taskType;
        this.content = content;
        this.datetime = datetime;
    }

    @Override
    public String execute() {
        Task newTask = null;
        switch (taskType.toLowerCase()) {
            case "todo":
                newTask = new Todo(content);
                break;
            case "deadline":
                newTask = new Deadline(content, datetime);
                break;
            case "event":
                newTask = new Event(content, datetime);
                break;
        }
        taskList.addTask(newTask);
        return String.format("""
                This task has been added as requested:
                %s
                You now have %d item(s) in your list""", newTask, taskList.size());
    }
}
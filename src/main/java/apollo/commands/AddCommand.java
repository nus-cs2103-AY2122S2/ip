package apollo.commands;

import apollo.tasks.Deadline;
import apollo.tasks.Event;
import apollo.tasks.Task;
import apollo.tasks.Todo;

import java.time.LocalDateTime;

public class AddCommand extends Command {

    private final String description;
    private final LocalDateTime dateTime;
    private final Task.Type taskType;

    public AddCommand(String description, LocalDateTime dateTime, Task.Type taskType) {
        this.description = description;
        this.dateTime = dateTime;
        this.taskType = taskType;
    }

    @Override
    public String execute() {
        Task newTask = null;
        switch (taskType) {
        case TODO:
            newTask = new Todo(description);
            break;
        case DEADLINE:
            newTask = new Deadline(description, dateTime);
            break;
        case EVENT:
            newTask = new Event(description, dateTime);
            break;
        }

        taskList.addTask(newTask);
        return String.format("I've added this task:\n  %s\n"
                        + "There's a total of %d tasks now. ",
                newTask, taskList.taskCount());
    }
}

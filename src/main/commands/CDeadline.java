package main.commands;

import main.enums.CommandType;
import main.tasks.Deadline;
import main.tasks.Task;

public class CDeadline extends Command {
    protected String description;
    protected String dueDate;

    public CDeadline(String description, String dueDate) {
        super(CommandType.DEADLINE);
        this.description = description;
        this.dueDate = dueDate;
    }

    public String getDueDate() {
        return this.dueDate;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public void runCommand() {
        Task newDeadline = new Deadline(this.getDescription(), this.getDueDate());
        Task.addTask(newDeadline);
        System.out.printf("Got it. I've added this task:\n" + "%s\n" + "%s\n",
                newDeadline, Task.taskCountToString());
    }
}

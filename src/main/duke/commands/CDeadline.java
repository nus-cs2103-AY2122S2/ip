package main.duke.commands;

import main.duke.TaskList;
import main.duke.Ui;
import main.duke.enums.CommandType;
import main.duke.tasks.Deadline;
import main.duke.tasks.Task;

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
    public void runCommand(Ui ui, TaskList taskList) {
        Task newDeadline = new Deadline(this.getDescription(), this.getDueDate());
        taskList.addTask(newDeadline);
        ui.respondAddTask(newDeadline, taskList);
    }
}

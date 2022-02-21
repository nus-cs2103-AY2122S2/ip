package main.duke.commands;

import main.duke.DukeException;
import main.duke.TaskList;
import main.duke.Ui;
import main.duke.enums.CommandType;
import main.duke.tasks.Deadline;
import main.duke.tasks.Task;

import java.time.LocalDateTime;

public class CDeadline extends Command {
    protected String description;
    protected LocalDateTime dueDate;

    public CDeadline(String description, LocalDateTime dueDate) {
        super(CommandType.DEADLINE);
        this.description = description;
        this.dueDate = dueDate;
    }

    public LocalDateTime getDueDate() {
        return this.dueDate;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public String runCommand(Ui ui, TaskList taskList) throws DukeException {
        Task newDeadline = new Deadline(this.getDescription(), this.getDueDate());
        taskList.addTask(newDeadline);
        super.runCommand(ui, taskList);
        return ui.respondAddTask(newDeadline, taskList);
    }
}

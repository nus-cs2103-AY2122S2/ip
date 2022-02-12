package main.duke.commands;

import main.duke.DukeException;
import main.duke.TaskList;
import main.duke.Ui;
import main.duke.enums.CommandType;
import main.duke.tasks.Task;
import main.duke.tasks.ToDo;

public class CTodo extends Command{
    protected String description;

    public CTodo(String description) {
        super(CommandType.TODO);
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public String runCommand(Ui ui, TaskList taskList) throws DukeException {
        Task newToDo = new ToDo(this.getDescription());
        taskList.addTask(newToDo);
        super.runCommand(ui, taskList);
        return ui.respondAddTask(newToDo, taskList);
    }
}

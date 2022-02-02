package main.commands;

import main.TaskList;
import main.Ui;
import main.enums.CommandType;
import main.tasks.Task;
import main.tasks.ToDo;

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
    public void runCommand(Ui ui, TaskList taskList) {
        Task newToDo = new ToDo(this.getDescription());
        taskList.addTask(newToDo);
        ui.respondAddTask(newToDo, taskList);
    }
}

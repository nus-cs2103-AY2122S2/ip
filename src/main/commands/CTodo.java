package main.commands;

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
    public void runCommand() {
        Task newToDo = new ToDo(this.getDescription());
        Task.addTask(newToDo);
        System.out.printf("Got it. I've added this task:\n" + "%s\n" + "%s\n",
                newToDo, Task.taskCountToString());
    }
}

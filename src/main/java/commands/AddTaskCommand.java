package commands;

import tasks.TaskManager;
import exceptions.DateException;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;
import ui.UiManager;


public class AddTaskCommand extends Command{
    private String description;
    private String date;
    private TaskManager taskManager;
    private UiManager uiManager;
    private Type type;

    public AddTaskCommand(UiManager um, TaskManager tm, String task, Type t) throws DateException {
        this.uiManager = um;
        this.taskManager = tm;
        this.type = t;
        switch(t) {
            case TODO:
                this.description = task;
                break;
            case DEADLINE:
                String[] dInput = task.split("/by", 2);
                if (dInput.length == 1) {
                    throw new DateException("deadline");
                }
                this.description = dInput[0];
                this.date = dInput[1];
                break;
            case EVENT:
                String[] eInput = task.split("/at", 2);
                if (eInput.length == 1) {
                    throw new DateException("event");
                }
                this.description = eInput[0];
                this.date = eInput[1];
                break;
        }
    }

    public void execute() {
        switch (type) {
            case TODO:
                Task newToDo = new ToDo(this.description);
                taskManager.addTask(newToDo);
                break;
            case DEADLINE:
                Task newDeadline = new Deadline(this.description, this.date);
                taskManager.addTask(newDeadline);
                break;
            case EVENT:
                Task newEvent = new Event(this.description, this.date);
                taskManager.addTask(newEvent);
                break;
        }
    }
}

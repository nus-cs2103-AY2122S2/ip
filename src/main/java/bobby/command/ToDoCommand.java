package bobby.command;

import bobby.exception.BobbyException;
import bobby.Storage;
import bobby.task.TaskList;
import bobby.task.ToDo;
import bobby.exception.ToDoException;
import bobby.Ui;

public class ToDoCommand extends Command {
    private String fullCommand;
    private String[] fullCommandArr;

    public ToDoCommand(String fullCommand, String[] fullCommandArr) {
        this.fullCommand = fullCommand;
        this.fullCommandArr = fullCommandArr;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BobbyException {
        ui.printLongLine();
        if (fullCommand.substring(4).isBlank()) {                   // nothing after command
            throw new ToDoException("todo");
        }
        ToDo newToDo = new ToDo(fullCommand.substring(5));
        ui.todoMessage(newToDo);
        tasks.addTask(newToDo);
        storage.saveTasks(tasks.getTaskList());
        ui.printNumTasks(tasks);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ToDoCommand;
    }
}

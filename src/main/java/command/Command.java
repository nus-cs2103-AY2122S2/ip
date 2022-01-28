package duke.command;

import duke.dukeexceptions.*;
import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.io.IOException;

public abstract class Command{
    String parameter;

    protected Command(String parameter) {
        this.parameter = parameter;
    }

    public abstract void run(TaskList taskList, Ui ui, Storage storage) throws DukeExceptions;

    public static Command getCommand(String command, String parameter) throws InvalidCommand {
        if (command.equals("BYE")) {
            return new ByeCommand(parameter);
        } else if (command.equals("LIST")) {
            return new ListCommand(parameter);
        } else if (command.equals("MARK")) {
            return new MarkCommand(parameter);
        } else if (command.equals("UNMARK")) {
            return new UnmarkCommand(parameter);
        } else if (command.equals("TODO")) {
            return new TodoCommand(parameter);
        } else if (command.equals("DEADLINE")) {
            return new DeadlineCommand(parameter);
        } else if (command.equals("EVENT")) {
            return new EventCommand(parameter);
        } else if (command.equals("DELETE")) {
            return new DeleteCommand(parameter);
        } else {
            throw InvalidCommand.createInvalidCommand(command);
        }
    }
}

class ByeCommand extends Command {
    ByeCommand(String parameter) {
        super(parameter);
    }

    @Override
    public void run(TaskList taskList, Ui ui, Storage storage) throws DukeExceptions {
        ui.showExitScreen();
        System.exit(0);
    }
}

class ListCommand extends Command {
    ListCommand(String parameter) {
        super(parameter);
    }

    @Override
    public void run(TaskList taskList, Ui ui, Storage storage) throws DukeExceptions {
        ui.taskListDisplay.run(taskList);
    }
}

class MarkCommand extends Command {
    MarkCommand(String parameter) {
        super(parameter);
    }

    @Override
    public void run(TaskList taskList, Ui ui, Storage storage) throws DukeExceptions {
        if (this.parameter.isBlank()) {
            throw EmptyNumber.createEmptyNumber("Mark");
        }
        try {
            String task = taskList.markTask(Integer.parseInt(this.parameter));
            ui.showMarkedTaskDisplay(task);
            storage.updateData(taskList);
        } catch (IndexOutOfBoundsException e) {
            throw new ListIndexOutOfBound();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class UnmarkCommand extends Command {
    UnmarkCommand(String parameter) {
        super(parameter);
    }

    @Override
    public void run(TaskList taskList, Ui ui, Storage storage) throws DukeExceptions {
        if (this.parameter.isBlank()) {
            throw EmptyNumber.createEmptyNumber("Unmark");
        }
        try {
            String task = taskList.unmarkTask(Integer.parseInt(this.parameter));
            ui.showUnmarkedTaskDisplay(task);
            storage.updateData(taskList);
        } catch (IndexOutOfBoundsException e) {
            throw new ListIndexOutOfBound();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class TodoCommand extends Command {
    TodoCommand(String parameter) {
        super(parameter);
    }

    @Override
    public void run(TaskList taskList, Ui ui, Storage storage) throws DukeExceptions {
        if (this.parameter.isBlank()) {
            throw EmptyTask.createEmptyTask("todo");
        }
        Task todo = Task.createTask("TODO", false, this.parameter, null);
        taskList.addTask(todo);
        ui.newTaskDisplay(todo, taskList);
        try {
            storage.updateData(taskList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class DeadlineCommand extends Command {
    DeadlineCommand(String parameter) {
        super(parameter);
    }

    @Override
    public void run(TaskList taskList, Ui ui, Storage storage) throws DukeExceptions {
        if (this.parameter.isBlank()) {
            throw EmptyTask.createEmptyTask("dateline");
        }
        int index = this.parameter.indexOf("/by ");
        if (index < 0) {
            throw EmptyDate.createEmptyDate("dateline");
        }
        String task = this.parameter.substring(0, index);
        if (task.isBlank()) {
            throw EmptyTask.createEmptyTask("dateline");
        }
        String date = this.parameter.substring(index + 4, parameter.length());
        if (date.isBlank()) {
            throw EmptyDate.createEmptyDate("dateline");
        }
        Task deadline = Task.createTask("DEADLINE", false, task, date);
        taskList.addTask(deadline);
        ui.newTaskDisplay(deadline, taskList);
        try {
            storage.updateData(taskList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class EventCommand extends Command {
    EventCommand(String parameter) {
        super(parameter);
    }

    @Override
    public void run(TaskList taskList, Ui ui, Storage storage) throws DukeExceptions {
        if (this.parameter.isBlank()) {
            throw EmptyTask.createEmptyTask("event");
        }
        int index = this.parameter.indexOf("/at ");
        if (index < 0) {
            throw EmptyDate.createEmptyDate("event");
        }
        String task = this.parameter.substring(0, index);
        if (task.isBlank()) {
            throw EmptyTask.createEmptyTask("event");
        }
        String date = this.parameter.substring(index + 4, parameter.length());
        if (date.isBlank()) {
            throw EmptyDate.createEmptyDate("event");
        }
        Task event = Task.createTask("EVENT", false, task, date);
        taskList.addTask(event);
        ui.newTaskDisplay(event, taskList);
        try {
            storage.updateData(taskList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class DeleteCommand extends Command {
    DeleteCommand(String parameter) {
        super(parameter);
    }

    @Override
    public void run(TaskList taskList, Ui ui, Storage storage) throws DukeExceptions {
        if (parameter.isBlank()) {
            throw EmptyNumber.createEmptyNumber("Delete");
        }
        try {
            String deletedTask = taskList.deleteFromIndex(Integer.parseInt(parameter));
            ui.showDeleteTaskDisplay(deletedTask, taskList);
            storage.updateData(taskList);
        } catch (IndexOutOfBoundsException e) {
            throw new ListIndexOutOfBound();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
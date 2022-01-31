package duke.command;

import duke.dukeexceptions.*;
import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.io.IOException;

public abstract class Command {
    protected String parameter;

    protected Command(String parameter) {
        this.parameter = parameter;
    }

    public abstract void run(TaskList taskList, Ui ui, Storage storage) throws DukeExceptions;

    public static Command getCommand(String command, String parameter) throws InvalidCommand {
        // Check if the command entered by the user is valid, then return the command on that type, otherwise, throws
        // the InvalidCommand exception.
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
    protected ByeCommand(String parameter) {
        super(parameter);
    }

    @Override
    public void run(TaskList taskList, Ui ui, Storage storage) throws DukeExceptions {
        // Shows the exit screen.
        ui.showExitScreen();

        // Exits duke.
        System.exit(0);
    }
}

class ListCommand extends Command {
    protected ListCommand(String parameter) {
        super(parameter);
    }

    @Override
    public void run(TaskList taskList, Ui ui, Storage storage) throws DukeExceptions {
        // Shows the screen with all the task in task list.
        ui.taskListDisplay.run(taskList);
    }
}

class MarkCommand extends Command {
    protected MarkCommand(String parameter) {
        super(parameter);
    }

    @Override
    public void run(TaskList taskList, Ui ui, Storage storage) throws DukeExceptions {
        // Checks if the user did not enter a number, then throw an exception.
        if (parameter.isBlank()) {
            throw EmptyNumber.createEmptyNumber("Mark");
        }

        // Marks the task as indicated in the index of the task list.
        try {
            String task = taskList.markTask(Integer.parseInt(parameter));
            ui.showMarkedTaskDisplay(task);
            storage.updateData(taskList);
        } catch (IndexOutOfBoundsException e) {
            // If the user enters a number that is out of bound of the task list.
            throw new ListIndexOutOfBound();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            // If the user enters a parameter that is not a number.
            throw new InvalidNumber();
        }
    }
}

class UnmarkCommand extends Command {
    protected UnmarkCommand(String parameter) {
        super(parameter);
    }

    @Override
    public void run(TaskList taskList, Ui ui, Storage storage) throws DukeExceptions {
        // Checks if the user did not enter a number, then throw an EmptyNumber exception.
        if (parameter.isBlank()) {
            throw EmptyNumber.createEmptyNumber("Unmark");
        }

        // Unmarks the task as indicated in the index of the task list.
        try {
            String task = taskList.unmarkTask(Integer.parseInt(parameter));
            ui.showUnmarkedTaskDisplay(task);
            storage.updateData(taskList);
        } catch (IndexOutOfBoundsException e) {
            // If the user enters a number that is out of bound of the task list.
            throw new ListIndexOutOfBound();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            // If the user enters a parameter that is not a number.
            throw new InvalidNumber();
        }
    }
}

class TodoCommand extends Command {
    protected TodoCommand(String parameter) {
        super(parameter);
    }

    @Override
    public void run(TaskList taskList, Ui ui, Storage storage) throws DukeExceptions {
        // Checks if the user enters an empty parameter.
        if (parameter.isBlank()) {
            throw EmptyTask.createEmptyTask("todo");
        }

        // Creates a new TODO task.
        Task todo = Task.createTask("TODO", false, parameter, null);
        taskList.addTask(todo);

        // Shows the screen with the new task that was recently created.
        ui.newTaskDisplay(todo, taskList);

        // Updates the task list in the file.
        try {
            storage.updateData(taskList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class DeadlineCommand extends Command {
    protected DeadlineCommand(String parameter) {
        super(parameter);
    }

    @Override
    public void run(TaskList taskList, Ui ui, Storage storage) throws DukeExceptions {
        // Check if the user did not enter a task name and date, then throw Empty Task parameter.
        if (parameter.isBlank()) {
            throw EmptyTask.createEmptyTask("dateline");
        }

        // Get the index "/by" from the parameter.
        int index = parameter.indexOf("/by ");
        // If the user did not enter /by, then throw the EmptyDate exception.
        if (index < 0) {
            throw EmptyDate.createEmptyDate("dateline");
        }

        // Gets the task name form the parameter.
        String task = parameter.substring(0, index);
        // If the user did not enter a task name, then throw empty task exception.
        if (task.isBlank()) {
            throw EmptyTask.createEmptyTask("dateline");
        }

        // Get the date from the parameter.
        String date = parameter.substring(index + 4, parameter.length());
        // If the user did not enter a date after "/by".
        if (date.isBlank()) {
            throw EmptyDate.createEmptyDate("dateline");
        }

        // Creates a new deadline task, then add it to the task list.
        Task deadline = Task.createTask("DEADLINE", false, task, date);
        taskList.addTask(deadline);

        // Shows the recently created deadline task.
        ui.newTaskDisplay(deadline, taskList);

        // Updates the task list in the file.
        try {
            storage.updateData(taskList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class EventCommand extends Command {
    protected EventCommand(String parameter) {
        super(parameter);
    }

    @Override
    public void run(TaskList taskList, Ui ui, Storage storage) throws DukeExceptions {
        // Check if the user did not enter a task name and date, then throw Empty Task parameter.
        if (parameter.isBlank()) {
            throw EmptyTask.createEmptyTask("event");
        }

        // Get the index "/at" from the parameter.
        int index = parameter.indexOf("/at ");
        if (index < 0) {
            throw EmptyDate.createEmptyDate("event");
        }

        // Gets the task name form the parameter.
        String task = parameter.substring(0, index);
        // If the user did not enter a task name, then throw empty task exception.
        if (task.isBlank()) {
            throw EmptyTask.createEmptyTask("event");
        }

        // Get the date from the parameter.
        String date = parameter.substring(index + 4, parameter.length());
        // If the user did not enter a date after "/at".
        if (date.isBlank()) {
            throw EmptyDate.createEmptyDate("event");
        }

        // Creates a new event task, then add it to the task list.
        Task event = Task.createTask("EVENT", false, task, date);
        taskList.addTask(event);

        // Shows the recently created event task.
        ui.newTaskDisplay(event, taskList);

        // Updates the task list in the file.
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
        // If the user did not enter a number, returns an empty number parameter.
        if (parameter.isBlank()) {
            throw EmptyNumber.createEmptyNumber("Delete");
        }

        // Deletes the task from the task list based on the index. Then update the file.
        try {
            String deletedTask = taskList.deleteFromIndex(Integer.parseInt(parameter));
            ui.showDeleteTaskDisplay(deletedTask, taskList);
            storage.updateData(taskList);
        } catch (IndexOutOfBoundsException e) {
            // If the user enters a number that is out of bound of the task list.
            throw new ListIndexOutOfBound();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            // If the user enters a parameter that is not a number.
            throw new InvalidNumber();
        }
    }
}
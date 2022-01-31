package duke.command;

import duke.dukeexceptions.*;
import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.io.IOException;

/**
 * The Command object that represents the different commands of Duke.
 */
public abstract class Command{
    /** The parameter entered by the user which varies based on the type of comments. */
    String parameter;

    /**
     * Creates a new Command.
     *
     * @param parameter The parameter entered by the user.
     */
    protected Command(String parameter) {
        this.parameter = parameter;
    }

    /**
     * Runs the various commands based on the type of command it is.
     *
     * @param taskList The TaskList which the command will act on.
     * @param ui The UI on which the command will act on.
     * @param storage The Storage on which the command will act on.
     * @throws DukeExceptions The exception from Duke if there are errors encountered.
     */
    public abstract void run(TaskList taskList, Ui ui, Storage storage) throws DukeExceptions;

    /**
     * Creates the command based on the user's input in the command line.
     *
     * @param command The type of command.
     * @param parameter The parameter which varies based on the type of command.
     * @return The command with its type based on command.
     * @throws InvalidCommand If the user enters either an invalid command or empty command.
     */
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

/**
 * The Bye command exits the Duke application.
 */
class ByeCommand extends Command {
    /** Constructs a bye command without expecting a parameter. */
    ByeCommand(String parameter) {
        super(parameter);
    }

    /**
     * Causes the Duke application to exit.
     *
     * @param taskList The TaskList which the command will act on.
     * @param ui The UI on which the command will act on.
     * @param storage The Storage on which the command will act on.
     * @throws DukeExceptions Should not have an exception unless it is an unexpected error.
     */
    @Override
    public void run(TaskList taskList, Ui ui, Storage storage) throws DukeExceptions {
        ui.showExitScreen();
        System.exit(0);
    }
}

/**
 * The list command shows the task list to the user.
 */
class ListCommand extends Command {
    /** Constructs the list command without expecting a parameter. */
    ListCommand(String parameter) {
        super(parameter);
    }

    /**
     * Shows all the tasks in the task list.
     *
     * @param taskList The TaskList which the command will act on.
     * @param ui The UI on which the command will act on.
     * @param storage The Storage on which the command will act on.
     * @throws DukeExceptions Should not have an exception unless it is an unexpected error.
     */
    @Override
    public void run(TaskList taskList, Ui ui, Storage storage) throws DukeExceptions {
        ui.taskListDisplay.run(taskList);
    }
}

/**
 * The mark command marks the tasks, representing the tasks as complete.
 */
class MarkCommand extends Command {
    /** Constructs the mark command with index number of the item in the task list as the parameter. */
    MarkCommand(String parameter) {
        super(parameter);
    }

    /**
     * Marks the task indicated by the index in the task list.
     *
     * @param taskList The TaskList which the command will act on.
     * @param ui The UI on which the command will act on.
     * @param storage The Storage on which the command will act on.
     * @throws DukeExceptions Occurs if the user did not enter a number or the number is out of range from the index.
     */
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

/**
 * The unmarks command unmarks the task, indicating it as incomplete.
 */
class UnmarkCommand extends Command {
    /** Constructs the unmark command with index number of the item in the task list as the parameter. */
    UnmarkCommand(String parameter) {
        super(parameter);
    }

    /**
     * Unmarks the task indicated by the index in the task list.
     *
     * @param taskList The TaskList which the command will act on.
     * @param ui The UI on which the command will act on.
     * @param storage The Storage on which the command will act on.
     * @throws DukeExceptions Occurs if the user did not enter a number or the number is out of range from the index.
     */
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

/**
 * The Todo Command dds a todo tasks in the task list.
 */
class TodoCommand extends Command {
    /** Constructs the TODO command that takes in the name of the TODO task as the parameter. */
    TodoCommand(String parameter) {
        super(parameter);
    }

    /**
     * Adds a todo task in the task list.
     *
     * @param taskList The TaskList which the command will act on.
     * @param ui The UI on which the command will act on.
     * @param storage The Storage on which the command will act on.
     * @throws DukeExceptions If the user did not indicate a name of the task.
     */
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

/**
 * The deadline command adds a deadline tasks.
 */
class DeadlineCommand extends Command {
    /** Creates the deadline command with task name /by dd/mm/yyyy HHmm as parameter. */
    DeadlineCommand(String parameter) {
        super(parameter);
    }

    /**
     * Adds a deadline command into the task list.
     *
     * @param taskList The TaskList which the command will act on.
     * @param ui The UI on which the command will act on.
     * @param storage The Storage on which the command will act on.
     * @throws DukeExceptions If the users enters no parameter, no date or invalid date.
     */
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

/**
 * The event command adds an event task in the task list.
 */
class EventCommand extends Command {
    /** Creates the event command with task name /at dd/mm/yyyy HHmm as parameter. */
    EventCommand(String parameter) {
        super(parameter);
    }

    /**
     * Adds an event task in the task list.
     *
     * @param taskList The TaskList which the command will act on.
     * @param ui The UI on which the command will act on.
     * @param storage The Storage on which the command will act on.
     * @throws DukeExceptions If the users enters no parameter, no date or invalid date.
     */
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

/**
 * The delete command deletes a task in the task list.
 */
class DeleteCommand extends Command {
    /** Constructs the delete command with index number of the item in the task list as the parameter. */
    DeleteCommand(String parameter) {
        super(parameter);
    }

    /**
     * Deletes the task indicated by the index number in the task list.
     *
     * @param taskList The TaskList which the command will act on.
     * @param ui The UI on which the command will act on.
     * @param storage The Storage on which the command will act on.
     * @throws DukeExceptions If the user did not enter a number.
     */
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
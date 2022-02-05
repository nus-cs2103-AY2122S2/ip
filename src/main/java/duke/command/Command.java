package duke.command;


import duke.dukeexceptions.*;
import duke.dukeexceptions.EmptyDateException;
import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.io.IOException;

/**
 * The Command object that represents the different commands of Duke.
 */
public abstract class Command {
    protected String parameter;

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
     * @throws InvalidCommandException If the user enters either an invalid command or empty command.
     */
    public static Command getCommand(String command, String parameter) throws InvalidCommandException {
        // Check if the command entered by the user is valid, then return the command on that type, otherwise, throws
        // the InvalidCommandException exception.
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
        } else if (command.equals("FIND")) {
            return new FindCommand(parameter);
        } else {
            throw InvalidCommandException.createInvalidCommand(command);
        }
    }
}

/**
 * The Bye command exits the Duke application.
 */
class ByeCommand extends Command {
    /** Constructs a bye command without expecting a parameter. */
    protected ByeCommand(String parameter) {
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
        // Shows the exit screen.
        ui.showExitScreen();

        // Exits duke.
        System.exit(0);
    }
}

/**
 * The list command shows the task list to the user.
 */
class ListCommand extends Command {
    /** Constructs the list command without expecting a parameter. */
    protected ListCommand(String parameter) {
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
        // Shows the screen with all the task in task list.
        ui.taskListDisplay.run(taskList);
    }
}

/**
 * The mark command marks the tasks, representing the tasks as complete.
 */
class MarkCommand extends Command {
    /** Constructs the mark command with index number of the item in the task list as the parameter. */
    protected MarkCommand(String parameter) {
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
        // Checks if the user did not enter a number, then throw an exception.
        if (parameter.isBlank()) {
            throw EmptyNumberException.createEmptyNumber("Mark");
        }

        // Marks the task as indicated in the index of the task list.
        try {
            String task = taskList.markTask(Integer.parseInt(parameter));
            ui.showMarkedTaskDisplay(task);
            storage.updateData(taskList);
        } catch (IndexOutOfBoundsException e) {
            // If the user enters a number that is out of bound of the task list.
            throw new ListIndexOutOfBoundException();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            // If the user enters a parameter that is not a number.
            throw new InvalidNumberException();
        }
    }
}

/**
 * The unmarks command unmarks the task, indicating it as incomplete.
 */
class UnmarkCommand extends Command {
    /** Constructs the unmark command with index number of the item in the task list as the parameter. */
    protected UnmarkCommand(String parameter) {
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
        // Checks if the user did not enter a number, then throw an EmptyNumberException exception.
        if (parameter.isBlank()) {
            throw EmptyNumberException.createEmptyNumber("Unmark");
        }

        // Unmarks the task as indicated in the index of the task list.
        try {
            String task = taskList.unmarkTask(Integer.parseInt(parameter));
            ui.showUnmarkedTaskDisplay(task);
            storage.updateData(taskList);
        } catch (IndexOutOfBoundsException e) {
            // If the user enters a number that is out of bound of the task list.
            throw new ListIndexOutOfBoundException();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            // If the user enters a parameter that is not a number.
            throw new InvalidNumberException();
        }
    }
}

/**
 * The Todo Command dds a todo tasks in the task list.
 */
class TodoCommand extends Command {
    /** Constructs the TODO command that takes in the name of the TODO task as the parameter. */
    protected TodoCommand(String parameter) {
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
        // Checks if the user enters an empty parameter.
        if (parameter.isBlank()) {
            throw EmptyTaskException.createEmptyTask("todo");
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

/**
 * The deadline command adds a deadline tasks.
 */
class DeadlineCommand extends Command {
    /** Creates the deadline command with task name /by dd/mm/yyyy HHmm as parameter. */
    protected DeadlineCommand(String parameter) {
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
        // Check if the user did not enter a task name and date, then throw Empty Task parameter.
        if (parameter.isBlank()) {
            throw EmptyTaskException.createEmptyTask("dateline");
        }

        // Get the index "/by" from the parameter.
        int index = parameter.indexOf("/by ");
        // If the user did not enter /by, then throw the EmptyDateException exception.
        if (index < 0) {
            throw EmptyDateException.createEmptyDate("dateline");
        }

        // Gets the task name form the parameter.
        String task = parameter.substring(0, index);
        // If the user did not enter a task name, then throw empty task exception.
        if (task.isBlank()) {
            throw EmptyTaskException.createEmptyTask("dateline");
        }

        // Get the date from the parameter.
        String date = parameter.substring(index + 4, parameter.length());
        // If the user did not enter a date after "/by".
        if (date.isBlank()) {
            throw EmptyDateException.createEmptyDate("dateline");
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

/**
 * The event command adds an event task in the task list.
 */
class EventCommand extends Command {
    /** Creates the event command with task name /at dd/mm/yyyy HHmm as parameter. */
    protected EventCommand(String parameter) {
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
        // Check if the user did not enter a task name and date, then throw Empty Task parameter.
        if (parameter.isBlank()) {
            throw EmptyTaskException.createEmptyTask("event");
        }

        // Get the index "/at" from the parameter.
        int index = parameter.indexOf("/at ");
        if (index < 0) {
            throw EmptyDateException.createEmptyDate("event");
        }

        // Gets the task name form the parameter.
        String task = parameter.substring(0, index);
        // If the user did not enter a task name, then throw empty task exception.
        if (task.isBlank()) {
            throw EmptyTaskException.createEmptyTask("event");
        }

        // Get the date from the parameter.
        String date = parameter.substring(index + 4, parameter.length());
        // If the user did not enter a date after "/at".
        if (date.isBlank()) {
            throw EmptyDateException.createEmptyDate("event");
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

/**
 * The delete command deletes a task in the task list.
 */
class DeleteCommand extends Command{
    /**
     * Constructs the delete command with index number of the item in the task list as the parameter.
     */
    DeleteCommand(String parameter) {
        super(parameter);
    }

    /**
     * Deletes the task indicated by the index number in the task list.
     *
     * @param taskList The TaskList which the command will act on.
     * @param ui       The UI on which the command will act on.
     * @param storage  The Storage on which the command will act on.
     * @throws DukeExceptions If the user did not enter a number.
     */
    @Override
    public void run(TaskList taskList, Ui ui, Storage storage) throws DukeExceptions {
        // If the user did not enter a number, returns an empty number parameter.
        if (parameter.isBlank()) {
            throw EmptyNumberException.createEmptyNumber("Delete");
        }

        // Deletes the task from the task list based on the index. Then update the file.
        try {
            String deletedTask = taskList.deleteFromIndex(Integer.parseInt(parameter));
            ui.showDeleteTaskDisplay(deletedTask, taskList);
            storage.updateData(taskList);
        } catch (IndexOutOfBoundsException e) {
            // If the user enters a number that is out of bound of the task list.
            throw new ListIndexOutOfBoundException();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            // If the user enters a parameter that is not a number.
            throw new InvalidNumberException();
        }
    }
}

/**
 * Finds all the tasks in the task list that contain the keyword entered by the user.
 */
class FindCommand extends Command {
    /**
     * Creates a new FindCommand object.
     *
     * @param parameter The parameter of the command entered by the user.
     */
    protected FindCommand(String parameter) {
        super(parameter);
    }

    @Override
    public void run(TaskList taskList, Ui ui, Storage storage) throws DukeExceptions {
        // Check if the user did not enter a keyword, then throw the EmptyKeywordException exception.
        if (parameter.isBlank()) {
            throw new EmptyKeywordException();
        }

        // Gets a new task list which the tasks that contains the keyword.
        TaskList filteredTaskList = taskList.findTasksFromKeyword(parameter);

        // Gets the UI to show the filtered task list.
        ui.showFilteredTaskListDisplay(parameter, filteredTaskList);
    }
}
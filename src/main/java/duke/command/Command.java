package duke.command;

import java.io.IOException;

import duke.dukeexceptions.DukeExceptions;
import duke.dukeexceptions.EmptyDateException;
import duke.dukeexceptions.EmptyKeywordException;
import duke.dukeexceptions.EmptyNumberException;
import duke.dukeexceptions.EmptyTaskException;
import duke.dukeexceptions.InvalidCommandException;
import duke.dukeexceptions.InvalidNumberException;
import duke.dukeexceptions.ListIndexOutOfBoundException;
import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;

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
     * @param storage The Storage on which the command will act on.
     * @return The string that will be shown on the Dialogue Box.
     * @throws DukeExceptions The exception from Duke if there are errors encountered.
     */
    public abstract String run(TaskList taskList, Storage storage) throws DukeExceptions;

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
        } else if (command.equals("HELP")) {
            return new HelpCommand(parameter);
        } else {
            // Throws invalid command as the user gives a invalid command.
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
     * @param storage The Storage on which the command will act on.
     * @return Empty string, there is no significance.
     * @throws DukeExceptions Should not have an exception unless it is an unexpected error.
     */
    @Override
    public String run(TaskList taskList, Storage storage) throws DukeExceptions {
        // Exits duke.
        System.exit(0);
        return "";
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
     * @param storage The Storage on which the command will act on.
     * @return All the tasks in the task list which will then be shown in the Dialogue box.
     * @throws DukeExceptions Should not have an exception unless it is an unexpected error.
     */
    @Override
    public String run(TaskList taskList, Storage storage) throws DukeExceptions {
        // Shows the screen with all the task in task list.
        return "Here you go:\n" + taskList.printTasks();
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
     * @param storage The Storage on which the command will act on.
     * @return The recently marked task that will be shown on the Dialogue box.
     * @throws DukeExceptions Occurs if the user did not enter a number or the number is out of range from the index.
     */
    @Override
    public String run(TaskList taskList, Storage storage) throws DukeExceptions {
        String result = "";

        // Checks if the user did not enter a number, then throw an exception.
        if (parameter.isBlank()) {
            throw EmptyNumberException.createEmptyNumber("Mark");
        }

        // Marks the task as indicated in the index of the task list.
        try {
            String task = taskList.markTask(Integer.parseInt(parameter));
            storage.updateData(taskList);
            result += "Marked the following task to the list:\n" + task + "\nGood job by the way";
        } catch (IndexOutOfBoundsException e) {
            // If the user enters a number that is out of bound of the task list.
            throw new ListIndexOutOfBoundException();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            // If the user enters a parameter that is not a number.
            throw new InvalidNumberException();
        }
        return result;
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
     * @param storage The Storage on which the command will act on.
     * @return The recently unmarked task that will be shown in the dialogue box.
     * @throws DukeExceptions Occurs if the user did not enter a number or the number is out of range from the index.
     */
    @Override
    public String run(TaskList taskList, Storage storage) throws DukeExceptions {
        String result = "";

        // Checks if the user did not enter a number, then throw an EmptyNumberException exception.
        if (parameter.isBlank()) {
            throw EmptyNumberException.createEmptyNumber("Unmark");
        }

        // Unmarks the task as indicated in the index of the task list.
        try {
            String task = taskList.unmarkTask(Integer.parseInt(parameter));
            storage.updateData(taskList);
            result += "Unmarked the following task to the list:\n" + task + "\nHope you get it done soon!";
        } catch (IndexOutOfBoundsException e) {
            // If the user enters a number that is out of bound of the task list.
            throw new ListIndexOutOfBoundException();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            // If the user enters a parameter that is not a number.
            throw new InvalidNumberException();
        }

        return result;
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
     * @param storage The Storage on which the command will act on.
     * @return The recently created todo which will then be shown on the dialogue box.
     * @throws DukeExceptions If the user did not indicate a name of the task.
     */
    @Override
    public String run(TaskList taskList, Storage storage) throws DukeExceptions {
        // Checks if the user enters an empty parameter.
        if (parameter.isBlank()) {
            throw EmptyTaskException.createEmptyTask("todo");
        }

        // Creates a new TODO task.
        Task todo = Task.createTask("TODO", false, parameter, null);
        taskList.addTask(todo);

        // Updates the task list in the file.
        try {
            storage.updateData(taskList);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "Added the following todo into the list:\n" + todo;
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
     * @param storage The Storage on which the command will act on.
     * @return The recently created deadline that will be then shown in the dialogue box.
     * @throws DukeExceptions If the users enters no parameter, no date or invalid date.
     */
    @Override
    public String run(TaskList taskList, Storage storage) throws DukeExceptions {
        // Check if the user did not enter a task name and date, then throw Empty Task parameter.
        if (parameter.isBlank()) {
            throw EmptyTaskException.createEmptyTask("deadline");
        }

        // Get the index "/by" from the parameter.
        int index = parameter.indexOf(" /by ");
        // If the user did not enter /by, then throw the EmptyDateException exception.
        if (index < 0) {
            throw EmptyDateException.createEmptyDate("deadline");
        }

        // Gets the task name form the parameter.
        String task = parameter.substring(0, index);
        // If the user did not enter a task name, then throw empty task exception.
        if (task.isBlank()) {
            throw EmptyTaskException.createEmptyTask("deadline");
        }

        // Get the date from the parameter.
        String date = parameter.substring(index + 5, parameter.length());
        // If the user did not enter a date after "/by".
        if (date.isBlank()) {
            throw EmptyDateException.createEmptyDate("deadline");
        }

        // Creates a new deadline task, then add it to the task list.
        Task deadline = Task.createTask("DEADLINE", false, task, date);
        taskList.addTask(deadline);

        // Updates the task list in the file.
        try {
            storage.updateData(taskList);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "Added the following deadline into the list:\n" + deadline;
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
     * @param storage The Storage on which the command will act on.
     * @return The recently created event which will be then sent to the dialogue box.
     * @throws DukeExceptions If the users enters no parameter, no date or invalid date.
     */
    @Override
    public String run(TaskList taskList, Storage storage) throws DukeExceptions {
        // Check if the user did not enter a task name and date, then throw Empty Task parameter.
        if (parameter.isBlank()) {
            throw EmptyTaskException.createEmptyTask("event");
        }

        // Get the index "/at" from the parameter.
        int index = parameter.indexOf(" /at ");
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
        String date = parameter.substring(index + 5, parameter.length());
        // If the user did not enter a date after "/at".
        if (date.isBlank()) {
            throw EmptyDateException.createEmptyDate("event");
        }

        // Creates a new event task, then add it to the task list.
        Task event = Task.createTask("EVENT", false, task, date);
        taskList.addTask(event);

        // Updates the task list in the file.
        try {
            storage.updateData(taskList);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "Added the following event into the list:\n" + event;
    }
}

/**
 * The delete command deletes a task in the task list.
 */
class DeleteCommand extends Command {
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
     * @param storage The Storage on which the command will act on.
     * @return The recently deleted task that will be sent to the dialogue box.
     * @throws DukeExceptions If the user did not enter a number.
     */
    @Override
    public String run(TaskList taskList, Storage storage) throws DukeExceptions {
        String result = "";

        // If the user did not enter a number, returns an empty number parameter.
        if (parameter.isBlank()) {
            throw EmptyNumberException.createEmptyNumber("Delete");
        }

        // Deletes the task from the task list based on the index. Then update the file.
        try {
            String deletedTask = taskList.deleteFromIndex(Integer.parseInt(parameter));
            storage.updateData(taskList);
            result += "Deleted the following task:\n" + deletedTask;
        } catch (IndexOutOfBoundsException e) {
            // If the user enters a number that is out of bound of the task list.
            throw new ListIndexOutOfBoundException();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            // If the user enters a parameter that is not a number.
            throw new InvalidNumberException();
        }

        return result;
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

    /**
     * Gets all the tasks that contains the keyword and shows it to the user.
     *
     * @param taskList The TaskList which the command will act on.
     * @param storage The Storage on which the command will act on.
     * @return The tasks that contain the keyword.
     * @throws DukeExceptions
     */
    @Override
    public String run(TaskList taskList, Storage storage) throws DukeExceptions {
        // Check if the user did not enter a keyword, then throw the EmptyKeywordException exception.
        if (parameter.isBlank()) {
            throw new EmptyKeywordException();
        }

        // Gets a new task list which the tasks that contains the keyword.
        TaskList filteredTaskList = taskList.findTasksFromKeyword(parameter);

        return "These are the tasks that have the keyword \"" + parameter + "\"\n" + filteredTaskList.printTasks();
    }
}

class HelpCommand extends Command {
    /**
     * Creates a new Help Command.
     *
     * @param parameter The parameter entered by the user.
     */
    protected HelpCommand(String parameter) {
        super(parameter);
    }

    /**
     * Shows all the manual for all commands in duke.
     *
     * @param taskList The TaskList which the command will act on.
     * @param storage The Storage on which the command will act on.
     * @return The string that shows the manual for all commands.
     * @throws DukeExceptions There is no Duke exception expected.
     */
    @Override
    public String run(TaskList taskList, Storage storage) throws DukeExceptions {
        String openingSentence = "Here are the tasks avaliable for duke:\n";
        String listHelp = "List: Shows all the tasks in duke\n";
        String markHelp = "Mark <number>: Marks the task in the index <number>\n";
        String unmarkHelp = "Unmark <number>: Unmarks the task in the index <number>\n";
        String findHelp = "Find <keyword>: finds all the task that contains <keyword>\n";
        String deleteHelp = "Delete <number>: Deletes the task in index <number>\n";
        String todoHelp = "Todo <name of todo>: Creates a new todo <name of todo>\n";
        String deadlineHelp = "Deadline <name of deadline> <date of deadline in dd/mm/yyyy HHmm>: "
                + "Creates a new deadline <name of deadline> at <date of deadline in dd/mm/yyyy HHmm>\n";
        String eventHelp = "Event <name of deadline> <date of deadline in dd/mm/yyyy HHmm>: "
                + "Creates a new event <name of deadline> at <date of deadline in dd/mm/yyyy HHmm>\n";
        String byeHelp = "Bye: Exits duke.";
        String result = openingSentence
                .concat(listHelp)
                .concat(markHelp)
                .concat(unmarkHelp)
                .concat(findHelp)
                .concat(deleteHelp)
                .concat(todoHelp)
                .concat(deadlineHelp)
                .concat(eventHelp)
                .concat(byeHelp);
        System.out.println(result);
        return result;
    }
}

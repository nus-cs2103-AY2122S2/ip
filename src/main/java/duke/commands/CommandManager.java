package duke.commands;

import java.io.IOException;
import java.time.format.DateTimeParseException;

import duke.exceptions.DateException;
import duke.exceptions.DukeException;
import duke.exceptions.InvalidOperationException;
import duke.exceptions.TaskIndexException;
import duke.tasks.TaskManager;
import duke.ui.ListLoader;
import duke.ui.ListStorage;
import duke.ui.UiManager;

/**
 * CommandManager Object that handles initialisation,
 * receiving and dispatching of commands.
 */
public class CommandManager {
    private final TaskManager taskManager;
    private final UiManager uiManager;
    private final ListLoader listLoader;


    /**
     * Constructs the CommandManager Object.
     */
    public CommandManager() {
        this.uiManager = new UiManager();
        this.taskManager = new TaskManager(this.uiManager, new ListStorage());
        this.listLoader = new ListLoader(this.taskManager);
    }

    /**
     * Initialises and runs the program.
     * Scans String input from console and issues
     * commands to different objects via switching.
     * Checks for and handles errors.
     * Saves task list into a ser file upon exit.
     */
    public void run() {
        uiManager.welcome();
        try {
            this.listLoader.loadList();
            uiManager.printList(taskManager);
        } catch (IOException e) {
            uiManager.printLoadFail();
        } catch (ClassNotFoundException e) {
            uiManager.showErrorMessage("Oops! There was a corrupted task in the previous list!");
        }
    }

    /**
     * Runs command based on a given string command.
     * Returns a processed string to be printed on the GUI.
     *
     * @param s command to be parsed and processed
     * @return String to be printed on the GUI
     */
    public String runCommand(String s) {
        try {
            String[] command = uiManager.parseCommand(s);
            switch (command[0]) {
            case "bye":
                return taskManager.exit();
            case "list":
                return uiManager.printList(taskManager);
            case "todo":
                AddTaskCommand todo = new AddTaskCommand(this.uiManager, this.taskManager, command[1], Type.TODO);
                return todo.execute();
            case "deadline":
                AddTaskCommand deadline =
                        new AddTaskCommand(this.uiManager, this.taskManager, command[1], Type.DEADLINE);
                return deadline.execute();
            case "event":
                AddTaskCommand event = new AddTaskCommand(this.uiManager, this.taskManager, command[1], Type.EVENT);
                return event.execute();
            case "mark":
                NumCommand mark = new NumCommand(this.uiManager, this.taskManager, command[1], Type.MARK);
                return mark.execute();
            case "unmark":
                NumCommand unmark = new NumCommand(this.uiManager, this.taskManager, command[1], Type.UNMARK);
                return unmark.execute();
            case "delete":
                NumCommand delete = new NumCommand(this.uiManager, this.taskManager, command[1], Type.DELETE);
                return delete.execute();
            case "save":
                SaveCommand save = new SaveCommand(this.uiManager, this.taskManager);
                return save.execute();
            case "find":
                FindCommand find = new FindCommand(this.uiManager, this.taskManager, command[1]);
                return find.execute();
            case "update":
                UpdateCommand update = new UpdateCommand(this.uiManager, this.taskManager, command[1], Type.UPDATE);
                return update.execute();
            default:
                throw new DukeException();
            }
        } catch (InvalidOperationException | DateException | TaskIndexException | DukeException e) {
            return uiManager.showErrorMessage(e.toString());
        } catch (NumberFormatException e) {
            return uiManager.showErrorMessage("Give me the task number in numbers please!");
        } catch (IndexOutOfBoundsException e) {
            return uiManager.showErrorMessage("I don't think we have that task!\nUse 'list' to check");
        } catch (DateTimeParseException e) {
            return uiManager.showErrorMessage("Invalid date! Please use the format 'YYYY-MM-DD'");
        } catch (IOException e) {
            return uiManager.showErrorMessage("Oops! There was a corrupted task in the previous list!");
        }
    }
}

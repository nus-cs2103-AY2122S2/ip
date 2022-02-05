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
    private final ListStorage listStorage;
    private final ListLoader listLoader;
    private boolean isOpen;


    /**
     * Constructs the CommandManager Object.
     */
    public CommandManager() {
        this.uiManager = new UiManager();
        this.taskManager = new TaskManager(this.uiManager);
        this.isOpen = true;
        this.listStorage = new ListStorage(this.taskManager);
        this.listLoader = new ListLoader(this.taskManager);
    }

    /**
     * Sets the boolean isOpen to false.
     */
    public void setClose() {
        this.isOpen = false;
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
                this.setClose();
                listStorage.saveList();
                return uiManager.exit();
            case "list":
                return uiManager.printList(taskManager);
            case "todo":
                AddTaskCommand todo = new AddTaskCommand(this.uiManager, this.taskManager, command[1], Type.TODO);
                String todoTask = todo.execute();
                listStorage.saveList();
                return todoTask;
            case "deadline":
                AddTaskCommand deadline =
                        new AddTaskCommand(this.uiManager, this.taskManager, command[1], Type.DEADLINE);
                String deadlineTask = deadline.execute();
                listStorage.saveList();
                return deadlineTask;
            case "event":
                AddTaskCommand event = new AddTaskCommand(this.uiManager, this.taskManager, command[1], Type.EVENT);
                String eventTask = event.execute();
                listStorage.saveList();
                return eventTask;
            case "mark":
                NumCommand mark = new NumCommand(this.uiManager, this.taskManager, command[1], Type.MARK);
                String markTask = mark.execute();
                listStorage.saveList();
                return markTask;
            case "unmark":
                NumCommand unmark = new NumCommand(this.uiManager, this.taskManager, command[1], Type.UNMARK);
                String unmarkTask = unmark.execute();
                listStorage.saveList();
                return unmarkTask;
            case "delete":
                NumCommand delete = new NumCommand(this.uiManager, this.taskManager, command[1], Type.DELETE);
                String deleteTask = delete.execute();
                listStorage.saveList();
                return deleteTask;
            case "save":
                SaveCommand save = new SaveCommand(this.uiManager, this.taskManager);
                String saveTask = save.execute();
                listStorage.saveList();
                return saveTask;
            case "find":
                FindCommand find = new FindCommand(this.uiManager, this.taskManager, command[1]);
                return find.execute();
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

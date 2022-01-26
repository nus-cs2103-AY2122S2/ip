package duke.commands;

import duke.exceptions.InvalidOperationException;
import duke.tasks.TaskManager;
import duke.exceptions.DateException;
import duke.exceptions.DukeException;
import duke.exceptions.TaskIndexException;
import duke.ui.TextLoader;
import duke.ui.TextStorage;
import duke.ui.UiManager;

import java.io.FileNotFoundException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * CommandManager Object that handles initialisation,
 * receiving and dispatching of commands.
 */
public class CommandManager {
    private final TaskManager tm;
    private final UiManager um;
    private final TextStorage storage;
    private final TextLoader loader;
    private boolean isOpen;


    /**
     * Constructs the CommandManager Object.
     */
    public CommandManager() {
        this.um = new UiManager();
        this.tm = new TaskManager(this.um);
        this.isOpen = true;
        this.storage = new TextStorage(this.um);
        this.loader = new TextLoader(this.um, this.tm, this.storage);
    }

    /**
     * Sets the boolean isOpen to false.
     */
    public void close() {
        this.isOpen = false;
    }

    /**
     * Initialises and runs the program.
     * Scans String input from console and issues
     * commands to different objects via switching.
     * Checks for and handles errors.
     */
    public void run() {
        um.welcome();
        try {
            this.loader.load();
        } catch (FileNotFoundException e) {
            um.printLoadFail();
        }catch (TaskIndexException e) {
            um.errorMessage("Oops! There was a corrupted task in the previous list!");
        }
        Scanner sc = new Scanner(System.in);
        String s = "";
        do {
            s = sc.nextLine();
            try {
               String[] command = um.parseCommand(s);
                switch (command[0]) {
                    case "bye":
                        this.close();
                        storage.save();
                        um.exit();
                        break;
                    case "list":
                        um.printList(tm);
                        break;
                    case "todo":
                        AddTaskCommand todo = new AddTaskCommand(this.um, this.tm, command[1], Type.TODO);
                        todo.execute();
                        storage.append(command[1], Type.TODO);
                        break;
                    case "deadline":
                        AddTaskCommand deadline = new AddTaskCommand(this.um, this.tm, command[1], Type.DEADLINE);
                        deadline.execute();
                        storage.append(command[1], Type.DEADLINE);
                        break;
                    case "event":
                        AddTaskCommand event = new AddTaskCommand(this.um, this.tm, command[1], Type.EVENT);
                        event.execute();
                        storage.append(command[1], Type.EVENT);
                        break;
                    case "mark":
                        NumCommand mark = new NumCommand(this.um, this.tm, command[1], Type.MARK);
                        mark.execute();
                        storage.append(command[1], Type.MARK);
                        break;
                    case "unmark":
                        NumCommand unmark = new NumCommand(this.um, this.tm, command[1], Type.UNMARK);
                        unmark.execute();
                        storage.append(command[1], Type.UNMARK);
                        break;
                    case "delete":
                        NumCommand delete = new NumCommand(this.um, this.tm, command[1], Type.DELETE);
                        delete.execute();
                        storage.append(command[1], Type.DELETE);
                        break;
                    case "save":
                        SaveCommand save = new SaveCommand(this.um, this.tm, Type.SAVE);
                        save.execute();
                        break;
                    default:
                        throw new DukeException();
                }
            } catch (InvalidOperationException | DateException | TaskIndexException | DukeException e) {
                um.errorMessage(e.toString());
            } catch (NumberFormatException e) {
                um.errorMessage("Give me the task number in numbers please!");
            } catch (IndexOutOfBoundsException e) {
                um.errorMessage("I don't think we have that task!\nUse 'list' to check");
            } catch (DateTimeParseException e) {
                um.errorMessage("Invalid date! Please use the format 'YYYY-MM-DD'");
            }
        } while (this.isOpen);
        sc.close();
    }
}

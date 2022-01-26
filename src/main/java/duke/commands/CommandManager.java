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

public class CommandManager {
    private final TaskManager taskManager;
    private final UiManager uiManager;
    private final TextStorage storage;
    private final TextLoader loader;
    private boolean isOpen;

    public CommandManager() {
        this.uiManager = new UiManager();
        this.taskManager = new TaskManager(this.uiManager);
        this.isOpen = true;
        this.storage = new TextStorage(this.uiManager);
        this.loader = new TextLoader(this.uiManager, this.taskManager, this.storage);
    }

    public void setClose() {
        this.isOpen = false;
    }

    public void run() {
        uiManager.welcome();
        try {
            this.loader.load();
        } catch (FileNotFoundException e) {
            uiManager.printLoadFail();
        }catch (TaskIndexException e) {
            uiManager.showErrorMessage("Oops! There was a corrupted task in the previous list!");
        }
        Scanner sc = new Scanner(System.in);
        String s = "";
        do {
            s = sc.nextLine();
            try {
               String[] command = uiManager.parseCommand(s);
                switch (command[0]) {
                case "bye":
                    this.setClose();
                    storage.save();
                    uiManager.exit();
                    break;
                case "list":
                    uiManager.printList(taskManager);
                    break;
                case "todo":
                    AddTaskCommand todo = new AddTaskCommand(this.uiManager, this.taskManager, command[1], Type.TODO);
                    todo.execute();
                    storage.append(command[1], Type.TODO);
                    break;
                case "deadline":
                    AddTaskCommand deadline =
                            new AddTaskCommand(this.uiManager, this.taskManager, command[1], Type.DEADLINE);
                    deadline.execute();
                    storage.append(command[1], Type.DEADLINE);
                    break;
                case "event":
                    AddTaskCommand event = new AddTaskCommand(this.uiManager, this.taskManager, command[1], Type.EVENT);
                    event.execute();
                    storage.append(command[1], Type.EVENT);
                    break;
                case "mark":
                    NumCommand mark = new NumCommand(this.uiManager, this.taskManager, command[1], Type.MARK);
                    mark.execute();
                    storage.append(command[1], Type.MARK);
                    break;
                case "unmark":
                    NumCommand unmark = new NumCommand(this.uiManager, this.taskManager, command[1], Type.UNMARK);
                    unmark.execute();
                    storage.append(command[1], Type.UNMARK);
                    break;
                case "delete":
                    NumCommand delete = new NumCommand(this.uiManager, this.taskManager, command[1], Type.DELETE);
                    delete.execute();
                    storage.append(command[1], Type.DELETE);
                    break;
                case "save":
                    SaveCommand save = new SaveCommand(this.uiManager, this.taskManager, Type.SAVE);
                    save.execute();
                    break;
                default:
                    throw new DukeException();
                }
            } catch (InvalidOperationException | DateException | TaskIndexException | DukeException e) {
                uiManager.showErrorMessage(e.toString());
            } catch (NumberFormatException e) {
                uiManager.showErrorMessage("Give me the task number in numbers please!");
            } catch (IndexOutOfBoundsException e) {
                uiManager.showErrorMessage("I don't think we have that task!\nUse 'list' to check");
            } catch (DateTimeParseException e) {
                uiManager.showErrorMessage("Invalid date! Please use the format 'YYYY-MM-DD'");
            }
        } while (this.isOpen);
        sc.close();
    }
}

package mnsky;

import java.util.ArrayList;

import mnsky.exceptions.MnskyException;
import mnsky.task.Deadline;
import mnsky.task.Event;
import mnsky.task.Task;

public class Mnsky {
    private static final String[] writeCommands = {"mark", "unmark", "todo", "event", "deadline", "delete"};

    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    /**
     * Constructor for the Mnsky object.
     */
    public Mnsky() {
        ui = new Ui();
        storage = new Storage("data/MnskyData.txt");
        taskList = new TaskList(ui, storage);
        ui.printGreeting();
    }

    private boolean isWriteCommand(String searchedCommand) {
        for (String command : writeCommands) {
            if (command.equals(searchedCommand)) {
                return true;
            }
        }

        return false;
    }

    public void run() {
        boolean isRunning = true;

        while (isRunning) {
            String input = ui.getInput();
            ArrayList<String> parsedInput = Parser.parseInput(input);

            try {
                switch (parsedInput.get(0)) {
                case "bye":
                    isRunning = false;
                    break;
                case "list":
                    ui.printList(taskList);
                    break;
                case "mark":
                    Task markedTask = taskList.mark(parsedInput.get(1));
                    ui.printTask(markedTask);
                    break;
                case "unmark":
                    Task unmarkedTask = taskList.unmark(parsedInput.get(1));
                    ui.printTask(unmarkedTask);
                    break;
                case "task":
                    Task task = taskList.addTask(parsedInput.get(1));
                    ui.printAddedTask(task);
                    break;
                case "event":
                    Event event = taskList.addEvent(parsedInput.get(1), parsedInput.get(2));
                    ui.printAddedTask(event);
                    break;
                case "deadline":
                    Deadline deadline = taskList.addDeadline(parsedInput.get(1), parsedInput.get(2));
                    ui.printAddedTask(deadline);
                    break;
                case "delete":
                    Task deleted = taskList.delete(parsedInput.get(1));
                    ui.printDeletedTask(deleted);
                    break;
                default:
                    ui.printMnsky("...");
                }
            } catch (MnskyException e) {
                ui.printException(e);
            }

            if (isWriteCommand(parsedInput.get(0))) {
                storage.writeToDataFile(taskList);
            }

            ui.printMnsky("I can help!");
        }

        ui.printBye();
    }

    public static void main(String[] args) {
        Mnsky mnsky = new Mnsky();
        mnsky.run();
    }
}

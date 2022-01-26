package mnsky;

import mnsky.exceptions.MnskyException;
import mnsky.task.*;

import java.util.ArrayList;

public class Mnsky {
    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    /**
     * Creates a UI object.
     */
    public Mnsky() {
        this.ui = new Ui();
        this.storage = new Storage("data/MnskyData.txt");
        this.taskList = new TaskList(this.ui, this.storage);
        this.ui.greeting();
    }

    /**
     * Returns whether or not command passed in the argument is a command that writes to the task list.
     * @param searchedCommand The command to be checked.
     * @return True if the command writes to the task list, or false otherwise.
     */
    private boolean isWriteCommand(String searchedCommand) {
        String[] writeCommands = {"mark", "unmark", "todo", "event", "deadline", "delete"};

        for (String command : writeCommands) {
            if (command.equals(searchedCommand)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Runs the main loop of Mnsky, including retrieving, parsing, and processing user input.
     * @return False is the user input the command bye, or true otherwise.
     */
    public boolean run() {
        String input = this.ui.getInput();
        ArrayList<String> parsedInput = Parser.parseInput(input);

        try {
            switch (parsedInput.get(0)) {
            case "bye":
                this.ui.bye();
                return false;

            case "list":
                this.ui.printList(this.taskList);
                break;

            case "mark":
                Task markedTask = taskList.mark(parsedInput.get(1));
                this.ui.printTask(markedTask);
                break;

            case "unmark":
                Task unmarkedTask = taskList.unmark(parsedInput.get(1));
                this.ui.printTask(unmarkedTask);
                break;

            case "task":
                Task task = taskList.addTask(parsedInput.get(1));
                this.ui.printAddedTask(task);
                break;

            case "event":
                Event event = taskList.addEvent(parsedInput.get(1), parsedInput.get(2));
                this.ui.printAddedTask(event);
                break;

            case "deadline":
                Deadline deadline = taskList.addDeadline(parsedInput.get(1), parsedInput.get(2));
                this.ui.printAddedTask(deadline);
                break;

            case "delete":
                Task deleted = taskList.delete(parsedInput.get(1));
                this.ui.printDeletedTask(deleted);
                break;

            default:
                this.ui.printMnsky("...");
            }
        } catch (MnskyException e) {
            this.ui.printException(e);
        }

        if (this.isWriteCommand(parsedInput.get(0))) {
            this.storage.writeToDataFile(this.taskList);
        }

        this.ui.printMnsky("I can help!");
        return true;
    }

    public static void main(String[] args) {
        Mnsky mnsky = new Mnsky();

        while (mnsky.run()) {}
    }
}

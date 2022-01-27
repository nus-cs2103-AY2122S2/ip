package mnsky;

import mnsky.exceptions.MnskyException;
import mnsky.task.*;

import java.util.ArrayList;

public class Mnsky {
    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    /**
     * Constructor for the Mnsky object.
     */
    public Mnsky() {
        this.ui = new Ui();
        this.storage = new Storage("data/MnskyData.txt");
        this.taskList = new TaskList(this.ui, this.storage);
        this.ui.greeting();
    }

    private boolean isWriteCommand(String searchedCommand) {
        String[] writeCommands = {"mark", "unmark", "todo", "event", "deadline", "delete"};

        for (String command : writeCommands) {
            if (command.equals(searchedCommand)) {
                return true;
            }
        }

        return false;
    }

    public boolean run() {
        String input = this.ui.getInput();

        try {
            ArrayList<String> parsedInput = Parser.parseInput(input);

            switch (parsedInput.get(0)) {
            case "bye":
                this.ui.bye();
                return false;

            case "list":
                this.ui.printListStrings(this.taskList.getListString());
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

            case "find":
                this.ui.printListStrings(taskList.find(parsedInput.get(1)));
                break;

            default:
                this.ui.printMnsky("...");
            }

            if (this.isWriteCommand(parsedInput.get(0))) {
                this.storage.writeToDataFile(this.taskList);
            }
        } catch (MnskyException e) {
            this.ui.printException(e);
        }

        this.ui.printMnsky("I can help!");
        return true;
    }

    public static void main(String[] args) {
        Mnsky mnsky = new Mnsky();

        while (mnsky.run()) {}
    }
}

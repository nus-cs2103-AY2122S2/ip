import java.time.format.DateTimeParseException;
import java.time.LocalDate;
import java.time.LocalTime;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

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

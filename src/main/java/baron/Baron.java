package baron;

import baron.commands.Command;
import baron.commands.CommandManager;
import baron.exceptions.BaronException;
import baron.printer.Printer;
import baron.tasks.TaskManager;
import baron.util.Storage;

import java.util.Scanner;

public class Baron {
    private final Scanner inputScanner;
    private final TaskManager taskManager;
    private final CommandManager commandManager;
    private final Storage storage;

    public Baron(String relativeFilePath) {
        TaskManager taskManagerTemp;
        this.inputScanner = new Scanner(System.in);
        this.storage = new Storage(relativeFilePath);
        try {
            taskManagerTemp = new TaskManager(this.storage.load());
        } catch (BaronException e) {
            Printer.printCommandOutput(e.toString());
            taskManagerTemp = new TaskManager();
        }
        this.taskManager = taskManagerTemp;
        this.commandManager = new CommandManager(this.taskManager, this.storage);
    }

    private void start() {
        Printer.printWelcomeMessage();

        Command command;

        do {
            String fullCommand = inputScanner.nextLine();

            command = commandManager.parseCommand(fullCommand);
            Printer.printCommandOutput(command.execute());
        }
        while (!command.isByeCommand());
        try {
            this.storage.save(this.taskManager.getAllTasks());
        } catch (BaronException e) {
            Printer.printCommandOutput(e.toString());
        }
    }

    public static void main(String[] args) {
        Baron baron = new Baron("data/baron.txt");
        baron.start();
    }
}

package baron;

import baron.commands.Command;
import baron.commands.CommandManager;
import baron.exceptions.BaronException;
import baron.printer.Printer;
import baron.tasks.TaskManager;
import baron.util.StorageManager;

import java.util.Scanner;

public class Baron {
    Scanner inputScanner;
    TaskManager taskManager;
    CommandManager commandManager;
    StorageManager storageManager;

    public Baron(String relativeFilePath) {
        this.inputScanner = new Scanner(System.in);
        this.storageManager = new StorageManager(relativeFilePath);
        try {
            this.taskManager = new TaskManager(this.storageManager.load());
        } catch (BaronException e) {
            Printer.printCommandOutput(e.toString());
            this.taskManager = new TaskManager();
        }
        this.commandManager = new CommandManager(this.taskManager, this.storageManager);
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
            this.storageManager.save(this.taskManager.getAllTasks());
        } catch (BaronException e) {
            Printer.printCommandOutput(e.toString());
        }
    }

    public static void main(String[] args) {
        Baron baron = new Baron("data/baron.txt");
        baron.start();
    }
}

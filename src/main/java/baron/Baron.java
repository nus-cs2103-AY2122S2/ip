package baron;

import baron.commands.Command;
import baron.commands.CommandManager;
import baron.printer.Printer;
import baron.tasks.TaskManager;

import java.util.Scanner;

public class Baron {
    Scanner inputScanner;
    TaskManager taskManager;
    CommandManager commandManager;

    public Baron() {
        this.inputScanner = new Scanner(System.in);
        this.taskManager = new TaskManager();
        this.commandManager = new CommandManager(this.taskManager);
    }

    private void start() {
        Printer.printWelcomeMessage();

        Command command = null;

        do {
            String fullCommand = inputScanner.nextLine();

            command = commandManager.parseCommand(fullCommand);
            Printer.printCommandOutput(command.execute());
        }
        while (!command.isByeCommand());
    }

    public static void main(String[] args) {
        Baron baron = new Baron();
        baron.start();
    }
}

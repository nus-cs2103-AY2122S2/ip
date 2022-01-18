package baron;

import baron.commands.Command;
import baron.commands.CommandParser;
import baron.printer.Printer;

import java.util.Scanner;

public class Baron {
    Scanner inputScanner;

    public Baron() {
        inputScanner = new Scanner(System.in);
    }

    private void start() {
        Printer.printWelcomeMessage();

        Command command = null;

        do {
            String fullCommand = inputScanner.nextLine();

            command = CommandParser.parseCommand(fullCommand);
            Printer.printCommandOutput(command.execute());
        }
        while (!command.isByeCommand());
    }

    public static void main(String[] args) {
        Baron baron = new Baron();
        baron.start();
    }
}

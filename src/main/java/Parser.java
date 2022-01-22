import command.*;
import ui.Ui;

public class Parser {
    public static Command parse(String input) {
        String[] commandSeperated = input.split(" ", 2);
        if (input.strip().equals("list")) {
            return new ListCommand();
        }
        switch (commandSeperated[0]) {
            case "todo":
                return new TodoCommand(commandSeperated[1]);
            case "event":
                String[] messageSeperated = commandSeperated[1].split("/at ", 2);
                return new EventCommand(messageSeperated[0], messageSeperated[1]);
            case "deadline":
                messageSeperated = commandSeperated[1].split("/by ", 2);
                return new DeadlineCommand(messageSeperated[0], messageSeperated[1]);
            case "mark": //unmark, delete
                int serialNumber = Integer.parseInt(commandSeperated[1]);
                return new MarkCommand(serialNumber);
            case "unmark": //unmark, delete
                serialNumber = Integer.parseInt(commandSeperated[1]);
                return new UnmarkCommand(serialNumber);
            case "delete":
                serialNumber = Integer.parseInt(commandSeperated[1]);
                return new DeleteCommand(serialNumber);
            default:
                Ui.outputError(" I'm sorry, but I don't know what that means :-(");
                return new ListCommand(); //CHANGE THIS IN THE FUTURE
        }
    }
}

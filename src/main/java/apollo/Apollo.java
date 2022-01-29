package apollo;

import apollo.commands.Command;
import apollo.commands.ExitCommand;
import apollo.exceptions.ApolloException;
import apollo.exceptions.ApolloIoException;
import apollo.parser.Parser;
import apollo.tasks.TaskList;
import apollo.ui.Welcome;
import apollo.ui.Ui;

public class Apollo {

    private static TaskList taskList;
    private static Ui ui;

    private static void initialise() {

        ui = new Ui();
        boolean isLoaded;
        try {
            taskList = Storage.load();
            isLoaded = true;
        } catch (ApolloIoException e) {
            taskList = new TaskList();
            isLoaded = false;
        }
        Command.setTaskList(taskList);
        ui.printMessage(Welcome.welcomeMessage(isLoaded));
    }

    private static void run() {

        Command command = null;
        do {
            String userCommand = ui.getUserCommand();
            try {
                command = new Parser().parseCommand(userCommand);
                ui.printMessage(command.execute());
                Storage.save(taskList);
            } catch (ApolloException e) {
                ui.printMessage(e.getMessage());
            }
        }
        while (!ExitCommand.isExit(command));
    }

    public static void main(String[] args) {
        initialise();
        run();
    }
}

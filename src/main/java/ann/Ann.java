package ann;

import java.util.ArrayList;

import ann.commands.Command;
import ann.commands.ExitCommand;
import ann.data.tasks.Task;
import ann.data.TaskList;
import ann.parser.Parser;
import ann.storage.Storage;
import ann.ui.Ui;

public class Ann {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private ArrayList<Task> list;
    private int numItems;

    public Ann() {
    }

    private void run(String[] args) {
        start(args);
        runCommandUntilExitCommand();
        exit();
    }

    public void start(String[] args) {
        try {
            this.ui = new Ui();
            this.storage = initialiseStorage(args);
            this.tasks = new TaskList(storage.load());
            ui.showGreeting();
        } catch (Exception e) {
            ui.showFailedInitMessage();
            throw new RuntimeException(e);
        }
    }

    private Storage initialiseStorage(String[] args) throws Storage.StorageOperationException {
        return args.length != 2 ? new Storage(args[0], args[1]) : new Storage();
    }

    private void runCommandUntilExitCommand() {
        Command command;
        do {
            command = Parser.parse(ui.getCommand());
            String commandMessage = executeCommand(command);
            ui.showToUser(commandMessage);
        } while(!ExitCommand.isExit(command));
    }

    private String executeCommand(Command command) {
        try {
            command.setTaskList(tasks);
            command.executeCommand();
            storage.save(tasks);
            return command.getMessage();
        } catch (Storage.StorageOperationException e) {
            ui.showToUser(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private void exit() {
        ui.showExitMessage();
        System.exit(0);
    }

    public static void main(String[] args) {
        new Ann().run(args);
    }
}
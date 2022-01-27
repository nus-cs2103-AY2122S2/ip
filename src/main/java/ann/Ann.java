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

    public Ann(String folderName, String fileName) {
        try {
            this.ui = new Ui();
            this.storage = new Storage(folderName, fileName);
            this.tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showInitFailedMessage();
            throw new RuntimeException(e);
        }
    }

    private void run() {
        ui.showGreeting();
        runCommandUntilExitCommand();
        exit();
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
        new Ann("data", "ann.txt").run();
    }
}
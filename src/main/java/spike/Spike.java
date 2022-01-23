package spike;

import spike.command.Command;
import spike.command.ExitCommand;
import spike.parser.Parser;
import spike.storage.Storage;
import spike.task.*;
import spike.ui.Ui;

import java.util.Scanner;

public class Spike {
    private Storage storage;
    private Ui ui;
    private TaskList tasks;

    public Spike(String directory, String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(directory, filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (SpikeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        Command command;
        do {
            String input = ui.getCommand();
            command = new Parser().parseCommand(input, tasks);
            String executionResult = command.execute(tasks);
            ui.printMsg(executionResult);
        } while (!(command instanceof ExitCommand));
        storage.saveChanges(tasks);
    }


    public static void main(String[] args) {
        new Spike("data/", "data/Spike.txt").run();
        return;
    }

}

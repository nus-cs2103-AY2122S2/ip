package bobby;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import bobby.command.ByeCommand;
import bobby.command.Command;
import bobby.exception.BobbyException;
import bobby.task.TaskList;

public class Bobby {
    private static final String FILE_LOCATION = "./Bobby.txt";
    private static Ui ui;
    private static Storage storage;
    private static TaskList tasks;

    /**
     * Constructor for Bobby
     */
    public Bobby() {
        ui = new Ui(new Scanner(System.in));
        try {
            storage = new Storage(new File(FILE_LOCATION));
            tasks = new TaskList(storage.loadTasks());
        } catch (BobbyException | FileNotFoundException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Driver method for Bobby
     */
    public void run() {
        ui.printLogo();
        ui.printGreeting();
        while (true) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                if (c instanceof ByeCommand) {
                    break;
                }
            } catch (BobbyException e) {
                System.out.println(e.getMessage());
            } finally {
                ui.printLongLine(2);
            }
        }
    }

    /**
     * Main method of Bobby
     *
     * @param args User inputs
     */
    public static void main(String[] args) {
        new Bobby().run();
    }

    public static String getResponse(String input) {
        String replyMessage;
        try {
            Command c = Parser.parse(input);
            replyMessage = c.execute(tasks, ui, storage);
        } catch (BobbyException e) {
            replyMessage = e.toString();
        }
        return replyMessage;
    }
}

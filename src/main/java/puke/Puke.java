package puke;

import java.util.Scanner;

import puke.exception.PukeException;
import puke.io.Storage;
import puke.parser.Parser;
import puke.task.TaskList;
import puke.ui.Ui;

/**
 * Main class for Puke.
 */
public class Puke {
    private static Ui ui;
    private static TaskList tasks;
    private static Storage storage;
    private static Parser parser;

    /**
     * Initialises Puke and the objects required.
     *
     * @param filePath Path of storage file.
     */
    public Puke(String filePath) {
        ui = new Ui();
        tasks = new TaskList();
        storage = new Storage(filePath);
        parser = new Parser();

        try {
            storage.loadTasks(tasks);
        } catch (PukeException e) {
            ui.printError(e);
        }
    }

    /**
     * Reads inputs from user.
     */
    public void run() {
        Scanner sc = new Scanner(System.in);
        ui.printWelcomeMessage();

        String response = "";
        while (true) {
            ui.printCommandHead();

            try {
                response = parser.processInput(sc.nextLine(), tasks);

                if (response == null) {
                    storage.saveTasks(tasks);
                    ui.printExit();
                    break;
                }
            } catch (PukeException e) {
                ui.printError(e);
                continue;
            }

            ui.printResponse(response);
        }
    }
}

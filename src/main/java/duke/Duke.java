package duke;

import java.io.IOException;

import java.util.Scanner;

import duke.command.Command;

import duke.component.ExceptionHandler;
import duke.component.Parser;
import duke.component.Storage;
import duke.component.TaskList;
import duke.component.Ui;

import duke.exception.DukeException;

public class Duke {
    private static Ui ui = new Ui();
    private static ExceptionHandler exceptionHandler = new ExceptionHandler();
    private static Storage storage = new Storage();
    private static Parser parser = new Parser();
    private static TaskList tasks = new TaskList();

    /**
     * Class constructor
     * Initialize tasks by reading data file
     */
    public Duke() {
        try {
            tasks.setTasks(storage.readDataFromFile());
        } catch (IOException e) {
            exceptionHandler.handleOtherException(e);
        }
    }

    /**
     * Reads and executes commands from user inputs.
     */
    public void run() {
        ui.printGreeting();
        Scanner sc = new Scanner(System.in);
        while (true) {
            String userInput = ui.readCommand(sc);
            try {
                Command command = parser.parse(userInput);
                command.execute(tasks, ui, storage);
            } catch (DukeException e) {
                exceptionHandler.handleDukeException(e);
            } catch (Exception e) {
                exceptionHandler.handleOtherException(e);
            }
        }
    }

    /**
     * Main function to run Duke
     * @param args Unused
     */
    public static void main(String[] args) {
        new Duke().run();
    }

}

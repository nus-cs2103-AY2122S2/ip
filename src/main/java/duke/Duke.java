package duke;

import java.io.IOException;

/**
 * Duke class
 */
public class Duke {
    public static final String EXIT_PROGRAM = "Exit program";
    private Storage storage;
    private TaskList taskList;
    private Ui ui;


    /**
     * constructor for Duke
     *
     * @param filePath path to source file
     */
    public Duke(String filePath) throws IOException {
        ui = new Ui();
        storage = new Storage(filePath);
        taskList = new TaskList(storage.load());
    }

    /**
     * method to run the program
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (ExceptionHandler | IOException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * main method
     */
    public static void main(String[] args) throws IOException {
        new Duke("duke.txt").run();
    }
    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        if (input == null) {
            return null;
        }
        try {
            Command c = Parser.parse(input);
            c.execute(taskList, ui, storage);
            if (c.isExit()) {
                return EXIT_PROGRAM;
            } else {
                String response = ui.getMessage();
                assert response != null;
                return response;
            }
        } catch (ExceptionHandler | IOException e) {
            return e.getMessage();
        }
    }
}

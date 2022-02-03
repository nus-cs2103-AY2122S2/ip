package duke;

import duke.dukeexceptions.DukeException;
import duke.parser.Parser;
import duke.command.Command;

import java.io.IOException;
import java.util.Scanner;

public class Duke {
    /**
     * A task taking chatbot
     */
    private final Storage storage;
    private Ui ui;
    private TaskList taskList;
    public Duke(String filePath) {
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load());
        } catch (IOException e) {
            System.out.println("No path exist");
            taskList = new TaskList();
        }

    }

    /**
     * Main method to activate chatbot
     *
     * @throws IOException error when saving to the data file
     */
    public void run() throws IOException {
        ui = new Ui();
        boolean isExit = false;
        Scanner sc = new Scanner(System.in);
        while (!isExit) {
            try {
                String input = sc.nextLine();
                ui.showLine();
                Command c = Parser.parseCommand(input);
                c.execute(taskList, ui,storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showEndline();
            }


        }

    }

    public static void main(String[] args) throws DukeException {
        try {
            new Duke("data/tasks.txt").run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

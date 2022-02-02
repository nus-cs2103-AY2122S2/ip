package duke;

import java.io.IOException;
import java.util.Scanner;

import duke.commands.Command;
import duke.exceptions.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;


/**
 * duke.Main class which runs the program
 */
public class Duke {
    private TaskList tasks;
    private Storage storage;
    private Ui ui;

    /**
     * Creates a duke.Duke object with tasks loaded
     * @param filePath
     */
    public Duke(String filePath) {
        storage = new Storage(filePath);
        ui = new Ui();

        try {
            tasks = storage.load();
        } catch (IOException e) {
            System.out.println("An error occurred");
            e.printStackTrace();
        }
    }

    /**
     * duke.Main program loop that is run in main
     */
    public void run() {
        //intro messages
        ui.welcomeMessage();
        Scanner sc = new Scanner(System.in);
        Command cmd;
        //main program loop
        while (true) {
            try {
                String input = sc.nextLine();
                ui.printLine();
                cmd = Parser.processInput(input);
                cmd.execute(tasks, ui, storage);
                tasks = cmd.getList();
                if (cmd.endsProgram()) {
                    break;
                }
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.printLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

    public String getResponse(String input) {
        Command cmd;
        String output = " ";
        try {
            cmd = Parser.processInput(input);
            output = cmd.execute(tasks, ui, storage);
            tasks = cmd.getList();
        } catch (DukeException e) {
            output = e.getMessage();
        } finally {
            return output;
        }
    }
}

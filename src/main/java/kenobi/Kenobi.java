package kenobi;

import kenobi.command.Command;
import kenobi.command.ExitCommand;
import kenobi.parser.ParseException;
import kenobi.parser.Parser;
import kenobi.util.Storage;
import kenobi.util.TaskList;
import kenobi.util.Ui;

import java.util.Scanner;

/**
 * The Kenobi program implements a chatbot that manages a list of tasks.
 */
public class Kenobi {
    private TaskList tasks;
    private Storage storage;
    private Ui ui;

    /**
     * Constructs an instance of Kenobi with the specified save path for storage.
     *
     * @param savePath The path that directs to the storage that Kenobi uses.
     */
    public Kenobi(String savePath) {
        storage = new Storage(savePath);
        tasks = storage.load();
        ui = new Ui();
    }

    /**
     * Runs the main Kenobi program.
     * The program initializes user input and storage then greets the user.
     */
    public void run() {
        Scanner userInput = new Scanner(System.in);
        Command nextCmd;
        String response;

        ui.greet();

        while (true) {
            try {
                nextCmd = Parser.parse(userInput.nextLine());
                nextCmd.setData(tasks);
                response = nextCmd.execute();
                ui.say(response);

                if (nextCmd instanceof ExitCommand) {
                    storage.save(tasks);
                    break;
                }
            } catch (ParseException e) {
                ui.say(e.getMessage());
            }
        }

        userInput.close();
    }

    public static void main(String[] args) {
        new Kenobi("../../../data/tasks.txt").run();
    }
}

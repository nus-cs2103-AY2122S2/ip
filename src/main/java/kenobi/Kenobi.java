package kenobi;

import kenobi.command.Command;
import kenobi.command.ExitCommand;
import kenobi.parser.ParseException;
import kenobi.parser.Parser;
import kenobi.util.Storage;
import kenobi.util.TaskList;

/**
 * The Kenobi program implements a chatbot that manages a list of tasks.
 */
public class Kenobi {
    private final TaskList tasks;
    private final Storage storage;

    private String response;
    private Command cmd;

    /**
     * Constructs an instance of Kenobi with the specified save path for storage.
     *
     * @param savePath The path that directs to the storage that Kenobi uses.
     */
    public Kenobi(String savePath) {
        storage = new Storage(savePath);
        tasks = storage.load();
    }

    /**
     * Runs the main Kenobi program.
     * The program initializes user input and storage then greets the user.
     */
    public void giveCommand(String input) {
        try {
            cmd = Parser.parse(input);
            cmd.setData(tasks);
            response = cmd.execute();

            if (cmd instanceof ExitCommand) {
                storage.save(tasks);
            }
        } catch (ParseException e) {
            response = e.getMessage();
        }
    }

    public String getResponse() {
        return response;
    }

    public String echo(String input) {
        return "Kenobi heard: " + input;
    }
}


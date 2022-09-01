package funbox;

import java.io.IOException;

import funbox.util.Ui;
import funbox.util.Parser;
import funbox.util.Storage;
import funbox.util.TaskList;
import funbox.command.Command;
import funbox.exception.FunBoxExceptions;


/**
 * The FunBox class is used as the outer shell of FunBoxGear,
 * which contains the functionality of FunBox
 */
public class FunBox  {
    private final Parser parser = new Parser();
    private final Ui ui = new Ui();
    private final Storage storage = new Storage(ui, parser);
    private final TaskList taskList = storage.getTaskList();

    /**
     * Gets input from the user and return a Result.
     */
    public Result getResponse(String input) {
        String result;
        boolean isExit = false;
        assert input != null : "Input should not be null";
        try {
            Command task = parser.parseCommand(input);
            result = task.execute(taskList, ui, storage);
            isExit = task.isExit();
        } catch (FunBoxExceptions | IOException e) {
            result = e.getMessage();
        }

        return new Result(result, isExit);
    }
}

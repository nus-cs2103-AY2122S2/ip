package duke.modules;


/**
 * Duke is a chat bot that keeps track of tasks for you.
 */
public class Duke {
    private TaskList taskList;
    private Parser parser;
    private Storage storage;
    private boolean isByeCommand = false;

    public Duke() {
        storage = new Storage();
        taskList = new TaskList(storage);
        parser = new Parser(taskList);

    }

    public static String getInitMessage() {
        return Ui.getInitializationMessage();
    }

    public String getResponse(String input) {
        String response = Ui.getResponse(input, parser);
        if (parser.byeCommandHasExecuted()) {
            Storage.save(taskList);
            isByeCommand = true;
        }
        return response;
    }

    public boolean isByeCommand() {
        return isByeCommand;
    }


}


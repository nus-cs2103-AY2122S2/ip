package duke.modules;


/**
 * Duke is a chat bot that keeps track of tasks for you.
 */
public class Duke {
    private TaskList taskList;
    private Parser parser;
    private Storage storage;


    public static void main(String[] args) {
        // load in the TaskList
        TaskList taskList = new TaskList(new Storage());
        Parser parser = new Parser(taskList);

        Storage.save(taskList);

    }

    public Duke() {
        storage = new Storage();
        taskList = new TaskList(storage);
        parser = new Parser(taskList);

    }



    public String getInitMessage() {
        return Ui.getInitializationMessage();
    }

    public String getResponse(String input) {
        String response = Ui.getResponse(input, parser);
        if (parser.byeCommandHasExecuted()) {
            Storage.save(taskList);
        }
        return response;
    }


}


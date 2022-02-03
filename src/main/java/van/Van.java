package van;

public class Van {
    private Storage storage;
    private Ui ui;
    private TaskList taskList;


    public Van() {
        storage = new Storage("storage.txt");
        ui = new Ui();
        taskList = new TaskList(Parser.parseArray(storage.getTasks()));
    }

    public String getResponse(String input) {
        Command nextCommand = Parser.parseCommand(input);
        nextCommand.executeCommand(ui, taskList, storage);
        return ui.printString();
    }
}
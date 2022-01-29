package van;

public class Van {
    public static void main(String[] args) {
        boolean isExit = false;
        Storage storage = new Storage("storage.txt");
        Ui ui = new Ui();
        TaskList taskList = new TaskList(Parser.parseArray(storage.getTasks()));
        while (!(isExit)) {
            Command nextCommand = Parser.parseCommand(ui.standBy());
            isExit = nextCommand.executeCommand(ui, taskList, storage);
        }
    }
}

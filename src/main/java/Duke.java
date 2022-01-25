public class Duke {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private Parser parser;

    public Duke (String filePath) {
        this.storage = new Storage(filePath);
        this.taskList = new TaskList(storage);
        this.ui = new Ui(taskList);
        this.parser = new Parser(taskList, ui);

        try {
            taskList.load();
        } catch (DukeException e) {
            ui.showErrorMessage(e.toString());
        }
    }

    public void run () {
        ui.startMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String input = ui.readUserInput();
                parser.parseInput(input);
                isExit = parser.commandIsExit();
            } catch (DukeException e) {
                ui.showErrorMessage(e.toString());
            }
        }
    }

    public static void main(String[] args) {
       new Duke("data/duke.txt").run();
    }

}

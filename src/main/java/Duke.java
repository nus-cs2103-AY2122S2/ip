
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    private Duke(String filepath) {
        this.ui = new Ui();
        this.storage = new Storage(filepath);
        this.tasks = new TaskList(storage.load());
        this.parser = new Parser();
    }

    public void run() {
        ui.showWelcome();
        boolean run = true;
        while(run) {
            System.out.print("Me   : ");
            String message = ui.readCommand();
            ui.showLine();
            Command command = parser.parseCommand(message);
            run = command.exec(tasks, ui, storage);
        }
    }

    public static void main(String[] args) {
        Duke chatBot = new Duke("data/data.txt");
        chatBot.run();
    }
}

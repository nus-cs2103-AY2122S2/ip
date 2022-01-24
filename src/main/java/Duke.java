import java.io.IOException;

public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        
        try {
            this.taskList = new TaskList(storage.loadFromFile());
        } catch (IOException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
    }

    public void run() {
        ui.hello();

        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Parser parser = new Parser(fullCommand);
                Command c = parser.parse();

                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.output(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke(Constants.FILE_PATH + Constants.FILE_NAME).run();
    }
}
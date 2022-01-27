import java.io.FileNotFoundException;
import java.io.IOException;



public class Duke {

    private Storage storage;
    private List tasks;
    private Ui ui;

    public Duke(String filePath) throws FileNotFoundException{
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new List(storage.load());
    }

    public void run() throws IOException {
        ui.greetUser();
        try {
            storage.printFileContent("data/duke.txt");
        } catch (IOException e) {
        }
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.processCommand();
                Command c = Parser.parseUserInput(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.getIsExit();
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    public static void main(String[] args) throws IOException {
        Duke duke = new Duke("data/duke.txt");
        duke.run();
    }
}

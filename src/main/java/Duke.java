import javax.sound.midi.SysexMessage;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    Duke(String filePath) {
        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage);
            ui = new Ui(tasks);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    public void run() {
        ui.run();
    }


    public static void main(String[] args) {
        new Duke("data.txt").run();
    }
}

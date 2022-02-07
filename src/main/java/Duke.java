import java.util.Scanner;

public class Duke {
    private final String name;
    private final String path;

    public Duke(String name, String path) {
        this.name = name;
        this.path = path;
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        TaskList taskList = new TaskList();
        Storage storage = new Storage(path);
        Ui ui = new Ui(name);
        Parser parser = new Parser(sc, taskList);

        ui.boot();
        try {
            storage.loadTo(taskList);
        } catch (DukeException e) {
            System.err.println(e.toString());
        }
        ui.start();
        parser.parse();
        storage.writeFrom(taskList);
    }

    public static void main(String[] args) {
        new Duke("Cindy's Duke Bot", "./duke.txt").run();
    }
}









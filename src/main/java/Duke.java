import java.io.File;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.util.*;
import java.io.FileWriter;
import java.io.IOException;


public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filepath) {
        ui = new Ui();
        storage = new Storage(filepath);
        tasks = new TaskList(storage.load());
    }

    public void run() {
        ui.printWelcome();
        ui.start(this.tasks);
        this.storage.save(this.tasks);
    }


    public static void main(String[] args) {
        new Duke("../../../data/duke.txt").run();
    }
}

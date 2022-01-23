import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    TaskList tasks;

    Duke(TaskList tasks) {
        this.tasks = tasks;
    }

    public void run() throws DukeException, IOException {

        Ui ui = new Ui(tasks);
        ui.run();

    }

    public static void main(String[] args) throws DukeException, IOException {
        Duke duke = new Duke(Storage.readFile());
        duke.run();
    }
}

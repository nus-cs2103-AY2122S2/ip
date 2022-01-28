package duke;

import java.util.Scanner;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String directoryPath, String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(directoryPath, filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public static void main(String[] args) {
        new Duke("./data","./data/duke.txt").run();
    }

    public void run() {
        Ui.greet();
        String input;
        Scanner sc = new Scanner(System.in);
        input = sc.next();
        Parser.parse(input, sc, tasks, storage);
        Ui.bye();
        sc.close();
    }
}


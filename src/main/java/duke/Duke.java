package duke;

import java.util.Scanner;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            System.out.println(e);
            tasks = new TaskList();
        }

    }

    public void run() {

        ui.start();

        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();
        while (!input.equals("bye")) {
            ui.nextInput(input, tasks);
            input = sc.nextLine();
        }

        sc.close();
        storage.save(tasks);
        ui.exit();

    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

}

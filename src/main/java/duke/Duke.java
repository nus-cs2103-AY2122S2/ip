package duke;

import duke.util.Parser;

import java.util.Scanner;

public class Duke {
    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    public Duke() {
        ui = new Ui();
        storage = new Storage();
        try {
            this.taskList = storage.load();
        } catch (Exception e) {
            ui.showLoadingError(e);
            taskList = new TaskList();
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        ui.greetings();
        while (true) {
            try {
                String output = Parser.parse(sc.nextLine(), taskList);
                if (output.equals("BYE")) break;
                ui.log(output);
                storage.updateStorage(taskList);
            } catch (Exception e) {
                ui.showLoadingError(e);
            }
        }
        ui.bye();
    }
}

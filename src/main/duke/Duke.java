package duke;

import duke.ui.Ui;
import duke.storage.Storage;
import duke.tasks.TaskList;
import java.util.Scanner;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke() {
        ui = new Ui();
        storage = new Storage();
    }

    public void run() {
        ui.showWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            try {

            } catch () {

            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ChatBot duke = new ChatBot("Duke", new Storage());
        duke.greet();
        String input;
        boolean quit = false;
        while(!quit) {
            try {
                quit = duke.runCommand(sc.nextLine());
            } catch (DukeException e) {
                duke.echo(e.getMessage());
            }
        }
        sc.close();
    }
}

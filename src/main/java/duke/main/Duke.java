package duke.main;

import duke.storage.Storage;
import duke.ui.Ui;
import duke.parser.Parser;
import java.util.Scanner;

public class Duke {

    private Ui ui;
    private Storage storage;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
    }

    private void run(){
        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();

        while(new Parser(userInput).processInput()) {
            userInput = sc.nextLine();
        }
        exit();
    }

    private void exit() {
        ui.exit();
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}

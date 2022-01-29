package duke;

import duke.commands.Command;
import duke.parser.DukeParser;
import duke.storage.Storage;
import duke.tasklist.DukeList;
import duke.ui.Ui;

import java.util.Scanner;

public class Duke {


    public static void main(String[] args) {
        Ui ui = new Ui();
        Storage storage = new Storage("./data.txt");
        ui.showWelcome();
        boolean isExit = false;
        DukeList list = new DukeList(storage);
        while (!isExit){
            String inp = ui.readInput();
            Command c = DukeParser.parseInput(inp);
            c.execute(ui,list);
            isExit = c.isExit();
        }
    }
}

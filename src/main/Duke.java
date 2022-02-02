package main;

import main.commands.Command;
import main.io.Parser;
import main.io.Storage;

import java.util.Scanner;

public class Duke {
    private static final String WELCOME_MESSAGE = "Hello! I'm Duke \n" + "What can I do for you";

    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private Parser parser;

    public Duke(String dirname, String filename) {
        this.storage = new Storage(dirname, filename);
        this.taskList = new TaskList();
        this.ui =  new Ui();
        this.parser = new Parser();
    }

    public void run() throws DukeException{
        Scanner sc = new Scanner(System.in);

        this.storage.readFile(this.taskList);

        System.out.println(WELCOME_MESSAGE);

        while (!Command.getIsExit()) {
            Command newCommand = this.parser.parse(sc.nextLine());
            newCommand.runCommand(this.ui, this.taskList);
        }

        storage.writeFile(this.taskList);
    }

    public static void main(String[] args) throws DukeException {
        new Duke("data", "duke.txt").run();
    }
}

package main;

import main.commands.Command;
import main.io.Parser;
import main.io.Storage;

import java.io.IOException;
import java.util.Scanner;

public class Duke {
    private static final String WELCOME_MESSAGE = "Hello! I'm Duke \n" + "What can I do for you";

    public static void main(String[] args) throws DukeException {
        Scanner sc = new Scanner(System.in);
        Parser parser = new Parser();
        Storage storage = new Storage("data", "duke.txt");

        storage.readFile();

        System.out.println(WELCOME_MESSAGE);

        while (!Command.getIsExit()) {
            Command newCommand = parser.parse(sc.nextLine());
            newCommand.runCommand();
        }

        storage.writeFile();
    }
}

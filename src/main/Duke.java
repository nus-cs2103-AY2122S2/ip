package main;

import main.commands.Command;
import main.io.Parser;

import java.util.Scanner;

public class Duke {
    private static final String WELCOME_MESSAGE = "Hello! I'm Duke \n" + "What can I do for you";

    public static void main(String[] args) throws DukeException {
        Scanner sc = new Scanner(System.in);
        Parser parser = new Parser();

        System.out.println(WELCOME_MESSAGE);

        while (!Command.getIsExit()) {
            Command newCommand = parser.parse(sc.nextLine());
            newCommand.runCommand();
        }
    }
}

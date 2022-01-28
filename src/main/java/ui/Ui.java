package ui;

import commands.Command;
import parser.Parser;

import java.util.Scanner;

public class Ui {
    private final static String line =
            "____________________________________________________________\n";
    private final Scanner s = new Scanner(System.in);

    /** Say welcome to the user!  */
    public static void welcome() {
        System.out.println(line);
        System.out.println("Hello! I am xpz\nWhat can I do for you?\n" + line);
    }

    /** Read in the user input and pass it to Parser to return a command.  */
    public Command read() {
        String input = s.nextLine();
        return Parser.parse(input);
    }

    /** Returns the response and add dividing lines to it to make it more fancy.  */
    public void respond(String respond) {
        respond = line + respond + "\n" + line;
        System.out.println(respond);
    }

}

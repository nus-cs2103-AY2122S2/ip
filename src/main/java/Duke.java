import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private CommandParser cmdParser;

    public Duke() {
        this.cmdParser = new CommandParser();
    }

    public static void main(String[] args) {
        Duke Lumu = new Duke();

        Lumu.botInitialize();
    }

    private String lineBreak() { return "\n----------------------------------------\n"; }

    private void botInitialize() {
        String logo = "LUMU";
//            = " _       _   _   __  __   _   _ \n"
//            + "| |     | | | | |  \\/  | | | | |\n"
//            + "| |     | | | | | |\\/| | | | | |\n"
//            + "| |___  | |_| | | |  | | | |_| |\n"
//            + "|_____|  \\___/  |_|  |_|  \\___/ \n";

        System.out.println(lineBreak() + "Hello I'm\n" + logo);
        System.out.println("What can I do for you?" + lineBreak());
        cmdParser.start();
    }

}

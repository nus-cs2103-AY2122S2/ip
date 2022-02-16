package kenobi.util;

import java.io.PrintWriter;
import java.util.Scanner;

/**
 * The Ui class encapsulates the user interface of Kenobi.
 * Kenobi mainly uses the standard output to give feedback to users.
 */
public class Ui {
    private PrintWriter pw;

    /**
     * Constructs a new Ui.
     */
    public Ui() {
        pw = new PrintWriter(System.out);
    }

    /**
     * Prints a formatted greeting to the user through standard output.
     */
    public void greet() {
        say("Hello there, I am\n" + " __   ___\n"
                + "/  | /  / _____  _    _  _____  _____    __\n"
                + "|  |/  / / ____// \\  / \\/  _  \\/  _  \\  \\__\\\n"
                + "|     /  | |___ |  \\ | || | | || |_| /_ |  |\n"
                + "|     \\  | ____|| | \\| || | | ||  ___  ||  |\n"
                + "|  |\\  \\ | |___ | |\\ | || |_| || |___| ||  |\n"
                + "\\__/ \\__\\\\_____\\\\_/ \\__/\\_____/\\_______/|__|\n"
                + "How may I serve you?");
    }

    /**
     * Prints a formatted response to the standard output.
     *
     * @param line The response string which will be printed
     */
    public void say(String line) {
        String prefix = "    ";
        Scanner sc = new Scanner(line);

        while (sc.hasNextLine()) {
            pw.print(prefix);
            pw.println(sc.nextLine());
        }

        sc.close();
        pw.flush();
    }
}

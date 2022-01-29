package apollo.ui;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Ui {

    private final Scanner in;
    private final PrintStream out;

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public Ui() {
        this(System.in, System.out);
    }

    public String getUserCommand() {
        return in.nextLine().trim();
    }

    public void printMessage(String message) {
        out.println("\t________________________________________________________________________\n\t "
                + message.replace("\n","\n\t ")
                + "\n\t________________________________________________________________________\n");
    }


}

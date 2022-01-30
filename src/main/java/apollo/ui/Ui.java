package apollo.ui;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static apollo.messages.Messages.LINE_FEED;

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
        String border = "\t________________________________________________________________________\n";
        String wrapMessage = border + "\t " + message.replace("\n", "\n\t ") + "\n" + border;
        out.print(wrapMessage.replace("\n", LINE_FEED));
    }


}

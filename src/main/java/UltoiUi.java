import java.io.InputStream;
import java.io.PrintStream;

import java.util.Scanner;

public class UltoiUi {
    public static final String INDENT = "    ";
    private static final String LINE_BREAK = "======U======L======T======O======I======";
    private static final String WELCOME_MESSAGE = "Hello! I am Ultoi [ uhl-twah ].\n"
            + "What can I do for you? <O_O>";
    private static final String LOADING_ERROR = "Ultoi fails to load your past tasks. <OoO>";


    private final Scanner in;
    private final PrintStream out;


    public UltoiUi() {
        this(System.in, System.out);
    }

    public UltoiUi(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public String readInput() {
        return in.nextLine();
    }

    public void showMsg(String msg) {
        String fullMsg = LINE_BREAK + "\n" + msg + "\n" + LINE_BREAK;
        this.out.println(fullMsg);
    }

    public void showWelcomeMsg() {
        showMsg(WELCOME_MESSAGE);
    }

    public void showLoadingError() {
        showMsg(LOADING_ERROR);
    }

    public void showError(UltoiException e) {
        showMsg(e.toString() + " " + UltoiException.EXCEPTION_FACE);
    }

}

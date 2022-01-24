package duke.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ui {
    private BufferedReader br;
    private static final String line = "    ――――――――――――――――――――――――――――――――――\n";;
    private static final String indent = "    ";

    private static final String logo = "     ____        _\n"
            + "    |  _ \\ _   _| | _____\n"
            + "    | | | | | | | |/ / _ \\\n"
            + "    | |_| | |_| |   <  __/\n"
            + "    |____/ \\__,_|_|\\_\\___|\n";

    private static final String intro = indent + "Hello! I'm Duke!\n" + indent + "What can I do for you?";

    public Ui() {
        this.br = new BufferedReader(new InputStreamReader(System.in));
    }

    public String readInput() throws IOException {
        return br.readLine();
    }

    public String showLine() {
        return line;
    }

    public String showIndent() {
        return indent;
    }

    public void showGreeting() {
        System.out.print(logo + "\n" + intro + "\n" + line);
    }

    public void showError(String message) {
        System.out.print(line + indent + message + "\n" + line);
    }

    public void printOutput(String message) {
        System.out.print(line + indent + message + "\n" + line);
    }
}

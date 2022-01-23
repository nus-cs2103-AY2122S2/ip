package duke.ui;

import java.io.PrintStream;
import java.util.Scanner;

public class Ui {
    private final String LINE = "\t____________________________________________________________";
    private Scanner in;
    private PrintStream out;

    public Ui() {
        this.in = new Scanner(System.in);
        this.out = System.out;
    }

    public void showStartUpMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        this.out.println("Hello I'm\n" + logo + "\n" + "What can I do for you?\n" + LINE);
    }

    public void showFeedbackMessage(String mes) {
        if(mes == null) {
            return;
        }
        this.out.println(LINE + mes + LINE);
    }
    public String readCommand() {
        return in.nextLine().trim();
    }
}

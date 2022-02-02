package Duke.UI;
import Duke.Exception.DukeException;

import java.util.Scanner;
public class Ui {
    static final String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
    static final String intro = "Wake up, I need you \n";
    static final String seperator = "_____________________________________________\n";
    static final String bye = "Welcome Home John";
    Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public String read() {
        return this.sc.nextLine();
    }

    public void startmessage() {
        String message = logo + intro + seperator;
        System.out.println(message);
    }

    public void endmessage() {
        System.out.println(bye);
    }

    public void errorMessage(DukeException error) {
        System.out.println(error.getMessage());
    }

    public void divider() {
        System.out.println(seperator);
    }

}

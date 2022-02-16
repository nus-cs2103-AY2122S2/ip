package Duke.UI;
import Duke.Exception.DukeException;

import javax.print.DocFlavor;
import java.util.Scanner;
public class Ui {
    static final String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
    static final String INTRO = "Wake up, I need you \n";
    static final String SEPERATOR = "_____________________________________________\n";
    static final String BYE = "Welcome Home John";
    Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public String read() {
        return this.sc.nextLine();
    }

    /**
     * Outputs a welcome message to the user at the start of Duke
     *
     * @return a String output
     */
    public String startmessage() {
        String message = logo + INTRO + SEPERATOR;
        return message;
    }
    /**
     * Outputs an end message to the user at the end of Duke
     *
     * @return a String output
     */
    public String endmessage() {
        return BYE;
    }

    /**
     * Outputs an error message to the user in the event of a caught exception
     *
     * @return a String output
     */
    public String errorMessage(DukeException error) {
        return error.getMessage();
    }

    public void divider() {
        System.out.println(SEPERATOR);
    }

}

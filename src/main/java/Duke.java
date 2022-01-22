import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Project Duke is a educational software project designed to take you
 * through the steps of building a small software incrementally,
 * while applying as many Java and SE techniques as possible along the way.
 *
 * @author AdrianOngJJ
 */
public class Duke {
    private static final String LINE_BREAK = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";

    /**
     * Prints line break
     * @return void
     */
    private static final void printLineBreak() {
        System.out.println(LINE_BREAK);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printLineBreak();
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        printLineBreak();
        String inputCommand = "";
        boolean ifBye = false;
        do {
            inputCommand = br.readLine();
            switch (inputCommand) {
                case "bye":
                    ifBye = true;
                    bw.write("Bye. Hope to see you again soon!");
                    bw.close();
                    break;

                default:
                    System.out.println(inputCommand);
            }
        } while (!ifBye);

    }
}

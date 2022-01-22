import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

/**
 * Project Duke is a educational software project designed to take you
 * through the steps of building a small software incrementally,
 * while applying as many Java and SE techniques as possible along the way.
 *
 * @author AdrianOngJJ
 */
public class Duke {
    private static final String LINE_BREAK = "\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
    private static final ArrayList<String> masterList = new ArrayList<>();
    /**
     * Prints line break
     * @return void
     */
    private static final void printLineBreak() {
        System.out.println(LINE_BREAK);
    }

    private static final void printList(BufferedWriter bw) throws Exception {
        Object[] masterListArr = masterList.toArray();
        int x = 1;
        for (Object s : masterListArr) {
            bw.write(x++ + ". " + s);
            bw.newLine();
        }
        bw.flush();
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
        String inputCommand = "";
        boolean ifBye = false;
        do {
            printLineBreak();
            System.out.println();
            inputCommand = br.readLine();
            printLineBreak();
            switch (inputCommand) {
                case "bye":
                    ifBye = true;
                    System.out.println("Bye. Hope to see you again soon!");
                    break;

                case "list":
                    printList(bw);
                    break;

                default:
                    masterList.add(inputCommand);
                    System.out.println("added: " + inputCommand);
            }
        } while (!ifBye);
        printLineBreak();

    }
}

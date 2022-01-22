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
 * @version 0.1
 * @since 22/1/2022
 */
public class Duke {
    private static final String LINE_BREAK = "\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
    private static final ArrayList<Task> masterList = new ArrayList<>();
    /**
     * Prints line break.
     * @return void
     */
    private static final void printLineBreak() {
        System.out.println(LINE_BREAK);
    }

    /**
     * Prints Master List
     * @param bw BufferedWriter from main to print out the Master List.
     * @throws java.io.IOException If an I/O error occurs. Only takes in string.
     */
    private static final void printList(BufferedWriter bw) throws Exception {
        for(int i = 0; i < masterList.size(); i++) {
            Task curr = masterList.get(i);
            bw.write((i + 1) + "." + curr.toString());
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
        String[] inputCommandArr = new String[100];
        boolean ifBye = false;
        do {
            printLineBreak();
            System.out.println();
            inputCommand = br.readLine();
            inputCommandArr = inputCommand.split(" ");
            printLineBreak();
            switch (inputCommandArr[0]) {
                case "bye":
                    ifBye = true;
                    System.out.println("Bye. Hope to see you again soon!");
                    break;

                case "list":
                    bw.write("Here are the tasks in your list:\n");
                    printList(bw);
                    break;

                case "mark":
                    bw.write("Nice! I've marked this task as done:\n");
                    int index = Integer.parseInt(inputCommandArr[1]) - 1;
                    Task curr = masterList.get(index); // task to be marked
                    masterList.set(index, curr.markAsDone());
                    bw.write(masterList.get(index).toString());
                    bw.flush();
                    break;

                case "unmark":
                    bw.write("OK, I've marked this task as not done yet:\n");
                    int indexUnmark = Integer.parseInt(inputCommandArr[1]) - 1;
                    Task currUnmark = masterList.get(indexUnmark); // task to be unmarked
                    masterList.set(indexUnmark, currUnmark.unmarkItem());
                    bw.write(masterList.get(indexUnmark).toString());
                    bw.flush();
                    break;

                default:
                    masterList.add(new Task(inputCommand));
                    System.out.println("added: " + inputCommand);
            }
        } while (!ifBye);
        printLineBreak();

    }
}

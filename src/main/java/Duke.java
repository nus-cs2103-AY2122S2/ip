import java.io.*;
import java.util.ArrayList;
import java.nio.file.Paths;
import java.nio.file.Files;

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

    private static final String getDateTime(String[] inputArr) {
        return inputArr[1].split("/")[1].split(" ", 2)[1]; // split input from slash
    }

    private static final String getDescription(String[] inputArr) {
        return inputArr[1].split("/")[0]; // split input from slash
    }

    public static void main(String[] args) throws Exception {

        String home =  System.getProperty("user.home"); // base directory
        // following code should give me [HOME_DIRECTORY]/Desktop/iP/data
        java.nio.file.Path path = java.nio.file.Paths.get(home,"Desktop", "iP", "data");
        boolean directoryExists = java.nio.file.Files.exists(path);
        System.out.println(directoryExists);
        // if path doesn't exist, make new directory and file
        if (!directoryExists) {
            File dataDirectory = new File(path.toString());
            File dukeStore = new File(path + "\\duke.txt");
            System.out.println("I tried creating a new directory " + dataDirectory);
            System.out.println("I tried creating a new file " + dukeStore);

        }
        System.out.println("Does this directory " + path + " exist? " + directoryExists);
        System.exit(0);
        //java.nio.file.Path path = java.nio.file.Paths.get(home, "");
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
        String input; // to store raw input command
        String[] inputArr; // to store split input command
        boolean ifBye = false;
        do {
            printLineBreak();
            System.out.println();
            input = br.readLine();
            inputArr = input.split(" ", 2); // split first word from body
            printLineBreak();
            switch (inputArr[0]) {
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
                int index = Integer.parseInt(inputArr[1]) - 1;
                Task curr = masterList.get(index); // task to be marked
                masterList.set(index, curr.markAsDone());
                bw.write(masterList.get(index).toString());
                bw.flush();
                break;

            case "unmark":
                bw.write("OK, I've marked this task as not done yet:\n");
                int indexUnmark = Integer.parseInt(inputArr[1]) - 1;
                Task currUnmark = masterList.get(indexUnmark); // task to be unmarked
                masterList.set(indexUnmark, currUnmark.unmarkItem());
                bw.write(masterList.get(indexUnmark).toString());
                bw.flush();
                break;

            case "deadline":
                masterList.add(new Deadlines(getDescription(inputArr), getDateTime(inputArr)));
                System.out.println("Got it. I've added this task:\n\t "
                        + (masterList.get(masterList.size() - 1)).toString()
                        + "\nNow you have " + masterList.size() + " tasks in the list.");
                break;

            case "todo":
                masterList.add(new ToDos(getDescription(inputArr)));
                System.out.println("Got it. I've added this task:\n\t "
                        + (masterList.get(masterList.size() - 1)).toString()
                        + "\nNow you have " + masterList.size() + " tasks in the list.");
                break;

            case "event":
                masterList.add(new Events(getDescription(inputArr), getDateTime(inputArr)));
                System.out.println("Got it. I've added this task:\n\t "
                        + (masterList.get(masterList.size() - 1)).toString()
                        + "\nNow you have " + masterList.size() + " tasks in the list.");
                break;

            case "delete":
                bw.write("Noted. I've removed this task:\n\t");
                int indexDel = Integer.parseInt(inputArr[1]) - 1;
                bw.write(masterList.remove(indexDel).toString());
                bw.write("\nNow you have " + masterList.size() + " tasks in list.");
                bw.flush();
                break;


            default:
                System.out.println("Invalid input: " + input);
            }
        } while (!ifBye);
        printLineBreak();

    }
}

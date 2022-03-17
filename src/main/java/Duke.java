import java.io.*;
import java.nio.Buffer;
import java.nio.file.Path;
import java.util.ArrayList;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.Scanner;
import java.util.Arrays;


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
    private static ArrayList<Task> masterList = new ArrayList<>();
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private final String END_MESSAGE = "Sayonara~";


    public Duke(String filePath) {
        this.ui = new Ui(LINE_BREAK);
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void run() {
        this.ui.initUi();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Parser parser = new Parser();
        String parserOutput = "";
        while (!parserOutput.equals(END_MESSAGE)) {
            try {
                String input = br.readLine();
                parserOutput = parser.parse(input, this.tasks, this.storage);
                System.out.println();
            } catch (DukeException e) {
               System.out.println(e.getMessage());
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
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



    public static void main(String[] args) {




        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printLineBreak();
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");



    }
}

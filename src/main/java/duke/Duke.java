package duke;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
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
            System.out.println(e.getMessage());
            tasks = new TaskList();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Runs Duke to receive commands until command "bye" is given
     */
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
                System.out.println(parserOutput);
            } catch (DukeException e) {
               System.out.println(e.getMessage());
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke("/tasks.txt").run();
    }
}

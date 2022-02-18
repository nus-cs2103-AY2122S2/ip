package duke;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Represents a chatbot called BH
 * It is able to read input and proceed with different kind of task and save all the tasks
 *
 * @author brandonrhan
 * @version 0.0.0
 */
public class BH {
    private static final Path FILEPATH = Paths.get(System.getProperty("user.home"),
            "Downloads", "NUS", "CS2103", "ip", "data", "duke.txt");
    private static final Path DIRPATH = Paths.get(System.getProperty("user.home"),
            "Downloads", "NUS", "CS2103", "ip", "data");
    //"/Users/brandonrhan/Downloads/NUS/CS2103/ip/data/duke.txt";
    private TaskList taskList;
    private Storage storage;
    private Scanner sc = new Scanner(System.in);
    private Ui ui;
    private Parser parser;

    /**
     * Constructs a BH
     *
     * @throws DukeException If the filepath is invalid
     */
    public BH() throws DukeException {
        this.storage = new Storage(FILEPATH, DIRPATH);
        this.taskList = new TaskList(this.storage.load());
        this.ui = new Ui();
        this.parser = new Parser(this.ui, this.taskList, this.storage);
    }

    /**
     * Executes the input by parser
     *
     * @param input User Command
     * @return Result after execution
     * @throws DukeException If invalid input is detected
     */
    public String run(String input) throws DukeException {
        return this.parser.execute(input);
    }


    /**
     * creates a new BH object and start running the program
     */
    public static class Duke {
        private BH bh;

        /**
         * Constructs a Duke
         *
         * @throws DukeException If read file wrongly
         */
        public Duke() throws DukeException {
            this.bh = new BH();
        }

        /**
         * Gets the response from BH
         *
         * @param input User command
         * @return Response from BH
         * @throws DukeException If invalid input is detected
         */
        public String getResponse(String input) throws DukeException {
            return this.bh.run(input);
        }


    }
}

package duke;

import java.util.Scanner;

/**
 * Represents a chatbot called BH
 * It is able to read input and proceed with different kind of task and save all the tasks
 */
public class BH {
    private static final String FILEPATH = "/Users/brandonrhan/Downloads/NUS/CS2103/ip/data/duke.txt";
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
        this.storage = new Storage(FILEPATH);
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




}

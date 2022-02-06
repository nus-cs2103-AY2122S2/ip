package duke;

import java.util.Scanner;

/**
 * Represent a chatbot called BH
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
     * constructor of BH
     * @throws DukeException if the filepath is invalid
     */
    public BH() throws DukeException {
        this.storage = new Storage(FILEPATH);
        this.taskList = new TaskList(this.storage.load());
        this.ui = new Ui();
        this.parser = new Parser(this.ui, this.taskList, this.storage);
    }

    /**
     * Return a String of line
     * @return String of line
     */
    public String getUiLine() {
        return this.ui.getLine();
    }

    /**
     * Read the input and execute the command according to the input
     * Return the output as a String
     * @param input Command from users
     * @return String of the result after execuation
     * @throws DukeException
     */
    public String run(String input) throws DukeException {
        return this.parser.execute(input);
    }




}

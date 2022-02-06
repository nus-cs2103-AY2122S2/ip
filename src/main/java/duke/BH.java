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

    public String getUiGreet() {
        return this.ui.greet();
    }

    public String getUiLine() {
        return this.ui.getLine();
    }


    @SuppressWarnings("checkstyle:OperatorWrap")
    public String run(String input) throws DukeException {
        return this.parser.execute(input);
    }




}

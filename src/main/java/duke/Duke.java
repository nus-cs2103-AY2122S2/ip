package duke;

import java.util.Scanner;

/**
 * Represents a Duke chat bot and task management system.
 */
public class Duke {
    private static final String NAME = "Cindy's Duke Bot";
    private static final String PATH = "./duke.txt";

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return "Duke heard: " + input;
    }

    /**
     * Runs the bot.
     */
    public void run() {
        Scanner sc = new Scanner(System.in);
        TaskList taskList = new TaskList();
        Storage storage = new Storage(PATH);
        Ui ui = new Ui(NAME);
        Parser parser = new Parser(sc, taskList);

        ui.boot();
        try {
            storage.loadTo(taskList);
        } catch (DukeException e) {
            System.err.println(e.toString());
        }
        ui.start();
        parser.parse();
        storage.writeFrom(taskList);
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}









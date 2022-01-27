package Duke;

import Task.Task;

import java.io.IOException;

import java.util.ArrayList;

public class Duke {

    /**
     * Main method of Duke program.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {

        Ui ui = new Ui();
        ui.displayGreeting();

        Storage storage = new Storage();
        ArrayList<Task> list = storage.load();

        Parser parser = new Parser();
        CommandList commandList = new CommandList();

        while (!parser.isTerminated) {
            try {
                commandList.execute(parser.readInput(), parser, list, storage);
            } catch (IOException e) {
                System.out.println("Unable to read input!");
            }
        }
    }
}
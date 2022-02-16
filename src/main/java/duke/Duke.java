package duke;


/**
 * Duke is a bot that helps to keep track of tasks
 */
public class Duke {

    /**
     * Main method
     * @param args
     */
    public static void main(String[] args) {

        FileReaderWriter.readFile();
        Ui.start();
        FileReaderWriter.writeToFile();

    }
}

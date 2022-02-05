package duke;

/**
 * Main class of Duke
 * Prints the introduction text of Duke
 *
 * @author Justin Ng Jie Ern
 */
public class Duke {

    private static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n\n";

    private static String start = "Hello! I am Duke.\n"
            + "Your Personal Assistant.\n\n"
            + "Input 'help' for the Command Manual!!\n\n"
            + "What can I do for you?\n"
            + "__________________________________________";

    private static void run() {
        System.out.println(logo + start);
        TaskList taskList = new TaskList();
        Storage storage = new Storage(taskList);
        Ui ui = new Ui(taskList, storage);

        ui.run();
    }

    public static void main(String[] args) {
        run();
    }
}


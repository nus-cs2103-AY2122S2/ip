package duke;

import duke.util.Parser;

import java.util.Scanner;

/**
 * Duke driver class, contains the main method to initiate
 * Duke chat-bot.
 */
public class Duke {
    private TaskList taskList;
    private final Ui ui;
    private final Storage storage;

    /**
     * Duke constructor method, takes no parameter,
     * initialize Ui, storage, and tasklist loaded from
     * saved storage.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage();
        try {
            this.taskList = storage.load();
        } catch (Exception e) {
            ui.showLoadingError(e);
            taskList = new TaskList();
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     * Run the Duke chat-bot to accept user input.
     * Process user's input and stopped when user input "bye"
     */
    public void run() {
        Scanner sc = new Scanner(System.in);
        ui.greetings();
        while (true) {
            try {
                String output = Parser.parse(sc.nextLine(), taskList);
                if (output.equals("BYE")) {
                    break;
                }
                ui.log(output);
                storage.updateStorage(taskList);
            } catch (Exception e) {
                ui.showLoadingError(e);
            }
        }
        ui.bye();
    }

    public String getResponse(String input) {
        try {
            String output = Parser.parse(input, taskList);
            return output;
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}

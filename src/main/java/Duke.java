import java.lang.System;
import java.io.IOException;



public class Duke {

    private final Ui ui;
    private TaskList tasks;
    private Storage storage;

    /**
     *
     * @param path
     */
    public Duke(String path) {
        ui = new Ui();
        try {
            storage = new Storage(path);
            tasks = new TaskList(storage.readTasks());
        } catch (IOException ie) {
            System.out.println("OOPS! An error occurred while attempting to create/retrieve storage file.");
            System.exit(0);
        } catch (DukeException de) {
            de.getMessage();
            tasks = new TaskList();
        }

    }


    public void run() {
        System.out.println("Hello! I'm Duke.\n" + "What can I do for you?");
        boolean isExit = false;

        while (!isExit) {
            try {
                String stringCommand = ui.readCommand();
                Command command = Parser.parseCommand(stringCommand);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (DukeException de) {

                de.getMessage();

            } catch (IOException ie) {
                System.out.println("OOPS! An error occurred while attempting to update storage file:\n"
                        + ie.getMessage());
            }
        }

        ui.closeSc();
        System.out.println("Bye. Hope to see you again soon!\n");
    }

    public String getResponse(String input) {
        try {

            Command command = Parser.parseCommand(input);
            return command.execute(tasks, ui, storage);
        } catch (DukeException de) {
            de.getMessage();
        } catch (IOException ie) {
            System.out.println("OOPS! An error occurred while attempting to update storage file:\n"
                    + ie.getMessage());
        }
        return "Unknown Command?";
    }


}
package duke;

import task.TaskList;

/**
 * Represents Mike.
 */
public class Duke {

    /** Storage where Mike is saved, read and updated. */
    private Storage storage;
    /** List containing Tasks for Mike to process. */
    private TaskList tasks;
    /** Parser to handle user inputs. */
    private Parser parser;

    /**
     * Instantiates Mike with storage,
     * task list and parser information.
     */
    public Duke() {
        try {
            storage = new Storage();
            tasks = new TaskList(this.storage.readData());
            parser = new Parser(this.tasks);
        } catch (DukeException dukeException) {
            System.out.println(dukeException.getMessage());
        } catch (Exception exception) {
            System.out.println(UI.ERROR_CREATION);
        }
    }

    /**
     * Returns Mike response according to
     * the user command input.
     *
     * @param command User command input.
     * @return Mike response.
     */
    public String getResponse(String command) {
        try {
            if (command.equals("bye")) {
                storage.storeData(tasks.getList());
                return UI.printTerminate();
            } else {
                return parser.processCommand(command);
            }
        } catch (DukeException dukeException) {
            return dukeException.getMessage();
        } catch (Exception exception) {
            return UI.ERROR_INVALID;
        }
    }
}

package duke;

import duke.commands.Command;
import duke.exceptions.DukeException;

public class Duke {
    private static final String STATUS_RUNNING = "running";
    private static final String STATUS_STOPPED = "stopped";

    private String status;
    private final TextUI ui;
    private final Storage storage;
    private final DukeList dukeList;

    /**
     * Constructor for the Duke bot
     */
    public Duke() {
        this.status = STATUS_RUNNING;
        this.ui = new TextUI();
        this.storage = new Storage();
        this.dukeList = new DukeList(storage);
    }

    /**
     * Checks if the current instance is running.
     *
     * @return boolean of whether the instance of running
     */
    public boolean isRunning() {
        return this.status.equals(STATUS_RUNNING);
    }

    /**
     * Handler for the duke bot.
     */
    private void handler() {
        ui.greet();

        while (this.status.equals(STATUS_RUNNING) && ui.hasNextCmd()) {
            try {
                String cmdString = ui.nextCmd();
                Command cmd = Parser.parse(cmdString);
                cmd.execute(dukeList, ui);
                if (cmd.isExit()) {
                    this.status = STATUS_STOPPED;
                }
            } catch (DukeException e) {
                ui.printMsg(e.getMessage());
            }
        }
    }

    /**
     * Get response from executing the command.
     * @param cmdString command to execute
     * @return result
     */
    public String getResponse(String cmdString) {
        try {
            Command cmd = Parser.parse(cmdString);
            String result = cmd.getResult(dukeList);
            if (cmd.isExit()) {
                this.status = STATUS_STOPPED;
            }
            return result;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    /**
     * Entry point for duke app.
     *
     * @param args arguments
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.handler();
    }
}

package duke;

import duke.commands.Command;
import duke.exceptions.DukeException;

public class Duke {
    private static final String STATUS_RUNNING = "running";
    private static final String STATUS_STOPPED = "stopped";

    private String status;
    private final UI ui;
    private final Storage storage;
    private final DukeList dukeList;

    /**
     * Constructor for the Duke.Duke bot
     */
    public Duke() {
        this.status = STATUS_RUNNING;
        this.ui = new UI();
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
     * Entry point for duke app.
     *
     * @param args arguments
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.handler();
    }
}

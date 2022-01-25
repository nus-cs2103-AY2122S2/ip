package duke;

import duke.commands.Command;
import duke.exceptions.DukeException;

public class Duke {
    private static final String STATUS_RUNNING = "running";
    private static final String STATUS_STOPPED = "stopped";

    private String status;
    private final UI ui;
    private final DukeList dukeList;

    /**
     * Constructor for the Duke.Duke bot
     */
    public Duke() {
        this.status = STATUS_RUNNING;
        this.ui = new UI();
        this.dukeList = new DukeList();
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
                if (cmd.isExit()) this.status = STATUS_STOPPED;
            } catch (DukeException e) {
                ui.printMsg(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.handler();
    }
}

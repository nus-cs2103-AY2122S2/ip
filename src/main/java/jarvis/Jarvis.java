package jarvis;

import jarvis.commands.Command;
import jarvis.exceptions.JarvisException;
import jarvis.tasks.TaskList;
import jarvis.utils.Parser;
import jarvis.utils.Storage;
import jarvis.utils.TextUI;

public class Jarvis {
    private static final String STATUS_RUNNING = "running";
    private static final String STATUS_STOPPED = "stopped";

    private String status;
    private final TextUI ui;
    private final Storage storage;
    private final TaskList taskList;

    /**
     * Constructor for the Duke bot
     */
    public Jarvis() {
        this.status = STATUS_RUNNING;
        this.ui = new TextUI();
        this.storage = new Storage();
        this.taskList = new TaskList(storage);
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
                cmd.execute(taskList, ui);
                if (cmd.isExit()) {
                    this.status = STATUS_STOPPED;
                }
            } catch (JarvisException e) {
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
            String result = cmd.getResult(taskList);
            if (cmd.isExit()) {
                this.status = STATUS_STOPPED;
            }
            return result;
        } catch (JarvisException e) {
            return e.getMessage();
        }
    }

    /**
     * Entry point for duke app.
     *
     * @param args arguments
     */
    public static void main(String[] args) {
        Jarvis jarvis = new Jarvis();
        jarvis.handler();
    }
}

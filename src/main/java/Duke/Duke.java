import exceptions.DukeException;

public class Duke {
    private static final String STATUS_RUNNING = "running";
    private static final String STATUS_STOPPED = "stopped";

    private static final String QUIT_COMMAND = "bye";
    private static final String LIST_COMMAND = "list";
    private static final String MARK_COMMAND = "mark";
    private static final String UNMARK_COMMAND = "unmark";
    private static final String DELETE_COMMAND = "delete";

    private String status;
    private final UI ui;
    private final DukeList dukeList;

    /**
     * Constructor for the Duke bot
     */
    public Duke() {
        this.status = STATUS_RUNNING;
        this.ui = new UI();
        this.dukeList = new DukeList();
    }

    /**
     * Parse the user command.
     *
     * @param cmd string command
     * @return result after executing the command
     */
    private String parseCommand(String cmd) {
        String[] argv = cmd.split(" ", 2);
        String action = argv[0];

        String result;
        int idx;
        switch (action) {
            case QUIT_COMMAND:
                result = ui.getGoodbyeMsg();
                this.status = STATUS_STOPPED;
                break;

            case LIST_COMMAND:
                result = this.dukeList.toString();
                break;

            case MARK_COMMAND:
                try {
                    idx = Integer.parseInt(argv[1]);
                    result = this.dukeList.markTask(idx);
                } catch (IndexOutOfBoundsException e) {
                    result = e.getMessage();
                }
                break;

            case UNMARK_COMMAND:
                try {
                    idx = Integer.parseInt(argv[1]);
                    result = this.dukeList.unmarkTask(idx);
                } catch (IndexOutOfBoundsException e) {
                    result = e.getMessage();
                }
                break;

            case DELETE_COMMAND:
                try {
                    idx = Integer.parseInt(argv[1]);
                    result = this.dukeList.delete(idx);
                } catch (IndexOutOfBoundsException e) {
                    result = e.getMessage();
                }
                break;

            default:
                try {
                    result = this.dukeList.add(cmd);
                } catch (DukeException e) {
                    result = e.getMessage();
                }
                break;
        }
        return result;
    }

    /**
     * Handler for the duke bot.
     */
    private void handler() {
        ui.greet();

        while (this.status.equals(STATUS_RUNNING) && ui.hasNextCmd()) {
            String cmd = ui.nextCmd();
            String result = parseCommand(cmd);
            ui.printMsg(result);
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.handler();
    }
}

package duke;

/**
 * create a new BH object and start running the program
 */
public class Duke {
    private BH bh;

    /**
     * Constructor of Duke
     *
     * @throws DukeException If read file wrongly
     */
    public Duke() throws DukeException {
        this.bh = new BH();
    }

    /**
     * Gets the response from BH
     *
     * @param input User command
     * @return Response from BH
     * @throws DukeException If invalid input is detected
     */
    public String getResponse(String input) throws DukeException {
        return this.bh.run(input);
    }
}

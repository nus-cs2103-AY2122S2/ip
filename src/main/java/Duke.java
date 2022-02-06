
import duke.BH;
import duke.DukeException;

/**
 * create a new BH object and start running the program
 */
public class Duke{
    public BH bh;

    /**
     * Constructor of Duke
     * @throws DukeException if read file wrongly
     */
    public Duke() throws DukeException {
        this.bh = new BH();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) throws DukeException {
        return this.bh.run(input);
    }


}


import duke.BH;
import duke.DukeException;

/**
 * create a new BH object and start running the program
 */
public class Duke{

    public BH bh;

    public Duke() throws DukeException {
        this.bh = new BH();
    }

    String getBhGreet() {
        return this.bh.getUiGreet();
    }

    String getBhLine() {
        return this.bh.getUiLine();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) throws DukeException {
        return this.bh.run(input);
    }


}


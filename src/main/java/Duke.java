import duke.managers.DukeManager;

public class Duke {
    /**
     * The main function of Duke.
     */
    public static void main(String[] args) {
        DukeManager duke = new DukeManager("data/duke.txt");
        duke.run();
    }
}

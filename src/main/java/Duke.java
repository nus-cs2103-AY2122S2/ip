import user.Ui;

/** The main Duke class, that allows the user to save tasks. */
public class Duke {

    public Ui ui;

    /**
     * Constructor for the Duke class.
     */
    public Duke() {
        ui = new Ui();
    }

    /**
     * The main function that starts Duke.
     *
     * @param args String inputs, but not used for Duke.
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.ui.greet();
        String userInput;
        boolean finished = false;
        while (!finished) {
            userInput = duke.ui.getInput();
            if (userInput.equals("bye")) {
                duke.ui.sayGoodbye();
                finished = true;
            } else {
                duke.ui.handle(userInput);
            }
        }
    }
}

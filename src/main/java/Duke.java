import user.Ui;


/** The main Duke class, that allows the user to save tasks. */
public class Duke {

    private final Ui ui;

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
        String[] result;
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

    /**
     * Given a string input by the user, returns a String response.
     *
     * @param input User input.
     * @return Response depending on what the user inputs.
     */
    public String getResponse(String input) {
        if (input.equals("")) {
            return input;
        }
        String[] result = ui.handle(input);
        return String.join("\n", result);
    }
}

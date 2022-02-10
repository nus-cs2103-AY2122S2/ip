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

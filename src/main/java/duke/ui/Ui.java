package duke.ui;

/**
 * UI of the chatbot.
 */
public class Ui {
    /**
     * Creates a new UI object.
     */
    public Ui() {}

    /**
     * Adds four empty white spaces to an input.
     *
     * @param s the target input
     * @return input with prefix of four empty white spaces
     */
    public String addTab(String s) {
        return "    " + s;
    }
}

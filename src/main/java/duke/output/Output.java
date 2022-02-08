package duke.output;

/**
 * Encapsulates the output of a Duke command.
 */
public abstract class Output {
    protected String text;

    Output(String text) {
        this.text = text;
    }
    
    public abstract String getMessage();

    @Override
    public String toString() {
        return getMessage();
    }
}

/**
 * The Echo class is used to automatically format what Duke says.
 * This is the other half of the analogue to the 'Ui' class in the example.
 * Anytime you see Echo, it's UI.
 *
 * @author Rdac0
 */
public class Echo {

    /**
     * Creates an Echo object.
     */
    public Echo() {}

    /**
     * Prints Duke's message, formatted.
     *
     * @param temp String to be printed.
     */
    public void echoString (String temp) {
        System.out.println("  > " + temp + "\n");
    }

}

package utilities;

/** Custom output formatter class to support bulk string appends.
 * @author s7manth
 * @version 0.1
 */
public class OutputFormatter {
    private StringBuilder stringBuilder;

    /** Default constructor for the OutputFormatter class.
     */
    private OutputFormatter() {
        this.stringBuilder = new StringBuilder();
    }

    /** Factory method for the class.
     * @return An instance of the OutputFormatter class.
     */
    public static OutputFormatter getInstance() {
        return new OutputFormatter();
    }

    /** Appends objects in a bulk manner into a single string.
     * @param objectArray The array of objects to append into the OutputFormatter.
     */
    public void appendAll(Object... objectArray) {
        for (Object o : objectArray) {
            this.stringBuilder.append(o.toString());
        }
    }

    /** Appends a single object into the string.
     * @param object The object to append to the OutputFormatter.
     */
    public void append(Object object) {
        this.stringBuilder.append(object.toString());
    }

    /** Gets the formatted output.
     * @return The formatted output in the form of a single string.
     */
    public String getFormattedOutput() {
        return this.stringBuilder.toString();
    }

    /** Get a blank string.
     * @return Blank string.
     */
    public String emptyString() {
        return "";
    }
}

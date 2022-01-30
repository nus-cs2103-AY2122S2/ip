package duke.uicomponents;

/**
 * Shows the display when the exception is thrown.
 */
public class ErrorMenu{
    /**
     * Runs the error display.
     *
     * @param errorMessage The exception message.
     */
    public void run(String errorMessage) {
        System.out.println("============================");
        System.out.println("Error Detected: ");
        System.out.println(errorMessage);
    }
}

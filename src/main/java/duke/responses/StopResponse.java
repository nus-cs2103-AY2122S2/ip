package duke.responses;

/**
 * Response created at the termination of the Chatbot.
 */
public class StopResponse implements Response {

    /**
     * Callback function that displays the intended results
     */
    @Override
    public String callback() {
        String uiResponse = "";
        uiResponse += Response.DIVIDER + "\n";
        uiResponse += "Bye hope to see you agian!\n";
        uiResponse += Response.DIVIDER + "\n";
        return uiResponse;
    }
}

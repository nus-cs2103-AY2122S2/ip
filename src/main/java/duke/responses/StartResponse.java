package duke.responses;

/***
 * The response created at the start of the chatbot
 */
public class StartResponse implements Response {
    /***
     * Callback function that displays the intended results
     */
    @Override
    public String callback() {
        String uiResponse = "";
        uiResponse += Response.DIVIDER + "\n";
        uiResponse += "Hello! I'm Duke\n" + "What can I do for you?\n";
        uiResponse += Response.DIVIDER + "\n";
        return uiResponse;
    }
}

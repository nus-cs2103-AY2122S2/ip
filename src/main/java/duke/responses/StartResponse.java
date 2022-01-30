package duke.responses;

/***
 * The response created at the start of the chatbot
 */
public class StartResponse implements Response {
    /***
     * Callback function that displays the intended results
     */
    @Override
    public void callback() {
        System.out.println(
                "____________________________________________________________"
        );
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        System.out.println(
                "____________________________________________________________"
        );
    }
}

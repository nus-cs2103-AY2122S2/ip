package duke.responses;

/**
 * Response created at the termination of the Chatbot.
 */
public class StopResponse implements Response{

    /**
     * Callback function that displays the intended results
     */
    @Override
    public void callback() {
        System.out.println(
                "____________________________________________________________"
        );
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(
                "____________________________________________________________"
        );
    }
}

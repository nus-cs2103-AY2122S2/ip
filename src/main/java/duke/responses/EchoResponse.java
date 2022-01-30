package duke.responses;

/**
 * Type of response that is created to echo a response.
 */
public class EchoResponse implements Response {

    private String msg;

    /**
     * Constructor for Each Response.
     * @param msg string info.
     */
    EchoResponse(String msg) {
        this.msg = msg;
    }

    /**
     * Callback function that displays the intended results.
     */
    @Override
    public void callback() {
        System.out.println(
                "____________________________________________________________"
        );
        System.out.println(msg);
        System.out.println(
                "____________________________________________________________"
        );
    }
}

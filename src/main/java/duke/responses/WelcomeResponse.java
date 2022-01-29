package main.java.duke.responses;

/***
 * Welcome response
 */
public class WelcomeResponse implements Response {

    /**
     * Callback function that displays the intended results
     */
    @Override
    public void callback() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }
}

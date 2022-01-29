package main.java.duke.dukeexceptions;


public class ForeignException extends DukeException {
    
    public ForeignException(String msg) {
        super(msg);
    }

    /**
     * Prints a error msg
     */
    @Override
    public void callback() {
        System.out.println(
                "____________________________________________________________"
        );
        System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
        System.out.println(
                "____________________________________________________________"
        );
    }
}

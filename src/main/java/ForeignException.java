package main.java;


public class ForeignException extends DukeException {
    ForeignException(String msg) {
        super(msg);
    }

    @Override
    void callback() {
        System.out.println(
                "____________________________________________________________"
        );
        System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
        System.out.println(
                "____________________________________________________________"
        );
    }
}

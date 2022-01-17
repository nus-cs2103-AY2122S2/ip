package main.java;

public class StopResponse implements Response{
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

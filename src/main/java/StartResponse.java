package main.java;

public class StartResponse implements Response{
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

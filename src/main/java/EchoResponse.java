package main.java;

public class EchoResponse implements Response{
    String msg;

    EchoResponse(String msg) {
        this.msg = msg;
    }

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

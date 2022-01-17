package main.java;

public class AddResponse implements  Response{
    String msg;

    AddResponse(String msg) {
        this.msg = msg;
    }
    @Override
    public void callback() {
        System.out.println(
                "____________________________________________________________"
        );
        System.out.println("Added : " +msg);
        System.out.println(
                "____________________________________________________________"
        );
    }
}

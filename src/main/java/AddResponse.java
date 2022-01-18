package main.java;

public class AddResponse implements  Response{
    String msg;

    /**
     * Constructor of AddResponse.
     * @param msg the name of the task to be added.
     */

    AddResponse(String msg) {
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
        System.out.println("Added : " +msg);
        System.out.println(
                "____________________________________________________________"
        );
    }
}

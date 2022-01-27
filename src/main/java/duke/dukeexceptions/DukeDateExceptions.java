package main.java.duke.dukeexceptions;

public class DukeDateExceptions extends DukeException{
    public DukeDateExceptions(String msg) {
        super(msg);
    }

    @Override
    public void callback() {
        System.out.println(
                "____________________________________________________________"
        );
        System.out.println("Please provide a valid date time format");
        System.out.println(
                "____________________________________________________________"
        );
    }
}

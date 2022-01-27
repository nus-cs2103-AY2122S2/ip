package main.java.duke.dukeexceptions;

public class DukeTaskListException extends DukeDateExceptions{

    public DukeTaskListException(String msg) {
        super(msg);
    }

    @Override
    public void callback() {
        System.out.println(
                "____________________________________________________________"
        );
        System.out.println("This is not a valid index!");
        System.out.println(
                "____________________________________________________________"
        );

    }
}

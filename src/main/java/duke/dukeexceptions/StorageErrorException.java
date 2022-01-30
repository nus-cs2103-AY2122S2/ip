package duke.dukeexceptions;

public class StorageErrorException extends DukeException {
    public StorageErrorException(String msg) {
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
        System.out.println("Invalid Storage");
        System.out.println(
                "____________________________________________________________"
        );
    }
}

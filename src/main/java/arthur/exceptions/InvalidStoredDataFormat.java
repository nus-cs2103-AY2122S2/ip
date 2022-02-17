package arthur.exceptions;

public class InvalidStoredDataFormat extends Exception {
    public InvalidStoredDataFormat() {
        super("Invalid data format in storage data file. \nPlease clear data file and try again.");
    }
}

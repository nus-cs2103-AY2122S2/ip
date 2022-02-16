package aeromon;

/**
 * AeromonException class handles the exceptions specific to Aeromon.
 */
public class AeromonException extends Exception {

    /**
     * Constructs the AeromonException object.
     *
     * @param message the error messgae.
     */
    public AeromonException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "Ohnoz! " + getMessage();
    }
}

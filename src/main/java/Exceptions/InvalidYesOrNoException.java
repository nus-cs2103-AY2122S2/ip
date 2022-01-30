package Exceptions;

/**
 * <h1>InvalidYesOrNoException</h1>
 * <p>
 * InvalidYesOrNoException is used when conan expects the user to input only a yes or no command,
 * but the user has given a different response.
 * </p>
 *
 * @author Saravanan Anuja Harish
 */
public class InvalidYesOrNoException extends IllegalCommandException {

    private final String userInput;

    /**
     * Constructor for InvalidYesOrNoException.
     * @param userInput the user input.
     */
    public InvalidYesOrNoException(String userInput) {
        super(userInput);
        this.userInput = userInput;
    }

    /**
     * gives the string representation of the InvalidYesOrNoException.
     * @return the string representation of the exception object.
     */
    @Override
    public String toString() {
        return "You have entered: " + this.userInput + "\nPlease enter either yes or no";
    }

}

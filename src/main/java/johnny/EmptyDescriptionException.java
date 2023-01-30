package johnny;

public class EmptyDescriptionException extends Exception{

    /**
     * Returns error message for empty
     * description
     *
     * @return error message String.
     */
    public String errorMessage() {
        return "Error! Please enter a description for your todo task";
    }
}

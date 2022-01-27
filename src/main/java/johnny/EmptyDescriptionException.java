package johnny;

public class EmptyDescriptionException extends Exception{

    public String errorMessage() {
        return "Error! Please enter a description for your todo task";
    }
}

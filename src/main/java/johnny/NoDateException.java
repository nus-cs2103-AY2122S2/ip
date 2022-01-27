package johnny;

public class NoDateException extends Exception{

    public String errorMessage() {
        return "Error! Please enter a date for your deadline or event following a slash after the task description";
    }

}

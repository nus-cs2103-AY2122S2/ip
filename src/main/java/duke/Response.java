package duke;

/**
 * Represents a Response class that contains Duke's response to user input.
 *
 * @author  Elumalai Oviya Dharshini
 * @version 1.1
 */
public class Response {
    private Expression expression;
    private String message;
    private Boolean isExit = false;

    /**
     * Constructor for Response.
     *
     * @param message Duke's text response
     * @param expression Duke's visual expression
     */
    public Response(String message, Expression expression) {
        this.expression = expression;
        this.message = message;
    }

    public Expression getExpression() {
        return expression;
    }

    public String getMessage() {
        return message;
    }

    public void setExit() {
        isExit = true;
    }

    public boolean isExit() {
        return isExit;
    }
}

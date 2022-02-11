package duke;

public class Response {

    private final boolean isExit;
    private final String response;

    public Response(boolean isExit, String response) {
        this.isExit = isExit;
        this.response = response;
    }

    public boolean isExit() {
        return isExit;
    }

    public String getResponse() {
        return response;
    }

}

package duke.responses;

public class DuplicateTaskResponse implements Response {

    private String taskName;

    public DuplicateTaskResponse(String name) {
        this.taskName = name;
    }

    /**
     * Callback function that displays the intended results.
     */
    @Override
    public String callback() {
        String uiResponse = "";
        uiResponse += Response.DIVIDER + "\n";
        uiResponse += "There is already a task called " + taskName + " in the list. \n";
        uiResponse += Response.DIVIDER + "\n";
        return uiResponse;
    }
}

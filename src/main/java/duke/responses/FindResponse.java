package duke.responses;

import duke.data.TaskList;

/**
 * Response that is created in the event of FindCommand
 */
public class FindResponse implements Response {

    private TaskList iterate;
    private int count = 1;

    public FindResponse(TaskList iterate) {
        this.iterate = iterate;
    }
    @Override
    public String callback() {
        String uiResponse = "";
        uiResponse += Response.DIVIDER + "\n";
        uiResponse += "Here are the matching tasks in your list:\n";
        for (int i = 0; i < iterate.taskLength(); i++) {
            uiResponse += i + " " + iterate.getTask(i).display() + "\n";
        }
        uiResponse += Response.DIVIDER + "\n";
        return uiResponse;
    }
}

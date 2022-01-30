package duke.responses;

import duke.data.TaskList;

/**
 * Response that is created in the event of List command
 */
public class ListResponse implements Response {

    private TaskList iterate;
    private int count = 1;

    /**
     * Constructor for ListResponse.
     * @param iterate TaskList
     */
    public ListResponse(TaskList iterate) {
        this.iterate = iterate;
    }

    /**
     * Callback function that displays the intended results
     */
    @Override
    public void callback() {
        System.out.println(
                "____________________________________________________________"
        );

        System.out.println("Here are the tasks in your list:");
        iterate.getTaskList().forEach(x -> {
            System.out.println(count + ". " + x.display());
            count++;
        });

        System.out.println(
                "____________________________________________________________"
        );
    }
}

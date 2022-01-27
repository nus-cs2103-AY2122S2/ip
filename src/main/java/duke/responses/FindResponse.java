package main.java.duke.responses;

import main.java.duke.data.TaskList;

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
    public void callback() {
        System.out.println(
                "____________________________________________________________"
        );
        System.out.println("Here are the matching tasks in your list:");
        iterate.getTaskList().forEach(x -> {
            System.out.println(count + " " + x.display());
            count++;
        });
        System.out.println(
                "____________________________________________________________"
        );
    }
}

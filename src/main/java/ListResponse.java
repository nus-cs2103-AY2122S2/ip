package main.java;

import java.util.ArrayList;

/**
 * Reponse that is created in the event of List command
 */

public class ListResponse implements Response{

    ArrayList<Task> iterate;
    int count = 1;

    /**
     * Constructor for ListResponse.
     * @param iterate list of items.
     */
    ListResponse(ArrayList<Task> iterate) {
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
        iterate.forEach(x -> {
            System.out.println(count + ". " + x.display());
            count++;
        });

        System.out.println(
                "____________________________________________________________"
        );
    }
}
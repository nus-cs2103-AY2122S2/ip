package main.java;

import java.util.ArrayList;

public class ListResponse implements Response{

    ArrayList<Task> iterate;
    int count = 1;

    ListResponse(ArrayList iterate) {
        this.iterate = iterate;
    }

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
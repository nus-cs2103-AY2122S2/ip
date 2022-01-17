package main.java;

import java.util.ArrayList;

public class ListResponse implements Response{

    ArrayList<String> iterate;
    int count = 1;

    ListResponse(ArrayList iterate) {
        this.iterate = iterate;
    }

    @Override
    public void callback() {
        System.out.println(
                "____________________________________________________________"
        );
        iterate.forEach(x -> {
            System.out.println(count + ". " + x);
            count++;
        });

        System.out.println(
                "____________________________________________________________"
        );
    }
}
package duke.ui;

import java.util.Arrays;
import java.util.ArrayList;
import duke.task.Task;

public class Ui {
    public void print(String ... args) {
        System.out.printf("    %s%n", "==========================================");
        Arrays.asList(args).forEach((x) -> System.out.printf("    %s%n", x));
        System.out.printf("    %s%n", "==========================================");
    }

    public void print(ArrayList<Task> arr) {
        System.out.printf("    %s%n", "==========================================");
        if(arr.size() == 0)
            System.out.println("    Nothing to show!");
        else
            arr.forEach((x) -> System.out.printf("    %d. %s%n", arr.indexOf(x) + 1, x.toString()));
        System.out.printf("    %s%n", "==========================================");
    }
}
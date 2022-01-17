import java.util.Arrays;
import java.util.ArrayList;

class Printer {
    public void print(String ... args) {
        System.out.println(String.format("    %s", "=========================================="));
        Arrays.asList(args).forEach((x) -> { System.out.println(String.format("    %s", x.toString())); } );
        System.out.println(String.format("    %s", "=========================================="));
    }

    public void print(ArrayList<Task> arr) {
        System.out.println(String.format("    %s", "=========================================="));
        if(arr.size() == 0)
            System.out.println("    List is empty!");
        else
            arr.forEach((x) -> { System.out.println(String.format("    %d. %s", arr.indexOf(x) + 1, x.toString())); } );
        System.out.println(String.format("    %s", "=========================================="));
    }
}
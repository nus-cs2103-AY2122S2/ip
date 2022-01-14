import java.util.*;
import java.io.*;

public class Duke {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Duke: Hello! I'm Duke\n      What can I do for you?");
        String inp = "";
        List<String> a = new ArrayList<String>();
        while (!inp.equals("bye")) {
            inp = s.nextLine();
            if (inp.equals("bye")) {
                System.out.println("\nDuke:  Bye. Hope to see you again soon!");
            } else {
                System.out.println("\nDuke:  " + inp + "\n");
            }
        }
    }
}

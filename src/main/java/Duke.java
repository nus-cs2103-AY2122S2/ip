import java.io.*;
import java.util.*;

public class Duke {
    public static void main(String[] args) throws IOException {
        System.out.println("Hello!!! I'm jBot\nWhat can I do for you? :3");
        String s = "";
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            if (s.equals("bye")) {
                System.out.println("Bye!! See you again soon!!");
                break;
            } else
                System.out.println(s);
            s = br.readLine();
        }
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo)
    }
}

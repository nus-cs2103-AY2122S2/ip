import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Duke {
    public static void main(String[] args) throws IOException {
        
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String nextLine = br.readLine();
        while (!nextLine.equals("bye")) {
            System.out.println("__________________________________________________\n" 
                    + nextLine
                    + "\n__________________________________________________");
            nextLine = br.readLine();
        }
        System.out.println("__________________________________________________\n" 
                + "Bye. Hope to see you again soon!"
                + "\n__________________________________________________");
    }
}

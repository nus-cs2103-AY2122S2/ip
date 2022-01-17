import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;

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

        ArrayList<String> list = new ArrayList<String>();
        while (!nextLine.equals("bye")) {
            System.out.println("__________________________________________________");
            if (nextLine.equals("list")) {
                if (list.isEmpty()) {
                    System.out.println("Uh-oh! List is empty");
                } else {
                    for (int i = 0; i < list.size(); i++) {
                        String output = String.format("%d. %s", i + 1, list.get(i));
                        System.out.println(output);
                    }
                }
            } else {
                list.add(nextLine);
                System.out.println("added: " + nextLine);
            }
            System.out.println("__________________________________________________");
            nextLine = br.readLine();
        }
        System.out.println("__________________________________________________\n" 
                + "Bye. Hope to see you again soon!"
                + "\n__________________________________________________");
        br.close();
    }
}

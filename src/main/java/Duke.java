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

        ArrayList<Task> list = new ArrayList<Task>();

        while (!nextLine.equals("bye")) {
            System.out.println("__________________________________________________");
            if (nextLine.split(" ")[0].equals("mark")) {
                int indexToMark = Integer.parseInt(nextLine.split(" ")[1]) - 1;
                list.set(indexToMark, list.get(indexToMark).mark());
                System.out.println("Nice! I've marked this task as done:\n  " 
                        + list.get(indexToMark));
            } else if (nextLine.split(" ")[0].equals("unmark")) {
                int indexToMark = Integer.parseInt(nextLine.split(" ")[1]) - 1;
                list.set(indexToMark, list.get(indexToMark).unmark());
                System.out.println("OK, I've marked this task as not done yet:\n  " 
                        + list.get(indexToMark));
            } else if (nextLine.equals("list")) {
                if (list.isEmpty()) {
                    System.out.println("Uh-oh! List is empty");
                } else {
                    for (int i = 0; i < list.size(); i++) {
                        System.out.println((i + 1) + "." + list.get(i));
                    }
                }
            } else {
                list.add(new Task(nextLine));
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

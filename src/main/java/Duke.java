import java.io.*;
import java.util.*;

public class Duke {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Task> tasks = new ArrayList<>();
        System.out.println("Hello!!! I'm jBot\nWhat can I do for you? :3");
        String full = br.readLine();
        String[] s = full.split(" ");

        while (true) {
            // "bye" to end the program
            if (s[0].equals("bye")) {
                System.out.println("Bye!! See you again soon!!");
                break;
            }
            // if user requests to list their tasks
            else if (s[0].equals("list")){
                System.out.println("Here are the tasks in your lists:\n");
                int t = 1;
                for(Task item : tasks) {
                    System.out.println(t + ". [" + item.getDone() + "] " + item.desc);
                    t++;
                }
            }
            else if (s[0].equals("mark")) {
                int n = Integer.parseInt(s[1]) - 1;
                tasks.set(n, tasks.get(n).mark());
                Task temp = tasks.get(n);
                System.out.println("Alright! I've marked this as done:\n  [" + temp.getDone() + "] " + temp.desc);

            }
            else if (s[0].equals("unmark")) {
                int n = Integer.parseInt(s[1]) - 1;
                tasks.set(n, tasks.get(n).unmark());
                Task temp = tasks.get(n);
                System.out.println("Alright! I've marked this as not done:\n  [" + temp.getDone() + "] " + temp.desc);

            }
            // user add items to list
            else {
                System.out.println("     added: " + full);
                tasks.add(new Task(full));
            }
            full = br.readLine();
            s = full.split(" ");
        }
    }
}


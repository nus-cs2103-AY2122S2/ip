import java.io.*;
import java.util.*;

public class Duke {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Task> tasks = new ArrayList<>();
        System.out.println("Hello!!! I'm jBot\nWhat can I do for you? :3");
        String full = br.readLine();
        String[] s = full.split(" ");
        int items = 0;

        label:
        while (true) {
            // "bye" to end the program
            switch (s[0]) {
                case "bye":
                    System.out.println("Bye!! See you again soon!!");
                    break label;

                // if user requests to list their tasks
                case "list": {
                    System.out.println("Here are the tasks in your lists:\n");
                    int n = 1;
                    for (Task t : tasks) {
                        String by = "";
                        if (t.getType().equals("D") || t.getType().equals("E"))
                            by = t.getBy();
                        System.out.println(n + ". [" + t.getType() + "][" + t.getDone() + "] " + t.desc + by);
                        n++;
                    }
                    break;
                }
                case "mark": {
                    int n = Integer.parseInt(s[1]) - 1;
                    tasks.set(n, tasks.get(n).mark());
                    Task temp = tasks.get(n);
                    System.out.println("Alright! I've marked this as done:\n  [" + temp.getDone() + "] " + temp.desc);

                    break;
                }
                case "unmark": {
                    int n = Integer.parseInt(s[1]) - 1;
                    tasks.set(n, tasks.get(n).unmark());
                    Task temp = tasks.get(n);
                    System.out.println("Alright! I've marked this as not done:\n  [" + temp.getDone() + "] " + temp.desc);

                    break;
                }
                // user add a todo task
                case "todo":
                    Arrays.toString(full.split(" ", 2));
                    s = full.split(" ", 2);
                    System.out.println("Okay! I've added this task:\n  ");
                    tasks.add(new ToDo(s[1]));
//                System.out.println("full  " + s[1]);
                    System.out.println("[T][ ] " + s[1]);
                    items++;
                    System.out.println("\nNow you have " + items + " tasks on your list");

                    break;
                // user add a event task
                case "event":
                    Arrays.toString(full.split(" ", 2));
                    s = findDate(full.split(" "));
                    System.out.println("Okay! I've added this task:\n  ");
                    tasks.add(new Event(s[0], s[1]));
//                System.out.println("full  " + s[1]);
                    System.out.println("[E][ ] " + s[0]);
                    items++;
                    System.out.println("\nNow you have " + items + " tasks on your list");

                    break;
                // user add a deadline task
                case "deadline":
                    Arrays.toString(full.split(" ", 2));
                    s = findDate(full.split(" "));
                    System.out.println("Okay! I've added this task:\n  ");
                    tasks.add(new Deadline(s[0], s[1]));
//                System.out.println("full  " + s[1]);
                    System.out.println("[D][ ] " + s[0]);
                    items++;
                    System.out.println("\nNow you have " + items + " tasks on your list");

                    break;
                // user add items to list
                default:
                    System.out.println("     added: " + full);
                    tasks.add(new Task(full));
                    items++;
                    break;
            }
            full = br.readLine();
            s = full.split(" ");
        }
    }

    static String[] findDate(String[] full) {
        StringBuilder sb = new StringBuilder();
        String[] end = new String[100];
        int i = 0;
        for (String s : full) {
            if(s.charAt(0) == '/')
                break;
            i++;
        }
        // get the desc
        for(int j = 1; j < i; j++) {
            sb.append(full[j]).append(" ");
        }

        // get the time
        end[0] = sb.toString();
        sb = new StringBuilder();
        for(int j = i+1; j < full.length; j++) {
            sb.append(full[j]).append(" ");
        }
        end[1] = sb.toString();
//        end[1] = end[1].substring(1); // remove the slash
//        System.out.println("this is i " + end[1]);

        return end;
    }
}


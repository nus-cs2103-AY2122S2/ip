import java.util.*;
import java.lang.*;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        Scanner sc = new Scanner(System.in);

        int counter = 0;

        List list = new ArrayList<Task>();

        while(sc.hasNext()) {
            String value = sc.nextLine();
            String[] splitStr = value.split("\\s+");

            if (value.equals("bye")){
                System.out.println(" Bye. Hope to see you again soon!");
                break;
            }
            else if (value.equals("list")) {
                for (int i=0; i<list.size(); i++) {
                    System.out.println(i+1 + ". " + list.get(i));
                }
            }
            else if (splitStr[0].equals("mark")){
                int index = Integer.parseInt(splitStr[1]);
                Task task = (Task) list.get(index-1);
                task.markAsDone();
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println(list.get(index-1));
            }
            else if (splitStr[0].equals("unmark")){
                int index = Integer.parseInt(splitStr[1]);
                Task task = (Task) list.get(index-1);
                task.unmarkAsDone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(list.get(index-1));
            }
            else {
                list.add(new Task(value));
                System.out.println("added: "+ value);

            }
        }

    }
}



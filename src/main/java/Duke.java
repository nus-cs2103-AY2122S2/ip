import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    public static void listOut(ArrayList<Action> list) {
        int count = 1;
        for (Action act : list) {
            System.out.println(count + "." + act);
            count++;
        }
    }

    public static void main(String[] args) {
        String greeting = "Hello!! I am Duke, your humble pesonal chatbot.\n" +
                "What can I do for you?";
        System.out.println(greeting);
        Scanner sc = new Scanner(System.in);
        ArrayList<Action> store = new ArrayList<>();
        Action action = new Action(sc.nextLine());
        while (!action.isBye()) {
            if (action.isList()) {
                listOut(store);
                action = new Action(sc.nextLine());
                continue;
            }
            if (action.isMark()) {
                int index = Integer.parseInt(action.getBody());
                String statement = "";
                if (action.isUnmark()) {
                    store.set(index - 1, store.get(index - 1).setUnDone());
                    statement = "Ok, I have marked this task as not done yet:\n  ";
                } else {
                    store.set(index - 1, store.get(index - 1).setDone());
                    statement = "Nice! I have marked this task as done:\n  ";
                }
                System.out.println(statement + store.get(index - 1));
                action =  new Action(sc.nextLine());
                continue;
            }
            action = new Todo(action.getBody());
            if (action.isDeadline()) {
                String[] sarr = action.getBody().split("/by");
                action = new Deadline(sarr[0], sarr[1]);
                store.add(action);
            }
            if (action.isEvent()) {
                action = new Event(action.getBody());
                store.add(action);
            }
            store.add(action);
            System.out.println("Got it. I have added this task:\n  " + action +
                    "\nNow you have " + store.size() + " tasks in the list.");
            action = new Action(sc.nextLine());
        }
        System.out.println("Bye! Hope to see you again soon!");
    }
}

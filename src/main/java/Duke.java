import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    public static void listOut(ArrayList<String> list) {
        int count = 1;
        for (String s : list) {
            System.out.println(count + ". " + s);
            count++;
        }
    }

    public static void main(String[] args) {
        String greeting = "Hello!! I am Duke, your humble pesonal chatbot.\n" +
                "What can I do for you?";
        System.out.println(greeting);
        Scanner sc = new Scanner(System.in);
        ArrayList<String> store = new ArrayList<>();
        Action action = new Action(sc.nextLine());
        while (!action.isBye()) {
            if (action.isList()) {
                listOut(store);
                action = new Action(sc.nextLine());
                continue;
            }
            store.add(action.toString());
            System.out.println("added: " + action);
            action = new Action(sc.nextLine());
        }
        System.out.println("Bye! Hope to see you again soon!");
    }
}

import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String greeting = "Hello!! I am Duke, your humble pesonal chatbot.\n" +
                "What can I do for you?";
        System.out.println(greeting);
        Scanner sc = new Scanner(System.in);
        Action action = new Action(sc.nextLine());
        while (!action.isBye()) {
            System.out.println(action);
            action = new Action(sc.nextLine());
        }
        System.out.println("Bye! Hope to see you again soon!");
    }
}

import java.util.*;

public class PaggroBot {
    public static void main(String[] args) {
        Lister paggro = new Lister();
        Scanner sc = new Scanner(System.in);
        System.out.println("Hi I'm PaggroBot =.=\nWhat do you want? =.=");
        String command = sc.next();
        while (!command.equals("bye")) {
            if (command.equals("list")) {
                paggro.list();
            } else if (command.equals("mark") && sc.hasNextInt()) {
                int i = sc.nextInt();
                paggro.mark(i);
            } else if (command.equals("unmark") && sc.hasNextInt()) {
                int i = sc.nextInt();
                paggro.unmark(i);
            } else {
                paggro.add(new Task(command));
            }
            command = sc.next();
        }
        System.out.println("Oh finally. Please don't come back anytime soon. =.=");
    }
}

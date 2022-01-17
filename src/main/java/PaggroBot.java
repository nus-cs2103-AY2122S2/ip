import java.util.*;

public class PaggroBot {
    public static void main(String[] args) {
        Lister paggro = new Lister();
        Scanner sc = new Scanner(System.in);
        System.out.println("Hi I'm PaggroBot =.=\nWhat do you want? =.=");
        String command = sc.nextLine();
        while (!command.equals("bye")) {
            if (command.equals("list")) {
                paggro.list();
            } else {
                paggro.add(command);
            }
            command = sc.nextLine();
        }
        System.out.println("Oh finally. Please don't come back anytime soon. =.=");
    }
}

import java.util.*;

public class PaggroBot {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hi I'm PaggroBot =.=\nWhat do you want? =.=");
        String command = sc.nextLine();
        while (!command.equals("bye")) {
            System.out.println(command);
            command = sc.nextLine();
        }
        System.out.println("Oh finally. Please don't come back anytime soon. =.=");
    }
}

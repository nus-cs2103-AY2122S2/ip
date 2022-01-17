import java.util.*;

public class PaggroBot {
    public static void main(String[] args) {
        Lister paggro = new Lister();
        Scanner sc = new Scanner(System.in);
        System.out.println("   ________________________________________");
        System.out.println("    Hi I'm PaggroBot =.=\n    What do you want? =.=");
        System.out.println("   ________________________________________");
//        String input = sc.nextLine();
//        String[] arr = input.split(" ", 2);
//        String command = arr[0];
//        String parameters = arr[1];
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
                if (command.equals("todo")) {
                    paggro.add(new ToDo(sc.nextLine()));
                } else if (command.equals("deadline")) {
                    String details = sc.nextLine();
                    String[] arr = details.split(" /", 2);
                    String des = arr[0];
                    String time = arr[1];
                    paggro.add(new Deadline(des, time));
                } else if (command.equals("event")) {
                    String details = sc.nextLine();
                    String[] arr = details.split(" /", 2);
                    String des = arr[0];
                    String time = arr[1];
                    paggro.add(new Event(des, time));
                } else {
                    System.out.println("INVALID!!");
                }

            }
            command = sc.next();
        }
        System.out.println("   ________________________________________");
        System.out.println("    Oh finally. Please don't come back anytime soon. =.=");
        System.out.println("   ________________________________________");
    }
}

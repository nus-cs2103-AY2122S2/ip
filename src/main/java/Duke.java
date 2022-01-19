import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Duke {
    public static List<String> tasks = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hey there! I'm Mickey, your personal mouse assistant.\nWhat can I do for you today?\n");
        for (String in = sc.next(); !in.equals("bye"); in = sc.next()) {
            if (in.equals("list")) {
                System.out.println("\tOh boy! Here are your tasks:");

                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println("\t\t" + (i + 1) + ". " + tasks.get(i));
                }
            } else {
                tasks.add(in);
                System.out.println("\tadded: " + in);
            }
        }
        System.out.println("\tToodles! See ya real soon!");
    }
}

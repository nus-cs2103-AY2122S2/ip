import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        //String logo = " ____        _        \n"
        //        + "|  _ \\ _   _| | _____ \n"
        //        + "| | | | | | | |/ / _ \\\n"
        //        + "| |_| | |_| |   <  __/\n"
        //        + "|____/ \\__,_|_|\\_\\___|\n";
        //System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Duke");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");
        Scanner sc = new Scanner(System.in);
        String word = sc.nextLine();
        ArrayList<String> storingList = new ArrayList<>();
        while (! word.equals("bye")) {
            if (word.equals("list")) {
                System.out.println("____________________________________________________________");
                for (int i = 1; i <= storingList.size(); i++) {
                    System.out.println(i + ". " + storingList.get(i-1));
                }
                System.out.println("____________________________________________________________");
            } else {
                System.out.println("____________________________________________________________");
                System.out.println(" added: " + word);
                storingList.add(word);
                System.out.println("____________________________________________________________");
            }
            word = sc.nextLine();
        }
        System.out.println("____________________________________________________________");
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}

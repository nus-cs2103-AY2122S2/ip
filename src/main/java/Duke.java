import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    static String line = "\n_______________________^_^__________________________________\n";

    public static void addList() {
        Scanner sc = new Scanner(System.in);
        String input;
        ArrayList<String> list = new ArrayList<>();

        while(true) {
            input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println(line + "Bye. Hope to see you again soon!\n" + line);
                break;
            } else if(input.equals("list")) {
                System.out.println(line);
                for(int i = 1; i <= list.size(); i++) {
                    System.out.println(i + ". " + list.get(i - 1));
                }
                System.out.println(line);
            } else {
                list.add(input);
                System.out.println(line + "added: " + input + line);
            }
        }
    }

    public static void main(String[] args) {

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println(line + "Hello! I'm Duke\n" +
                "What can I do for you?\n" + line);

        Duke.addList();//level-2


    }

}

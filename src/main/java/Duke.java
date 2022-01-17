import java.util.*;

public class Duke {
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");

        ArrayList<String> tasks = new ArrayList<> ();


        while (true){
            String str= sc.nextLine();

            // quit
            if (str.equals("bye")) {
                break;
            }

            if (str.equals("list")) {
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println(i + 1 + " " + tasks.get(i));
                }
            }

            // repeat what the user says and save to tasks list
            else {
                tasks.add(str);
                System.out.println("added: " + str);
            }
        }
        System.out.println("Bye. Hope to see you again soon!\n");


    }
}

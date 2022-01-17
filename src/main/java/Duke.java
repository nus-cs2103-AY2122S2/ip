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

        // repeat what the user says
        while (true){
            String str= sc.nextLine();
            if (str.equals("bye")) {
                break;
            }
            System.out.println(str);
        }
        System.out.println("Bye. Hope to see you again soon!\n");


    }
}

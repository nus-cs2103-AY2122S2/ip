import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Scanner sc = new Scanner(System.in);

        List<Task> tasks = new ArrayList<Task>();

        while (true) {
            String[] instruction = sc.nextLine().split(" ");
            switch(instruction[0]){
                case "bye": {
                    System.out.println("Bye. Hope to see you again soon!\n");
                    return;
                }

                case "list": {
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println(i + 1 + ". " + tasks.get(i));
                    }
                    break;
                }

                default: {
                    String instructionName = String.join(" ", instruction);
                    tasks.add(new Task(instructionName));
                    System.out.println("added: " + instructionName);
                }
            }
            System.out.println("");
        }
    }
}

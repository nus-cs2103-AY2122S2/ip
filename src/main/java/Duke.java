import java.io.*;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) throws Exception {
        String logo = "    ____________________________________________________________\n" +
                "     Hello! I'm Duke\n" +
                "     What can I do for you?\n" +
                "    ____________________________________________________________\n";
        System.out.println("Hello from\n" + logo);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String command = br.readLine();
        String keyWord;
        ArrayList<Task> list = new ArrayList<>();
        while (!command.equals("bye")) {
            keyWord = command.split(" ")[0];
            switch(keyWord) {
                case "list": {
                    System.out.println("    ____________________________________________________________\n" +
                            "     Here are the tasks in your list:");
                    for (int i = 0; i < list.size(); i++) {
                        System.out.printf("     %d. %s%n", i + 1, list.get(i).toString());
                    }
                    System.out.println("    ____________________________________________________________");
                    command = br.readLine();
                    break;
                }
                case "mark": {
                    int taskNo = Integer.parseInt(command.split(" ")[1]) - 1;
                    list.get(taskNo).markDone();
                    System.out.println("    ____________________________________________________________\n" +
                            "     Nice! I've marked this task as done: ");
                    System.out.printf("       %s%n",list.get(taskNo).toString());
                    System.out.println("    ____________________________________________________________");
                    command = br.readLine();
                    break;
                }
                case "unmark": {
                    int taskNo = Integer.parseInt(command.split(" ")[1]) - 1;
                    list.get(taskNo).markUndone();
                    System.out.println("    ____________________________________________________________\n" +
                            "     OK, I've marked this task as not done yet:");
                    System.out.printf("       %s%n",list.get(taskNo).toString());
                    System.out.println("    ____________________________________________________________");
                    command = br.readLine();
                    break;
                }
                default: {
                    list.add(new Task(command));
                    System.out.println("    ____________________________________________________________\n" +
                            "     added: " + list.get(list.size()-1).toString() + "\n" +
                            "    ____________________________________________________________");
                    command = br.readLine();
                    break;
                }
            }
        }
        System.out.println("    ____________________________________________________________\n" +
                "     Bye. Hope to see you again soon!\n" +
                "    ____________________________________________________________");


    }
}

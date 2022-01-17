import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
//    Task tasks[] = new Task[100];
static ArrayList<Task> tasks = new ArrayList<Task>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String logo = " ____  _____ ____  ____ __  __\n"
                + "|  _ \\|  _  |  _ \\|  _ \\\\ \\/ /\n"
                + "| |_| | | | | |_| | |_| |\\  /\n"
                + "| |_| | |_| | |_| | |_| ||  |\n"
                + "|____/|_____|____/|____/ |__|\n";
        System.out.println("Hello from\n" + logo);
        String dash = "    ____________________________________\n";
        System.out.println(dash + "    Hello! I\'m Bobby\n    What can I do for you?\n" + dash);

         while (true) {
             String input = sc.nextLine();
             switch(input) {
                 case "bye":
                     System.out.println(dash + "    Bye! Hope to see you again soon!\n" + dash);
                     return;
                 case "list" :
                     System.out.println(dash);
                     for (int i = 0; i < Duke.tasks.size(); i++) {
                         int index = i+1;
                         System.out.println("    " + index + ". " + Duke.tasks.get(i).taskName);
                     }
                     System.out.println(dash);
                     break;
                 default:
//                     System.out.println(dash + "    " + input + "\n" + dash);
                     Task newTask = new Task(input);
                     tasks.add(newTask);
                     System.out.println(dash + "    " + "added: " + input + "\n" + dash);
             }

        }
    }
    public static class Task {
        public String taskName;
        public boolean done;

        public Task(String taskName) {
            this.taskName = taskName;
            this.done = false;
        }

        public void markDone(Task task) {
            task.done = true;
        }

        public void unmarkDone(Task task) {
            this.done = false;
        }
    }

}

import java.util.ArrayList;
import java.util.Scanner;

public class Bobby {
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
             String inputs[] = input.split(" ");
             switch(inputs[0]) {
                 case "bye":
                     System.out.println(dash + "    Bye! Hope to see you again soon!\n" + dash);
                     return;
                 case "list" :
                     System.out.println(dash);
                     Task currTask;
                     for (int i = 0; i < Bobby.tasks.size(); i++) {
                         currTask = Bobby.tasks.get(i);
                         int index = i+1;
                         System.out.println("    " + index + "." + currTask.getStatus() + currTask.taskName);
                     }
                     System.out.println(dash);
                     break;
                 case "mark":
                     Task doneTask = Bobby.tasks.get(Integer.parseInt(inputs[1])-1);
                     doneTask.markDone();
                     System.out.println(dash + "    " + "Nice! I've marked this task as done: ");
                     System.out.println("      [" + doneTask.getStatus() + doneTask.taskName);
                     System.out.println(dash);
                     break;
                 case "unmark":
                     Task undoneTask = Bobby.tasks.get(Integer.parseInt(inputs[1])-1);
                     undoneTask.unmarkDone();
                     System.out.println(dash + "    " + "OK! I've marked this task as not done yet: ");
                     System.out.println("      " + undoneTask.getStatus() + undoneTask.taskName);
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

        public void markDone() {
            this.done = true;
        }

        public void unmarkDone() {
            this.done = false;
        }

        public String getStatus() {
            return done ? "[X] " : "[ ] ";
        }
    }

}

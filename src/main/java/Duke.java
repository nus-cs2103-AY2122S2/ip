import main.java.Task;
import java.util.Scanner;

public class Duke {

    private static int level = 2;
    private Task task_arr[];
    private int curr_task;

    public Duke() {
        this.task_arr = new Task[100];
        this.curr_task = 0;
    }

    public void run() {
        welcome();
        Scanner sc = new Scanner(System.in);
        boolean end = false;
        while (!end) {
            String user_command = sc.nextLine();
            if (user_command.equals("bye")){
                exit();
                end = true;
            } else {
                handle(user_command);
            }
        }
    }

    public void handle(String user_command) {
        String words[] = user_command.split(" ", 2);
        horizontal();
        if (user_command.equals("list")) {
            if (curr_task == 0) {
                System.out.println("No tasks added yet!");
            } else {
                for (int i = 0; i < curr_task; i++) {
                    System.out.println(i+1 + "." + "[" + task_arr[i].getStatusIcon() + "] " + task_arr[i].getDescription());
                }
            }
        } else if (words[0].equals("mark")){
            System.out.println("Cool! You seemed to have been productive just like me! I've marked this task as done: ");
            task_arr[Integer.parseInt(words[1]) - 1].markDone();
            System.out.println( "[" + task_arr[Integer.parseInt(words[1]) - 1].getStatusIcon() + "] " + task_arr[Integer.parseInt(words[1]) - 1].getDescription());
        } else if (words[0].equals("unmark")){
            System.out.println("Did you mess up something? Fine... I'll mark it as undone heh but I believe you can do it!: ");
            task_arr[Integer.parseInt(words[1]) - 1].markUndone();
            System.out.println( "[" + task_arr[Integer.parseInt(words[1]) - 1].getStatusIcon() + "] " + task_arr[Integer.parseInt(words[1]) - 1].getDescription());
        } else {
            task_arr[curr_task] = new Task(user_command);
            System.out.println("added new task: "+ user_command);
            curr_task++;
            }
        horizontal();
    }

    public void exit() {
        horizontal();
        System.out.println("Bye nerd. Glad you'll be interacting with a real life human now! Haha...");
        horizontal();
    }

    public void horizontal() {
        System.out.println("-------------------------Level " + level + "-------------------------");
    }

    public void welcome(){
        horizontal();
        System.out.println("Greetings, NERD! I'm Duke");
        System.out.println("Fine, I'm programmed to be nice today. What can I do for you? :)");
        horizontal();
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}

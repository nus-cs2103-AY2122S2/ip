import java.util.Scanner;

public class Duke {

    private static int level = 2;
    private String task_arr[];
    private int curr_task;

    public Duke() {
        this.task_arr = new String[100];
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
        horizontal();
        if (user_command.equals("list")) {
            if (curr_task == 0) {
                System.out.println("No tasks added yet!");
            } else {
                for (int i = 0; i < curr_task; i++) {
                    System.out.println(i+1 + ". " + task_arr[i]);
                }
            }
        } else {
            task_arr[curr_task] = user_command;
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

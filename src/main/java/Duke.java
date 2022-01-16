import main.java.Task;
import java.util.Scanner;

public class Duke {

    private static int level = 3;
    private Task taskArr[];
    private int currTask;

    public Duke() {
        this.taskArr = new Task[100];
        this.currTask = 0;
    }

    public void run() {
        welcome();
        Scanner sc = new Scanner(System.in);
        boolean end = false;
        while (!end) {
            String userCommand = sc.nextLine();
            if (userCommand.equals("bye")){
                exit();
                end = true;
            } else {
                handle(userCommand);
            }
        }
    }

    public void handle(String userCommand) {
        String words[] = userCommand.split(" ", 2);
        horizontal();
        if (userCommand.equals("list")) {
            list();
        } else if (words[0].equals("mark")){
            mark(Integer.parseInt(words[1]) - 1);
        } else if (words[0].equals("unmark")){
            unmark(Integer.parseInt(words[1]) - 1);
        } else {
            addTask(userCommand);
        }
        horizontal();
    }

    public void list() {
        if (currTask == 0) {
            System.out.println("No tasks added yet!");
        } else {
            for (int i = 0; i < currTask; i++) {
                System.out.println(i+1 + "." + "[" + taskArr[i].getStatusIcon() + "] " + taskArr[i].getDescription());
            }
        }
    }

    public void mark(int num) {
        System.out.println("Cool! You seemed to have been productive just like me! I've marked this task as done: ");
        taskArr[num].markDone();
        System.out.println( "[" + taskArr[num].getStatusIcon() + "] " + taskArr[num].getDescription());
    }

    public void unmark(int num){
        System.out.println("Did you mess up something? Fine... I'll mark it as undone -- but I believe you can do it!: ");
        taskArr[num].markUndone();
        System.out.println( "[" + taskArr[num].getStatusIcon() + "] " + taskArr[num].getDescription());
    }
    public void exit() {
        horizontal();
        System.out.println("Bye nerd. Glad you'll be interacting with a real life human now! Haha...");
        horizontal();
    }

    public void addTask(String userCommand) {
        taskArr[currTask] = new Task(userCommand);
        System.out.println("added new task: "+ userCommand);
        currTask++;
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

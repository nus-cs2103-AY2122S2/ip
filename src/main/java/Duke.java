import main.java.Event;
import main.java.Task;
import java.util.Scanner;
import main.java.ToDo;
import main.java.Deadline;
import main.java.Event;

public class Duke {

    private static int level = 4;
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
        String command = words[0];
        horizontal();
        if (userCommand.equals("list")) {
            list();
        } else if (command.equals("mark")){
            mark(Integer.parseInt(words[1]) - 1);
        } else if (command.equals("unmark")){
            unmark(Integer.parseInt(words[1]) - 1);
        } else if (command.equals("todo") || command.equals("deadline") || command.equals("event")) {
            addTask(words[1], command);
        } else {
            System.out.println("Idk what you mean!");
        }
        horizontal();
    }

    public void list() {
        if (currTask == 0) {
            System.out.println("No tasks added yet!");
        } else {
            for (int i = 0; i < currTask; i++) {
                System.out.println(i+1 + ". " + taskArr[i].toString());
            }
        }
    }

    public void mark(int num) {
        taskArr[num].markDone();
        System.out.println("Cool! You seemed to have been productive just like me! I've marked this task as done:");
        System.out.println(taskArr[num].toString());
    }

    public void unmark(int num){
        System.out.println("Did you mess up something? Fine... I'll mark it as undone -- but I believe you can do it!:");
        taskArr[num].markUndone();
        System.out.println(taskArr[num].toString());
    }
    public void exit() {
        horizontal();
        System.out.println("Bye nerd. Glad you'll be interacting with a real life human now! Haha...");
        horizontal();
    }

    public void addTask(String userCommand, String type) {
        if (type.equals("todo")) {
            taskArr[currTask] = new ToDo(userCommand);
        } else if (type.equals("deadline")) {
            String words[] = userCommand.split(" /by ", 2);
            taskArr[currTask] = new Deadline(words[0], words[1]);
        } else {
            String words[] = userCommand.split(" /at ", 2);
            taskArr[currTask] = new Event(words[0], words[1]);
        }

        System.out.println("Roger, I got you. I've added this task:");
        System.out.println(taskArr[currTask].toString());
        currTask++;
        System.out.println("Now you have " + currTask + " tasks in the list.");

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

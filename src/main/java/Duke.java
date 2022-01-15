import java.util.Scanner;

public class Duke {

    private static int level = 1;

    public Duke() {}

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
                echo(user_command);
            }
        }
    }

    public void echo(String user_command) {
        horizontal();
        System.out.println(user_command);
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

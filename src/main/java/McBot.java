import java.util.Scanner;

public class McBot {
    public static void main(String[] args) {
            String logo = "\n" +
                    "___  ___    ______       _    \n" +
                    "|  \\/  |    | ___ \\     | |   \n" +
                    "| .  . | ___| |_/ / ___ | |_  \n" +
                    "| |\\/| |/ __| ___ \\/ _ \\| __| \n" +
                    "| |  | | (__| |_/ / (_) | |_  \n" +
                    "\\_|  |_/\\___\\____/ \\___/ \\__| \n" +
                    "                              \n" +
                    "                              \n";
        System.out.println("Ahoy! Me name be" + logo);

        Scanner sc = new Scanner(System.in);
        boolean running = true;

        while(running) {
            String command = sc.nextLine();
            switch(command) {
                case "bye":
                    System.out.println("Arghh! This ain't the last time ye see me -beep-");
                    running = false;
                    break;
                case "list":
                    System.out.println("list");
                    break;
                case "blah":
                    System.out.println("blah");
                    break;
                default:
            }
        }
    }
}

import java.util.ArrayList;
import java.util.Scanner;

public class McBot {
    public static void main(String[] args) {
            String frameLine = "==========================================";
            String logo = "\n" +
                    "___  ___    ______       _    \n" +
                    "|  \\/  |    | ___ \\     | |   \n" +
                    "| .  . | ___| |_/ / ___ | |_  \n" +
                    "| |\\/| |/ __| ___ \\/ _ \\| __| \n" +
                    "| |  | | (__| |_/ / (_) | |_  \n" +
                    "\\_|  |_/\\___\\____/ \\___/ \\__| \n" +
                    "                              \n" +
                    "                              \n";
        System.out.println(logo);
        System.out.println(frameLine);
        System.out.println("Ahoy! Me name be McBot.\nTell me lad, what do you want?");
        System.out.println(frameLine);

        Scanner sc = new Scanner(System.in);
        boolean running = true;
        ArrayList<String> arrList = new ArrayList<>(100);

        while(running) {
            String command = sc.nextLine();
            switch(command) {
                case "bye":
                    System.out.println(frameLine);
                    System.out.println("Arghh! This ain't the last time ye see me lad");
                    System.out.println(frameLine);
                    running = false;
                    break;
                case "list":
                    int i = 1;
                    System.out.println(frameLine);
                    for (String task : arrList) {
                        System.out.print(i + ". ");
                        System.out.println(task);
                        i++;
                    }
                    System.out.println(frameLine);
                    break;
                default:
                    arrList.add(command);
                    System.out.println(frameLine);
                    System.out.println("added: " + command);
                    System.out.println(frameLine);
            }
        }
    }
}

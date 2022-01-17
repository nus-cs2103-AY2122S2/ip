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
        ArrayList<Task> arrList = new ArrayList<>(100);

        while(running) {
            String fullCommand = sc.nextLine();
            String[] command = fullCommand.split(" ");
            switch(command[0]) {
                case "bye": {
                    System.out.println(frameLine);
                    System.out.println("Arghh! This ain't the last time ye see me lad");
                    System.out.println(frameLine);
                    running = false;
                    break;
                }
                case "list": {
                    int i = 1;
                    System.out.println(frameLine);
                    System.out.println("Here are yer tasks boi: ");
                    for (Task task : arrList) {
                        System.out.print(i + ".[" + task.getStatusIcon() + "] ");
                        System.out.println(task.getTaskName());
                        i++;
                    }
                    System.out.println(frameLine);
                    break;
                }
                case "mark": {
                    int num = Integer.parseInt(command[1]);
                    Task t = arrList.get(num - 1);
                    t.markDone();
                    System.out.println(frameLine);
                    System.out.println("Aye I'ave marked it done:");
                    System.out.println("[" + t.getStatusIcon() + "] " + t.getTaskName());
                    System.out.println(frameLine);
                    break;
                }
                case "unmark": {
                    int num = Integer.parseInt(command[1]);
                    Task t = arrList.get(num - 1);
                    t.undoDone();
                    System.out.println(frameLine);
                    System.out.println("Aye I'ave unmarked it:");
                    System.out.println("[" + t.getStatusIcon() + "] " + t.getTaskName());
                    System.out.println(frameLine);
                    break;
                }
                default: {
                    Task t = new Task(fullCommand);
                    arrList.add(t);
                    System.out.println(frameLine);
                    System.out.println("aye, I added " + fullCommand);
                    System.out.println(frameLine);
                }
            }
        }
    }
}

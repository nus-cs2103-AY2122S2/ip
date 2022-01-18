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
            String[] command = fullCommand.split(" ", 2);
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
                        System.out.print(i + ".[" + task.getTaskIcon() + "]");
                        System.out.print("[" + task.getStatusIcon() + "] ");
                        System.out.println(task.getFullDetails());
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
                    System.out.println("[" + t.getTaskIcon() + "][" + t.getStatusIcon() + "] " + t.getFullDetails());
                    System.out.println(frameLine);
                    break;
                }
                case "unmark": {
                    int num = Integer.parseInt(command[1]);
                    Task t = arrList.get(num - 1);
                    t.undoDone();
                    System.out.println(frameLine);
                    System.out.println("Aye I'ave unmarked it:");
                    System.out.println("[" + t.getTaskIcon() + "][" + t.getStatusIcon() + "] " + t.getFullDetails());
                    System.out.println(frameLine);
                    break;
                }
                case "todo": {
                    ToDo t = new ToDo(command[1]);
                    arrList.add(t);
                    System.out.println(frameLine);
                    System.out.println("Got 'em down as todo:");
                    System.out.println("[" + t.getTaskIcon() + "][" + t.getStatusIcon() + "] " + t.getFullDetails());
                    System.out.println("Ye now have " + arrList.size() + " tasks in list lad");
                    System.out.println(frameLine);
                    break;
                }
                case "deadline": {
                    String[] str = command[1].split("/by ");
                    String taskName = str[0];
                    String deadlineDate = str[1];
                    Deadline t = new Deadline(taskName, deadlineDate);
                    arrList.add(t);
                    System.out.println(frameLine);
                    System.out.println("Got 'em down as deadline:");
                    System.out.println("[" + t.getTaskIcon() + "][" + t.getStatusIcon() + "] " + t.getFullDetails());
                    System.out.println("Ye now have " + arrList.size() + " tasks in list lad");
                    System.out.println(frameLine);
                    break;
                }
                case "event": {
                    String[] str = command[1].split("/at ");
                    String taskName = str[0];
                    String eventDetails = str[1];
                    Event t = new Event(taskName, eventDetails);
                    arrList.add(t);
                    System.out.println("Got 'em down as event:");
                    System.out.println("[" + t.getTaskIcon() + "][" + t.getStatusIcon() + "] " + t.getFullDetails());
                    System.out.println("Ye now have " + arrList.size() + " tasks in list lad");
                    System.out.println(frameLine);
                    break;
                }
                default: {
                    System.out.println(frameLine);
                    System.out.println("What do ye mean boi?");
                    System.out.println(frameLine);
                }
            }
        }
    }
}

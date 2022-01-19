import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("A very good day to you master, I'm Blue the Genie");
        System.out.println("What do you wish for today? Your wish is my command");
        System.out.println("*-**-**-**-**-**-**-**-**-**-****-**-****-**-****-**");
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> storeList = new ArrayList<>();
        while (sc.hasNextLine()) {
            String command = sc.nextLine();
            if (command.equals("bye")) {
                System.out.println("Rub my lamp to summon me again");
                System.out.println("Good bye for now master");
                System.out.println("*-**-**-**-**-**-**-**-**-**-****-**-****-**-****-**");
                break;
            } else if (command.equals("list")) {
                int sizeOfList = storeList.size();
                System.out.println("Everything in my blue brain now: ");
                for (int i = 1; i <= sizeOfList; i++) {
                    Task t = storeList.get(i - 1);
                    System.out.println(i + "." + "[" + t.getStatusIcon() + "] " + t.getDescription());
                }
            } else if (command.startsWith("mark ")) {
                int taskNumber = Integer.parseInt(command.substring(5));
                Task tobeMark = storeList.get(taskNumber - 1);
                tobeMark.setMark();
                System.out.println("You could have gotten me to help you. This task has been marked done");
                System.out.println("[X] " + tobeMark.getDescription());

            } else if (command.startsWith("unmark ")) {
                int taskNumber1 = Integer.parseInt(command.substring(7));
                Task tobeUnmark = storeList.get(taskNumber1 - 1);
                tobeUnmark.setUnmark();
                System.out.println("You probably need more genies to help you. This task has been marked as not done");
                System.out.println("[ ] " + tobeUnmark.getDescription());
            } else {
                storeList.add(new Task(command));
                System.out.println("stored in my blue brain: " + command);
                System.out.println("*-**-**-**-**-**-**-**-**-**-****-**-****-**-****-**");
            }
        }
        sc.close();

    }
}

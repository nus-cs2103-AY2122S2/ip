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
                System.out.println("Everything in my blue brain now:");
                for (int i = 1; i <= sizeOfList; i++) {
                    Task t = storeList.get(i - 1);
                    System.out.println(i + "." + t.toString());
                }
            } else if (command.startsWith("mark ")) {
                int taskNumber = Integer.parseInt(command.substring(5));
                Task tobeMark = storeList.get(taskNumber - 1);
                tobeMark.setMark();
                System.out.println("You could have gotten me to help you. This task has been marked done");
                System.out.println(tobeMark.toString());

            } else if (command.startsWith("unmark ")) {
                int taskNumber1 = Integer.parseInt(command.substring(7));
                Task tobeUnmark = storeList.get(taskNumber1 - 1);
                tobeUnmark.setUnmark();
                System.out.println("You probably need more genies to help you. This task has been marked as not done");
                System.out.println(tobeUnmark.toString());

            } else if (command.startsWith("deadline ")) {
                int slash = command.indexOf("/");
                String newtask = command.substring(9, slash - 1);
                String endtime = command.substring(slash + 1);
                Deadline d = new Deadline(newtask, endtime);
                storeList.add(d);
                System.out.println("Added to my brain master:");
                System.out.println(d.toString());
                System.out.println("Currently I have " + storeList.size() + " things in my brain");

            } else if (command.startsWith("todo ")) {
                String newtask = command.substring(5);
                Todo t = new Todo(newtask);
                storeList.add(t);
                System.out.println("Added to my brain master:");
                System.out.println(t.toString());
                System.out.println("Currently I have " + storeList.size() + " things in my brain");

            } else if (command.startsWith("event ")) {
                int slash = command.indexOf("/");
                String newtask = command.substring(6, slash - 1);
                String attime = command.substring(slash + 1);
                Event e = new Event(newtask, attime);
                storeList.add(e);
                System.out.println("Added to my brain master:");
                System.out.println(e.toString());
                System.out.println("Currently I have " + storeList.size() + " things in my brain");
            }
        }
        sc.close();

    }
}

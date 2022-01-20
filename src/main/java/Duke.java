import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    private static ArrayList<Task> list = new ArrayList<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String lineBreak = "-------------------------------\n";
        String welcomeMessage =  lineBreak
                                 + "Hello! I'm Duke\n"
                                 + "What can I do for you?\n"
                                 + lineBreak;
        String goodbyeMessage = "Bye. Hope to see you again soon!";

        System.out.println(welcomeMessage);

        while(true){
            String input = sc.nextLine().trim();

            // Ignores empty lines
            if(input.equals("")) {
                continue;
            }

            System.out.println(lineBreak);

            // Exits program
            if(input.equals("bye")){
                System.out.println(goodbyeMessage);
                System.out.println(lineBreak);
                break;
            }

            // List out list
            if(input.equals("list")){
                System.out.println("Here are the tasks in your list:");
                for(int i = 0; i < list.size(); i++){
                    System.out.println(String.valueOf(i + 1) + ". " + list.get(i));
                }
                System.out.println(lineBreak);
                continue;
            }

            // Check for keywords
            String[] strArr = input.split(" ",2);
            String[] result;
            String keyword = strArr[0];
            Task task = null;
            switch(keyword){
                case "mark":
                    System.out.println(mark(strArr[1]));
                    break;
                case "unmark":
                    System.out.println(unmark(strArr[1]));
                    break;
                case "todo":
                    task = new Todo((strArr[1]));
                    break;
                case "deadline":
                    result = strArr[1].split("/by", 2);
                    task = new Deadline(result[0],result[1]);
                    break;
                case "event":
                    result = strArr[1].split("/at", 2);
                    task = new Event(result[0],result[1]);
                    break;
                default:
                    break;
            }

            if(task != null){
                list.add(task);
                System.out.println(taskAddedMessage(task));
            }
            System.out.println(lineBreak);
        }
        sc.close();
    }

    private static String mark(String input){
        int index;

        // Try to convert String input as index in list
        try {
            index = Integer.parseInt(input) - 1;
        } catch (NumberFormatException e) {
            index = -1;
        }

        // Check out of bounds
        if(index < 0 || index >= list.size()) {
            return "This is an invalid index, please try again!";
        }
        Task task = list.get(index);
        task.mark();
        return "Nice! I've marked this task as done:\n" + task;
    }

    private static String unmark(String input){
        int index;

        // Try to convert String input as index in list
        try {
            index = Integer.parseInt(input) - 1;
        } catch (NumberFormatException e) {
            index = -1;
        }

        // Check out of bounds
        if(index < 0 || index >= list.size()) {
            return "This is an invalid index, please try again!";
        }
        Task task = list.get(index);
        task.unmark();
        return "OK! I've marked this task as not done yet:\n" + task;
    }

    private static String taskAddedMessage(Task task){
        return "Got it. I've added this task:\n"
                + task.toString()
                + "\nNow you have " + list.size() + " tasks in the list.";
    }
}

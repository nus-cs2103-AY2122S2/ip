import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    private static ArrayList<Task> list = new ArrayList<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String lineBreak = "★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★\n";
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
            String[] strArr = input.split(" ",3);
            String keyword = strArr[0];
            switch(keyword){
                case "mark":
                    System.out.println(mark(strArr[1]));
                    break;
                case "unmark":
                    System.out.println(unmark(strArr[1]));
                    break;
                default:
                    list.add(new Task(input));
                    System.out.println("added: " + input);
                    break;
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
}

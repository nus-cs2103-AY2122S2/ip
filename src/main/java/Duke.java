import java.util.*;  

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ArrayList<String> taskArray = new ArrayList<String>();

        String beginning = "Hello! I'm Duke\n" +
                           "What can I do for you?";
        System.out.println(beginning);

        String input = sc.nextLine();

        while (!input.equals("bye")) {

            if (input.equals("list")) {

                if (taskArray.isEmpty()) {
                    System.out.println("List is empty");
                } else {
                    for (int i = 0; i < taskArray.size(); i++) {
                        String index = String.valueOf(i + 1);
                        System.out.println( index + ". " + taskArray.get(i));
                    }
                }
            } else {
                ChatBot chatBot = new ChatBot(input);
                taskArray.add(input);
                System.out.println(chatBot.toString());
            }
            input = sc.nextLine();
        }

        String ending = "Bye. Hope to see you again soon!";

        System.out.print(ending);

        sc.close();
    }
}

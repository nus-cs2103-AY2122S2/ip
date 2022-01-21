import java.util.*;  

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String beginning = "Hello! I'm Duke\n" +
                           "What can I do for you?";
        System.out.println(beginning);

        String input = sc.nextLine();

        while (!input.equals("bye")) {
            ChatBot chatBot = new ChatBot(input);
            System.out.println(chatBot.toString());
            input = sc.nextLine();
        }

        String ending = "Bye. Hope to see you again soon!";

        System.out.print(ending);

        sc.close();
    }
}

import java.util.*;  

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ArrayList<Task> taskArray = new ArrayList<Task>();

        String beginning = "Hello! I'm Duke\n" +
                           "What can I do for you?";
        System.out.println(beginning);

        String input = sc.nextLine();

        while (!input.equals("bye")) {
            String[] checkMarking = input.split(" ");
            
            if (input.equals("list")) {
                if (taskArray.isEmpty()) {
                    System.out.println("List is empty");
                } else {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < taskArray.size(); i++) {
                        String index = String.valueOf(i + 1);
                        System.out.println(index + "." + taskArray.get(i));
                    }
                }
            } else if (checkMarking[0].equals("mark") || checkMarking[0].equals("unmark")) { //this is to check whether it goes through marking
                int index = Integer.parseInt(checkMarking[1]) - 1;
                Task tasks = taskArray.get(index);
                System.out.println(tasks.marking(checkMarking[0]));
                taskArray.set(index, tasks);
            } else {
                ChatBot chatBot = new ChatBot(input);
                Task tasks = new Task(input);
                taskArray.add(tasks);
                System.out.println(chatBot.toString());
            }
            input = sc.nextLine();
        }

        String ending = "Bye. Hope to see you again soon!";

        System.out.print(ending);

        sc.close();
    }
}





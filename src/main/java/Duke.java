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

            String[] checkType = input.split(" ");
            String messageTask = "Got it. I've added this task: \n";
            
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
            } else if (checkType[0].equals("mark") || checkType[0].equals("unmark")) { //this is to check whether it goes through marking
                int index = Integer.parseInt(checkType[1]) - 1;
                Task tasks = taskArray.get(index);
                System.out.println(tasks.marking(checkType[0]));
                taskArray.set(index, tasks);
            } else if (checkType[0].equals("todo")) { //check todo
                String stringSliced = input.substring(5,input.length());
                Todo todoTask = new Todo(stringSliced);
                taskArray.add(todoTask);
                String noOfTask = String.valueOf(taskArray.size());
                System.out.println(messageTask + todoTask.toString() + "\n"
                                   + "Now you have " + noOfTask + " tasks in the list.");

            } else if (checkType[0].equals("deadline")) { //check deadline
                String deadlineCondition = "/by "; 
                int indexOfTime = input.indexOf(deadlineCondition); //to find /
                String dateTime = input.substring(indexOfTime + deadlineCondition.length(), input.length()); // the date and time for by
                String stringSliced = input.substring(9, indexOfTime); // after deadline
                Deadline deadlineTask = new Deadline(stringSliced, dateTime);
                taskArray.add(deadlineTask);
                String noOfTask = String.valueOf(taskArray.size());
                System.out.println(messageTask + deadlineTask.toString() + "\n"
                                    + "Now you have " + noOfTask + " tasks in the list.");
            } else if (checkType[0].equals("event")) { // check event
                String eventCondition = "/at "; 
                int indexOfTime = input.indexOf(eventCondition); //to find /
                String dateTime = input.substring(indexOfTime + eventCondition.length(), input.length()); // the date and time for by
                String stringSliced = input.substring(6, indexOfTime); // after deadline
                Event eventTask = new Event(stringSliced, dateTime);
                taskArray.add(eventTask);
                String noOfTask = String.valueOf(taskArray.size());
                System.out.println(messageTask + eventTask.toString() + "\n"
                                    + "Now you have " + noOfTask + " tasks in the list.");
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





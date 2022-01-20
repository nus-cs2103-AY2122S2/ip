import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static boolean findKeyword(String[] nextLineArr, String keyword){
        for(int i = 0; i < nextLineArr.length; i++) {
            if(nextLineArr[i].equals(keyword)) {
                return true;
            }
        }
        return false;
    }
    public static void main(String[] args) {
        String greeting = "Hello! I'm Duke\n" + "What can I do for you?";
        System.out.println(greeting);

        ArrayList<Task> taskList = new ArrayList<>();


        Scanner scanner = new Scanner(System.in);
        String input = null;
        String bye = "bye";



        while(scanner.hasNextLine()) {
            input = scanner.nextLine();
            String[] strArr = input.split(" ");
            String command = strArr[0];
            if(command.equals(bye)) {
                System.out.println("Bye. Hope to see you again soon!");
                return;
            }
            if(command.equals("list")){
                System.out.println("Here are the tasks in your list:");
                for(int i = 1; i <= taskList.size(); i++){
                    int index = i - 1;
                    System.out.println(i + ". " +taskList.get(index).toString());
                }
                continue;
            }
            if(command.equals("mark")){
                int index_andone =  Integer.parseInt(strArr[1]);
                Task temp = taskList.get(index_andone - 1);
                temp.isDone = true;
                System.out.println("Nice! I've marked this task as done:\n"+ temp.toString());
                continue;
            }

            if(command.equals("todo")) {
                String title = input.substring("todo".length() + 1).trim();
                System.out.println("Got it. I've added this task:");
                Task task = new Todo(title);
                taskList.add(task);
                System.out.println("   " + task.toString() + "\n"+ "Now you have " + taskList.size() + " tasks in the list.");
            }

            if(command.equals("deadline")) {
                if(findKeyword(strArr, "/by")) {
                    String[] splitArr = input.split("/by", 2);
                    String title = splitArr[0].substring("deadline".length() + 1).trim();
                    String time = splitArr[1].trim();
                    System.out.println("Got it. I've added this task:");
                    Task task = new Deadline(title, time);
                    taskList.add(task);
                    System.out.println("   " + task.toString() + "\n"+ "Now you have " + taskList.size() + " tasks in the list.");
                }
            }

            if(command.equals("event")) {
                if(findKeyword(strArr, "/at")) {
                    String[] splitArr = input.split("/at", 2);
                    String title = splitArr[0].substring("event".length() + 1).trim();
                    String time = splitArr[1].trim();
                    System.out.println("Got it. I've added this task:");
                    Task task = new Event(title, time);
                    taskList.add(task);
                    System.out.println("   " + task.toString() + "\n"+  "Now you have " + taskList.size() + "tasks in the list.");
                }
            }


        }
    }
}

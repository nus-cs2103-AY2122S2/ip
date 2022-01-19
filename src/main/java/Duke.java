import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
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
                for(int i = 1; i <= taskList.size(); i++){
                    int index = i - 1;
                    System.out.println(i + ". " + taskList.get(index).getStatusIcon()+  taskList.get(index).toString());
                }
                continue;
            }
            if(command.equals("mark")){
                int index_andone =  Integer.parseInt(strArr[1]);
                Task temp = taskList.get(index_andone - 1);
                temp.isDone = true;
                System.out.println("Nice! I've marked this task as done: \n" + temp.getStatusIcon() + temp.toString());
                continue;
            }

            Task task = new Task(input);
            taskList.add(task);
            System.out.println("added: " + input);
        }
    }
}

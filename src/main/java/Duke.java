import java.util.*;

public class Duke {

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can i do for you?");
        Scanner sc = new Scanner(System.in);
        Task[] taskList = new Task[100];
        int taskNum = 0;
        boolean isBye = false;
        while(!isBye) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye. I don't want to see you anytime soon! :)");
                isBye = true;
            } else if (input.equals("list")) {
                listAllTask(taskList, taskNum);
            } else if (input.startsWith("mark") || input.startsWith("unmark")){
                markTask(input, taskList);
            } else if (input.startsWith("todo")){
                taskList[taskNum] = new ToDo(input, taskNum);
                System.out.println("Got it. I've added this task: ");
                System.out.printf(" [T][%s] %s\n", taskList[taskNum].getStatus(),  taskList[taskNum].name);
                taskNum++;
                System.out.printf("Now you have %d task on the list\n", taskNum);
            } else if (input.startsWith("deadline")){
                taskList[taskNum] = new Deadline(input, taskNum);
                System.out.println("Got it. I've added this task: ");
                System.out.printf(" [D][%s] %s (by: %s) \n", taskList[taskNum].getStatus(),  taskList[taskNum].name, taskList[taskNum].deadline);
                taskNum++;
                System.out.printf("Now you have %d task on the list\n", taskNum);
            } else if (input.startsWith("event")){
                taskList[taskNum] = new Event(input, taskNum);
                System.out.println("Got it. I've added this task: ");
                System.out.printf(" [E][%s] %s (on: %s) \n", taskList[taskNum].getStatus(),  taskList[taskNum].name, taskList[taskNum].deadline);
                taskNum++;
                System.out.printf("Now you have %d task on the list\n", taskNum);
            } else {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
    }

    public static void markTask(String input, Task[] taskList){
        String[] inputArr = input.split(" ");
        int taskNum = Integer.parseInt(inputArr[1]) - 1;
        if (input.startsWith("mark")){
            taskList[taskNum].mark();
            System.out.println("Nice! I've marked this task as done: ");
            System.out.printf("[%s] %s\n", taskList[taskNum].getStatus(), taskList[taskNum].name);
        } else {
            taskList[taskNum].unmark();
            System.out.println("OK, I've marked this task as not done yet: ");
            System.out.printf("[%s] %s\n", taskList[taskNum].getStatus(), taskList[taskNum].name);
        }
    }

    public static void listAllTask(Task[] taskList, int taskNum){
        System.out.printf("Here are the tasks in your list:\n");
        for(int i=0; i<taskNum && taskNum!= 0; i++){
            System.out.print(taskList[i].toString());
        }
        System.out.println("__________________________________________________________________");
    }

}

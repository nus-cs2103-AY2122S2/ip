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
            } else if (input.startsWith("mark ") || input.startsWith("unmark ")){
                markTask(input, taskList);
            } else {
                taskList[taskNum] = new Task(input, taskNum);
                System.out.println("added: " + input);
                taskNum++;
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
        for(int i=0; i<taskNum && taskNum!= 0; i++){
            System.out.print(taskList[i].toString());
        }
        System.out.println("__________________________________________________________________");
    }

}

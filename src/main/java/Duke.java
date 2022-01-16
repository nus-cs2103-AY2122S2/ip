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
        boolean isBye = false;
        while(!isBye) {
            try {
                String input = sc.nextLine();
                if (input.equals("bye")) {
                    System.out.println("Bye. I don't want to see you anytime soon! :)");
                    isBye = true;
                } else if (input.equals("list")) {
                    listAllTask(taskList, Task.totalTask);
                } else if (input.startsWith("mark") || input.startsWith("unmark")) {
                    markTask(input, taskList);
                } else if (input.startsWith("todo")) {
                    taskList[Task.totalTask] = new ToDo(input, Task.totalTask);
                } else if (input.startsWith("deadline") || input.startsWith("event")) {
                    if (input.startsWith("deadline")) {
                        String[] inputArr = input.split("/by ");
                        if (inputArr.length == 1){
                            throw new EmptyDescriptorExceptions();
                        }
                        taskList[Task.totalTask] = new Deadline(inputArr[0].substring(8, inputArr[0].length()), Task.totalTask, inputArr[1]);
                    } else {
                        String[] inputArr = input.split("/at ");
                        if (inputArr.length == 1){
                            throw new EmptyDescriptorExceptions();
                        }
                        taskList[Task.totalTask] = new Event(inputArr[0].substring(5, inputArr[0].length()), Task.totalTask, inputArr[1]);
                    }
                } else {
                    System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            }
            catch (EmptyDescriptorExceptions e){
                System.out.println("  ☹ OOPS!!! The description of a task cannot be empty.");
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

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
        while(!isBye){
            String input = sc.nextLine();
            if (input.equals("bye")){
                System.out.println("Bye. I don't want to see you anytime soon! :)");
                isBye = true;
            } else if (input.equals("list")){
                listAllTask(taskList, taskNum);
            } else {
                taskList[taskNum] = new Task(input, taskNum);
                System.out.println("added: " + input);
                taskNum++;
            }
        }
    }
    public static void listAllTask(Task[] taskList, int taskNum){
        for(int i=0; i<taskNum; i++){
            System.out.printf("%d. %s \n", i+1, taskList[i].name);
        }
        System.out.println("__________________________________________________________________");
    }

}

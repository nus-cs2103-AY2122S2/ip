import java.util.Scanner;

public class Duke {

    private static Task taskList[] = new Task[100];
    private static int numOfTask = 0;

    public static void addToList(Task t){
        taskList[numOfTask] = t;
        numOfTask++;
        System.out.println("added: " + t.getDescription());
    }

    public static void markTask(int taskNum) {
        String message = "Nice! I've marked this task as done: " ;
        int actualTaskNum = taskNum - 1; //minus 1 as array index is from 0
        Task t = taskList[actualTaskNum]; // get the task from the array
        t.setTaskDone();
        System.out.println(message + "\n" + "[" + t.getStatusIcon() + "] " + t.getDescription());
    }

    public static void unMarkTask(int taskNum) {
        String message = "OK, I've marked this task as not done yet: ";
        int actualTaskNum = taskNum - 1;
        Task t = taskList[actualTaskNum]; // get the task from the array
        t.setTaskNotDone();
        System.out.println(message + "\n" + "[" + t.getStatusIcon() + "] " + t.getDescription());
    }

    public static void printList(){
        String message = "Here are the tasks in your list:";
        System.out.println(message);

        for(int i = 0; i < numOfTask; i++){
            String output = i + 1 + "." + "[" + taskList[i].getStatusIcon() + "] " + taskList[i].getDescription();
            System.out.println(output);
        }
    }

    public static void main(String[] args) {
        String greeting = " Hello! I'm TaskJamie \n What can i do for you?";
        String ending =  " Bye. Hope to see you again soon!";
        System.out.println(greeting);

        Scanner sc = new Scanner(System.in);
        while(true) {
            String input = sc.nextLine();
            String[] inputSplit = input.split(" "); //split input by space

            if(inputSplit[0].equals("bye")){
                break;
            } else if(inputSplit[0].equals("list")) {
                printList();

            } else if(inputSplit[0].equals("mark")){
                markTask(Integer.parseInt(inputSplit[1]));

            } else if(inputSplit[0].equals("unmark")) {
                unMarkTask(Integer.parseInt(inputSplit[1]));

            } else{
                addToList(new Task(input));
            }
        }
        System.out.println(ending);
    }
}

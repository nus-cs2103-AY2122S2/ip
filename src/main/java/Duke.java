import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Howdy and welcome to\n" + logo + "\n" + "Feel free to tell duke any tasks you'd like!");

        //Initialise objects needed
        String inputData;
        ArrayList<Task> list = new ArrayList<>();
        Scanner scan = new Scanner(System.in);

        while(true) {
            //Take in input
            inputData = scan.nextLine();

            //Check if input == bye
            if(inputData.equals("bye")) {
                System.out.println("~BYE!~ Come back to Duke anytime");
                break;
            }

            //Check if input == list
            else if(inputData.equals("list")){
                for(int i = 1; i <= list.size(); i++){
                    Task task = list.get(i-1);
                    System.out.print(i + ": ");
                    task.printTask();
                }

            //Check if input == unmark
            } else if(inputData.contains("unmark")){
                //split string by space
                String[] splitted = inputData.split("\\s+");
                int index = Integer.parseInt(splitted[1]);
                if(index > list.size() || index <= 0){
                    System.out.println("Index out of bounds, please try again");
                } else {
                    list.get(index-1).setDone(false);
                    for (int i = 1; i <= list.size(); i++) {
                        Task task = list.get(i - 1);
                        System.out.print(i + ": ");
                        task.printTask();
                    }
                }
            //Check if input == mark
            } else if(inputData.contains("mark")){
                //split string by space
                String[] splitted = inputData.split("\\s+");
                int index = Integer.parseInt(splitted[1]);
                if(index > list.size() || index <= 0){
                    System.out.println("Index out of bounds, please try again");
                } else {
                    list.get(index-1).setDone(true);
                    for (int i = 1; i <= list.size(); i++) {
                        Task task = list.get(i - 1);
                        System.out.print(i + ": ");
                        task.printTask();
                    }
                }
            }
            //input is a new type of task
            else{
                //identify type of task
                String[] arr = inputData.split(" ", 2);
                String taskType = arr[0];
                String taskDetails = arr[1];

                Task newTask = new Task("");

                if(taskType.equals("todo")){
                    newTask = new Todo(inputData);
                } else if(taskType.equals("deadline")){
                    String[] spl=taskDetails.split("/");
                    String details=spl[0];
                    String date=spl[1];
                    newTask = new Deadline(details,date);
                } else if(taskType.equals("event")){
                    String[] spl=taskDetails.split("/");
                    String details=spl[0];
                    String date=spl[1];
                    newTask = new Event(details,date);
                }

                list.add(newTask);
                System.out.println("Got it. The task has been added:");
                newTask.printTask();
                System.out.println("Now you have " + list.size() + " tasks in the list.");
            }
        }
    }
}

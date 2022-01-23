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
        System.out.println("-----------------------------------");

        //Initialise objects needed
        String inputData;
        ArrayList<Task> list = new ArrayList<>();
        Scanner scan = new Scanner(System.in);

        while(true) {

            try{
                //Take in input
                inputData = scan.nextLine();

                //Check if input == bye
                if(inputData.equals("bye")) {
                    System.out.println("~BYE!~ Come back to Duke anytime");
                    break;
                }

                //Check if input == list
                else if(inputData.equals("list")){
                    printList(list);

                //Check if input == unmark
                } else if(inputData.contains("unmark")){

                    unmark(inputData,list);

                //Check if input == mark
                } else if(inputData.contains("mark")){
                    mark(inputData,list);
                }
                // check if input == delete
                else if(inputData.contains("delete")){
                    delete(inputData, list);
                }
                //input is a new type of task
                else if(inputData.contains("todo") || inputData.contains("event") || inputData.contains("deadline")) {
                    //identify type of task
                    String[] arr = inputData.split(" ", 2);
                    
                    if(arr.length < 2){
                        throw new DukeException("Description of task cannot be empty!");
                    }

                    String taskType = arr[0];
                    String taskDetails = arr[1];

                    Task newTask = new Task("");

                    if(taskType.equals("todo")){
                        newTask = new Todo(taskDetails);
                    } else if(taskType.equals("deadline")){
                        String[] spl=taskDetails.split("/");
                        if(spl.length < 2){
                            throw new DukeException("Description of deadline must include a date/time! Did you miss out a /by?");
                        }
                        String details=spl[0];
                        String date=spl[1];
                        newTask = new Deadline(details,date);
                    } else if(taskType.equals("event")){
                        String[] spl=taskDetails.split("/");
                        if(spl.length < 2){
                            throw new DukeException("Description of event must include a date/time! Did you miss out a /at?");
                        }
                        String details=spl[0];
                        String date=spl[1];
                        newTask = new Event(details,date);
                    }

                    list.add(newTask);
                    System.out.println("Got it. The task has been added:");
                    newTask.printTask();
                    System.out.println("Now you have " + list.size() + " tasks in the list.");
                } else {
                    throw new DukeException("no such task type");
                }
                System.out.println("-----------------------------------");

            } catch (DukeException e) {
                System.out.println(e);
                System.out.println("-----------------------------------");
            }
        } 

        scan.close();
    }

    //Abstracted methods
    public static void printList(ArrayList<Task> taskArrayList) {
        for(int i = 1; i <= taskArrayList.size(); i++){
            Task task = taskArrayList.get(i-1);
            System.out.print(i + ": ");
            task.printTask();
        }
    }
    public static void unmark(String input, ArrayList<Task> taskArrayList) {
        String[] splitted = input.split("\\s+");
        try {
            int index = Integer.parseInt(splitted[1]);
            if(index > taskArrayList.size() || index <= 0){
                System.out.println("Index out of bounds, please try again");
            } else {
                taskArrayList.get(index-1).setDone(false);
                printList(taskArrayList);
            }
        } catch (Exception e) {
            System.out.println("You have entered an invalid input");
        }
    }

    public static void mark(String input, ArrayList<Task> taskArrayList) {
        String[] splitted = input.split("\\s+");
        try {
            int index = Integer.parseInt(splitted[1]);
            if(index > taskArrayList.size() || index <= 0){
                System.out.println("Index out of bounds, please try again");
            } else {
                taskArrayList.get(index-1).setDone(true);
                printList(taskArrayList);
            }
        } catch (Exception e) {
            System.out.println("You have entered an invalid input");
        }
    }

    public static void delete(String input, ArrayList<Task> taskArrayList) {
        String[] splitted = input.split("\\s+");
        try {
            int index = Integer.parseInt(splitted[1]);
            if (index > taskArrayList.size() || index <= 0) {
                System.out.println("Index out of bounds, please try again");
            } else {
                taskArrayList.remove(index - 1);
                printList(taskArrayList);
            }
        } catch (Exception e) {
            System.out.println("You have entered an invalid input");
        }
    }
}

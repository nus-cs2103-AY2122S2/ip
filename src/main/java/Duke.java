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

        String inputData;
        ArrayList<Task> list = new ArrayList<>();
        Scanner scan = new Scanner(System.in);

        while(true) {
            inputData = scan.nextLine();
            if(inputData.equals("bye")) {
                System.out.println("~BYE!~ Come back to Duke anytime");
                break;
            }
            else if(inputData.equals("list")){
                for(int i = 1; i <= list.size(); i++){
                    Task task = list.get(i-1);
                    System.out.print(i + ": ");
                    task.printTask();
                }
            } else if(inputData.contains("unmark")){
                String[] splitted = inputData.split("\\s+");
                int index = Integer.parseInt(splitted[1]);
                if(index >= list.size()){
                    System.out.println("Index out of bounds, please try again");
                } else {
                    list.get(index).setDone(false);
                    for (int i = 1; i <= list.size(); i++) {
                        Task task = list.get(i - 1);
                        System.out.print(i + ": ");
                        task.printTask();
                    }
                }
            } else if(inputData.contains("mark")){
                String[] splitted = inputData.split("\\s+");
                int index = Integer.parseInt(splitted[1]);
                if(index >= list.size()){
                    System.out.println("Index out of bounds, please try again");
                } else {
                    list.get(index).setDone(true);
                    for (int i = 1; i <= list.size(); i++) {
                        Task task = list.get(i - 1);
                        System.out.print(i + ": ");
                        task.printTask();
                    }
                }
            }
            else{
                Task newTask = new Task(inputData);
                list.add(newTask);
                System.out.println("added: " + inputData);
            }
        }
    }
}

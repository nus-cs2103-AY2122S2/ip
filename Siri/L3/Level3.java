import java.util.Scanner;
import java.util.ArrayList;

public class Level3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> arrList = new ArrayList<Task>();
        String line = "    __________________________________________________\n"; //print a line before greeting message
        System.out.println(line + "     Hi there. I'm Siri \n" + "     How may I help you? \n" + line);

        while (true) { //will keep querying user for input until user inputs "bye" into system
            String word = sc.next();
            if (word.equals("bye")) { //print goodbye text
                System.out.println(line + "     Goodbye.\n" + line);
                break; //end while loop
            } else if (word.equals("list")) { //print all elements in a list with index in front
                System.out.print(line);
                int s = arrList.size();
                for (int i = 0; i < s; i++) {
                    String currTask = arrList.get(i).toString();
                    System.out.println("     " + (i + 1) + "." + currTask);
                }
                System.out.println(line);
            } else if (word.equals("mark")) {
                int num = sc.nextInt();
                int index = num - 1;
                Task t = arrList.get(index);
                t.markAsDone();
                arrList.set(index, t);
                System.out.print(line);
                int s = arrList.size();
                for (int i = 0; i < s; i++) {
                    String currTask = arrList.get(i).toString();
                    System.out.println("     " + (i + 1) + "." + currTask);
                }
                System.out.println(line);
            } else { //print whatever user inputed with "added" infront
                String taskToDo = word + sc.nextLine();
                Task t = new Task(taskToDo);
                arrList.add(t);
                System.out.println(line + "     added: " + word + "\n" + line);
            }
        }
    }
}

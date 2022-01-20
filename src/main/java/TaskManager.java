import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class TaskManager {

    protected ArrayList<Task> tasks;

    public TaskManager(){
        this.tasks = new ArrayList<Task>();
    }

    public void mainLogic() {

        String userInput = "";
        Scanner sc = new Scanner(System.in);

        while (!userInput.equals("bye")) {
            userInput = sc.nextLine();
            System.out.println("-------------------");
            StringTokenizer st = new StringTokenizer(userInput, " ");
            String curr = st.nextToken();

            switch (curr){

                case "list":
                    this.printList();
                    break;

                case "mark":
                    int toMark = Integer.parseInt(st.nextToken());
                    tasks.get(toMark - 1).markCompleted();
                    break;

                case "unmark":
                    int toUnmark = Integer.parseInt(st.nextToken());
                    tasks.get(toUnmark - 1).markNotCompleted();
                    break;

                case "todo":
                    this.addToDo(st.nextToken(""));
                    break;

                case "deadline":
                    userInput = userInput.replace(curr, "");
                    String[] spl=  userInput.split("/by ");
                    this.addDeadline(spl[0], spl[1]);
                    break;

                case "event":
                    userInput = userInput.replace(curr, "");
                    String[] splo = userInput.split("/at ");
                    this.addEvent(splo[0], splo[1]);
                    break;
            }

            System.out.println("-------------------");
        }
    }

    private void printList(){
        for (int x = 0; x < tasks.size(); x++) {
            System.out.println((x + 1) + ". " + tasks.get(x).toString());
        }
    }

    private void addToDo(String desc){
        ToDo curr = new ToDo(desc, false);
        this.tasks.add(curr);
        this.printTaskAddition(curr);
    }

    private void addDeadline(String desc, String date) {
        Deadline curr = new Deadline(desc, false, date);
        this.tasks.add(curr);
        this.printTaskAddition(curr);
    }

    private void addEvent(String desc, String date){
        Event curr = new Event(desc, false, date);
        this.tasks.add(curr);
        this.printTaskAddition(curr);
    }

    public void printTaskAddition(Task curr){
        System.out.println("Got it! I've added this task:");
        System.out.println(curr.toString());
        System.out.println("Now you have " + this.tasks.size() + " tasks in the list");
    }
}

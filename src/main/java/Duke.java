import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        printContent("Hello! I'm Duke\n     What can I do for you?");
        ArrayList<Task> tasks = new ArrayList<Task>();
        Scanner sc = new Scanner(System.in);
        while (true){
            String query = sc.nextLine();
            if (query.compareTo("bye") == 0){
                printContent("Bye. Hope to see you again soon!");
                break;
            } else if (query.compareTo("list") == 0){
                String list = "";
                for (int i = 0; i < tasks.size(); i++){
                    list += (i+1) + ". " + tasks.get(i).getTitle();
                    if (i != tasks.size()-1)
                        list += "\n     ";
                }
                printContent(list);
            } else{
                printContent("added: " + query);
                tasks.add(new Task(query));
            }
        }
        sc.close();
    }

    public static void printLine(){
        System.out.println("    ____________________________________________________________");
    }

    public static void printContent(String text){
        String spacing = "     ";
        printLine();
        System.out.println(spacing + text);
        printLine();
        System.out.println();
    }
}

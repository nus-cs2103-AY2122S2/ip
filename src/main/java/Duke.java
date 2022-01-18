import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        printContent("Hello! I'm Duke\n     What can I do for you?");
        Scanner sc = new Scanner(System.in);
        while (true){
            String query = sc.nextLine();
            if (query.compareTo("bye") == 0){
                printContent("Bye. Hope to see you again soon!");
                break;
            } else {
                printContent(query);
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

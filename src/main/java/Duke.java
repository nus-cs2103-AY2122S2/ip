import java.util.Scanner;

public class Duke {
    private static String taskList[] = new String[100];
    private static int numOfTask = 0;

    public static void addToList(String s){
        taskList[numOfTask] = s;
        numOfTask++;
        System.out.println("added: " + s);
    }

    public static void printList(){
        for(int i = 0; i < numOfTask; i++){
            String output = i + 1 + "." + " " + taskList[i];
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
            if(input.equals("bye")){
                break;
            } else if(input.equals("list")) {
                printList();
            } else {
                addToList(input);
            }
        }
        System.out.println(ending);
    }
}

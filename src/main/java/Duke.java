import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        String greeting = " Hello! I'm AskJamie_TaskVer \n What can i do for you?";
        String ending =  " Bye. Hope to see you again soon!";
        System.out.println(greeting);

        Scanner sc = new Scanner(System.in);
        while(true) {
            String input = sc.nextLine();
            if(input.equals("bye")){
                break;
            }
            System.out.println(input);
        }
        System.out.println(ending);
    }
}

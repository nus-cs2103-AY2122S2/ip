import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String lineBreak = "★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★\n";
        String welcomeMessage =  lineBreak
                                 + "Hello! I'm Duke\n"
                                 + "What can I do for you?\n"
                                 + lineBreak;
        String goodbyeMessage = "Bye. Hope to see you again soon!";
        System.out.println(welcomeMessage);
        while(true){
            String input = sc.nextLine();
            System.out.println(lineBreak);
            if(input.equals("bye")){
                System.out.println(goodbyeMessage);
                System.out.println(lineBreak);
                break;
            }

            // Echo user input
            System.out.println(input);
            System.out.println(lineBreak);
        }
        sc.close();
    }
}

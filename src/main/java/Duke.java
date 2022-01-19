import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<>();
        String lineBreak = "★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★\n";
        String welcomeMessage =  lineBreak
                                 + "Hello! I'm Duke\n"
                                 + "What can I do for you?\n"
                                 + lineBreak;
        String goodbyeMessage = "Bye. Hope to see you again soon!";

        System.out.println(welcomeMessage);

        while(true){
            String input = sc.nextLine();

            // ignores empty lines
            if(input.equals("")) {
                continue;
            }

            System.out.println(lineBreak);

            if(input.equals("bye")){
                System.out.println(goodbyeMessage);
                System.out.println(lineBreak);
                break;
            }

            // List out list
            if(input.equals("list")){
                for(int i = 0; i < list.size(); i++){
                    System.out.println(String.valueOf(i + 1) + ". " + list.get(i));
                }
                continue;
            }

            // add user input to  list
            list.add(input);

            // print message
            System.out.println("added: " + input);
            System.out.println(lineBreak);
        }
        sc.close();
    }
}

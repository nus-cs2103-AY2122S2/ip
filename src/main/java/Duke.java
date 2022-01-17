import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String greet = "Hello! I'm Duke\n" + "What can I do for you?\n";
        String goodbye = "Bye. Hope to see you again soon!";
        System.out.println(greet);
        while(true){
            Scanner sc = new Scanner(System.in);
            String curr_word = sc.next();
            if (curr_word.equals("bye")) {
                System.out.println(goodbye);
                break;
            } else{
                System.out.println(curr_word);
            }
        }
    }
}
import java.util.Scanner;
public class Johnny {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Johnny \n" + "What can I do for you?");
        Scanner sc = new Scanner(System.in);
        String input;
        while(true) {
            input = sc.nextLine();
            if(input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            System.out.println(input);
        }
    }
}

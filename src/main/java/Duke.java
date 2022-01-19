import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        System.out.println("Hello! I'm Duke!");
        System.out.println("What can I do for you?");

        String input = "";
        Scanner sc = new Scanner(System.in);

        while(!input.equals("bye")){
            input = sc.nextLine();
            System.out.println(input);
        }

        System.out.println("Bye! Hope to see you again soon");
}
    }

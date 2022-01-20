import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        Scanner sc = new Scanner(System.in);

        while(sc.hasNext()) {
            String value = sc.nextLine();
            if (value.equals("bye")){
                System.out.println(" Bye. Hope to see you again soon!");
                break;
            }
            else {
                System.out.println(value);
            }
        }

    }
}

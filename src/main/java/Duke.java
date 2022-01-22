import java.util.Scanner;
public class Duke {
    private static final String lines = "____________________________________________________________";
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(lines + "\n" + "Hello! I am Pogu, your personal assistant");
        System.out.println("What do you wish for me to do?" + "\n" + lines);

        String echo = sc.nextLine();
        while(!echo.equals("bye")){
            System.out.println(lines + "\n" + echo + "\n" + lines);
            echo = sc.nextLine();
        }

        System.out.println(lines + "\n" + "Bye. Hope to see you again soon!" + "\n" + lines);
    }
}

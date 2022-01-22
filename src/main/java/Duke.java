import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class Duke {
    private static final String lines = "____________________________________________________________";
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(lines + "\n" + "Hello! I am Pogu, your personal assistant");
        System.out.println("What do you wish for me to do?" + "\n" + lines);

        String echo = sc.nextLine();
        ArrayList<String> arr = new ArrayList<>();

        while(!echo.equals("bye")){
            if (!echo.equals("list")) {
                arr.add(echo);
                System.out.println(lines + "\n" + "added: " + echo + "\n" + lines);
                echo = sc.nextLine();
            } else {
                int index = 1;
                System.out.println(lines);
                for(String str : arr){
                    System.out.println(index +". " + str);
                    index++;
                }
                echo = sc.nextLine();
            }
        }

        System.out.println(lines + "\n" + "Bye. Hope to see you again soon!" + "\n" + lines);
    }
}

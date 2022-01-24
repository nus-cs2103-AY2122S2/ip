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
        ArrayList<Task> arr = new ArrayList<>();

        while(!echo.equals("bye")){

            switch(echo) {
                case "list":
                    int index = 1;
                    System.out.println(lines);
                    System.out.println("Here are the tasks in your list:");
                    for(Task task : arr){
                        System.out.println(index +". " + task);
                        index++;
                    }
                    echo = sc.nextLine();
                    break;


                default:
                    if(echo.startsWith("mark")) {
                        int number = Integer.parseInt(echo.substring(5));
                        arr.set(number - 1, arr.get(number - 1).markAsDone());
                        System.out.println(lines);
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println(arr.get(number - 1) + "\n" + lines);
                        echo = sc.nextLine();

                    } else if (echo.startsWith("unmark")) {
                        int number = Integer.parseInt(echo.substring(7));
                        arr.set(number - 1, arr.get(number - 1).markAsUndone());
                        System.out.println(lines);
                        System.out.println("OK, I've marked this task as not done yet:");
                        System.out.println(arr.get(number - 1) + "\n" + lines);
                        echo = sc.nextLine();

                    } else {
                        arr.add(new Task(echo));
                        System.out.println(lines + "\n" + "added: " + echo + "\n" + lines);
                        echo = sc.nextLine();
                    }
            }
            /*if (!echo.equals("list")) {
                arr.add(echo);
                System.out.println(lines + "\n" + "added: " + echo + "\n" + lines);
                echo = sc.nextLine();
            } else {
                int index = 1;
                System.out.println(lines);
                System.out.println("Here are the tasks in your list:");
                for(String str : arr){
                    System.out.println(index +". " + str);
                    index++;
                }
                echo = sc.nextLine();
            }*/
        }

        System.out.println(lines + "\n" + "Bye. Hope to see you again soon!" + "\n" + lines);
    }
}

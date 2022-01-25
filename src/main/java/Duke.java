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
                        //echo = sc.nextLine();

                    } else if (echo.startsWith("unmark")) {
                        int number = Integer.parseInt(echo.substring(7));
                        arr.set(number - 1, arr.get(number - 1).markAsUndone());
                        System.out.println(lines);
                        System.out.println("OK, I've marked this task as not done yet:");
                        System.out.println(arr.get(number - 1) + "\n" + lines);
                        //echo = sc.nextLine();

                    } else if (echo.startsWith("deadline")) {
                        String str = echo.substring(9);
                        String[] arrOfStr = str.split("/by");
                        String task = arrOfStr[0];
                        String deadline = arrOfStr[1];
                        Task added = new Deadline(task,deadline);
                        arr.add(added);
                        System.out.println(lines + "\n" + "Ok I've added: " + added +
                                "\nThere are " + arr.size() + " task(s) in the list now." + "\n" + lines);
                        //echo = sc.nextLine();

                    } else if (echo.startsWith("event")) {
                        String str = echo.substring(6);
                        String[] arrOfStr = str.split("/at");
                        String task = arrOfStr[0];
                        String deadline = arrOfStr[1];
                        Task event = new Event(task,deadline);
                        arr.add(event);
                        System.out.println(lines + "\n" + "Ok I've added: " + event +
                                "\nThere are " + arr.size() + " task(s) in the list now." + "\n" + lines);
                        //echo = sc.nextLine();

                    } else if (echo.startsWith("todo")) {
                        try {
                            String str = echo.substring(5);
                            Task todo = new Todo(str);
                            arr.add(todo);
                            System.out.println(lines + "\n" + "Ok I've added: " + todo +
                                    "\nThere are " + arr.size() + " task(s) in the list now." + "\n" + lines);
                            //echo = sc.nextLine();
                        } catch (StringIndexOutOfBoundsException e) {
                            System.out.println(lines + "\n" + "☹ OOPS!!! The description of a todo cannot be empty."
                                + "\n" + lines);
                            //echo = sc.nextLine();
                        }

                    } else if (echo.startsWith("delete")) {
                        int number = Integer.parseInt(echo.substring(7));
                        System.out.println("im here");
                        Task task = arr.get(number - 1);
                        arr.remove(number - 1);
                        System.out.println(lines + "\n" + "Ok I've deleted this task: " + task +
                                "\nThere are " + arr.size() + " task(s) in the list now." + "\n" + lines);
                    }
                    else {
                        try {
                            throw new IllegalArgumentException();
                        } catch (IllegalArgumentException e){
                            System.out.println(lines + "\n" + "☹ OOPS!!! I'm sorry " +
                                    "but I don't know what that means." + "\n" + lines);
                        }
                      }
                    }
                        echo = sc.nextLine();



        }

        System.out.println(lines + "\n" + "Bye. Hope to see you again soon!" + "\n" + lines);
    }
}

import java.util.*;
public class Duke {
    static Scanner sc = new Scanner(System.in);
    static String line = "\n---------------------\n";
    public static void main(String[] args) {
        String logo = " ____         _   _     \n"
                    + "|  _ \\       | | | |\n"
                    + "| |_| |      | |-| |\n"
                    + "| |_| |  _   | |-| |\n"
                    + "|____/  |_|  |_| |_|\n";
        System.out.println("Hello, I am B.H. How can I help you?\n" + logo + line);

        BH bh = new BH();

        while(true) {
            String input = sc.nextLine();
            String[] inputArray = input.split(" ", 2);
            if (inputArray[0].equals("bye")) {
                System.out.println(line + "GoodBye! Thanks for using B.H!" + line);
                break;
            } else if (inputArray[0].equals("list")) {
                System.out.println(line + bh.getList() + line);
            } else if (inputArray[0].equals("mark") && inputArray.length > 1) {
                int index = Integer.parseInt(inputArray[1]) - 1;
                if (index < bh.getListSize()) {
                    System.out.println(line + "Well done! \n" + bh.mark(index) + line);
                } else {
                    System.out.println("Index out of range");
                }
            } else if (inputArray[0].equals("unmark") && inputArray.length > 1) {
                int index = Integer.parseInt(inputArray[1]) - 1;
                System.out.println("Oh no! \n" + bh.unmark(index));
            } else {
                if (inputArray[0].equals("todo")) {
                    String task = inputArray[1];
                    Task newtask = new Todo(task);
                    bh.addToList(newtask);
                    System.out.println(line + "Task added: " + newtask.toString() + "\n" +
                            "Now you have " + bh.getListSize() + " tasks in the list" + line);
                } else if (inputArray[0].equals("deadline")) {
                    String s = inputArray[1];
                    String[] arr = s.split("/by");
                    String task = arr[0];
                    String time = arr[1];
                    Task newtask = new Deadline(task, time);
                    bh.addToList(newtask);
                    System.out.println(line + "Task added: " + newtask.toString() + "\n" +
                            "Now you have " + bh.getListSize() + " tasks in the list" + line);
                } else if (inputArray[0].equals("event")){
                    String s = inputArray[1];
                    String[] arr = s.split("/at");
                    String task = arr[0];
                    String time = arr[1];
                    Task newtask = new Event(task, time);
                    bh.addToList(newtask);
                    System.out.println(line + "Task added: " + newtask.toString() + "\n" +
                            "Now you have " + bh.getListSize() + " tasks in the list" + line);
                } else {
                    System.out.println("Wrong input, please try again");
                }

            }
        }
    }
}

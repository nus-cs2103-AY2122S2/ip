import java.util.Scanner;
public class Johnny {

    //Checks if a String is a numerical value before parsing it
    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    public static void main(String[] args) {
        System.out.println("Hello! I'm Johnny \n" + "What can I do for you?");
        Scanner sc = new Scanner(System.in);
        String input;
        InputList userList = new InputList();

        while(true) {
            input = sc.nextLine();
            String[] tags = input.split(" ", 2);

            if(input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            else if(input.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                userList.printList();
            }
            else if(input.length() >= 5 && input.substring(0, 5).equals("mark ") && isNumeric(input.substring(5))) {
                userList.mark(Integer.parseInt(input.substring(5)));
            }
            else if(input.length() >= 7 && input.substring(0, 7).equals("unmark ") && isNumeric(input.substring(7))) {
                userList.unmark(Integer.parseInt(input.substring(7)));
            }
            else if(tags[0].equals("todo")) {
                String content = tags[1];

                Task newTask = new Todo(content);
                userList.add(newTask);

                System.out.println("Got it! I've added this task:");
                System.out.println(newTask);
                System.out.println("Now you have " + userList.getCount() + " tasks in your list.");
            }
            else if(tags[0].equals("deadline") && tags[1].contains("/")) {
                String content = tags[1];
                String[] details = content.split("/", 2);

                Task newTask = new Deadline(details[0], details[1]);
                userList.add(newTask);

                System.out.println("Got it! I've added this task:");
                System.out.println(newTask);
                System.out.println("Now you have " + userList.getCount() + " tasks in your list.");
            }
            else if(tags[0].equals("event") && tags[1].contains("/")) {
                String content = tags[1];
                String[] details = content.split("/", 2);

                Task newTask = new Event(details[0], details[1]);
                userList.add(newTask);

                System.out.println("Got it! I've added this task:");
                System.out.println(newTask);
                System.out.println("Now you have " + userList.getCount() + " tasks in your list.");
            }
            else {
                System.out.println("I'm sorry, I didn't quite get that");
            }
        }
    }
}

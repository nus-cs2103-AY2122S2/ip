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
            else {
                Task newTask = new Task(input);
                userList.add(newTask);
            }
        }
    }
}

import java.util.Scanner;
import java.util.ArrayList;

public class Level5 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> arrList = new ArrayList<Task>();
        String line = "    __________________________________________________\n"; //print a line before greeting message
        System.out.println(line + "     Hi there. I'm Siri \n" + "     How may I help you? \n" + line);

        while (true) { //will keep querying user for input until user inputs "bye"
            String word = sc.next();
            if (word.equals("bye")) { //prints goodbye text and exit while loop,stop scanner from scanning
                System.out.println(line + "     Goodbye.\n" + line);
                break; //end while loop
            } else if (word.equals("list")) { //prints all elements in a list with index in front
                System.out.println(line + "     Tasks to do:");
                int s = arrList.size();
                for (int i = 0; i < s; i++) {
                    String currTask = arrList.get(i).toString();
                    System.out.println("     " + (i + 1) + "." + currTask);
                }
                System.out.println(line);
            } else if (word.equals("mark")) { //marks the specified task with an "X"
                int num = sc.nextInt();
                int index = num - 1;
                Task t = arrList.get(index);
                t.markAsDone();
                arrList.set(index, t);
                System.out.println(line + "     This task has been marked:\n       "
                        + t.toString() + "\n"
                        + line);
            } else if (word.equals("unmark")) { //unmarks the specified task
                int num = sc.nextInt();
                int index = num - 1;
                Task t = arrList.get(index);
                t.markAsNotDone();
                arrList.set(index, t);
                System.out.println(line + "     This task has been unmarked:\n       "
                        + t.toString() + "\n"
                        + line);
            } else if (word.equals("todo")) { //creates todo task with "[T]" prefix and adds to the list
                String desc = sc.nextLine();
                try {
                    checkRestOfInput(desc);
                    Todo t = new Todo(desc);
                    arrList.add(t);
                    System.out.println(line + "     This task has been added to your list:\n       "
                            + t.toString() + "\n" + "\n"
                            + "     Number of task(s) in your list: " + arrList.size() + "\n"
                            + line);
                } catch (Exception e) {
                    System.out.println(line + e.toString() + "\n" + line);
                }
            } else if (word.equals("deadline")) {
                //creates deadline task with "[D]" prefix and deadline postfix and adds to list
                while (sc.hasNextLine()) {
                    String[] userInput = sc.nextLine().split("/by");
                    // The userInput array now contains [taskDescription, deadline]
                    Deadline deadLineTask = new Deadline(userInput[0], userInput[1]);
                    arrList.add(deadLineTask);
                    System.out.println(line + "     This task has been added to your list:\n       "
                            + deadLineTask.toString() + "\n" + "\n"
                            + "     Number of task(s) in your list: " + arrList.size() + "\n"
                            + line);
                    break;
                }
            } else if (word.equals("event")) {
                //creates event task with "[E]" prefix and event date postfix and adds to list
                while (sc.hasNextLine()) {
                    String[] userInput = sc.nextLine().split("/at");
                    // The userInput array now contains [taskDescription, event date]
                    Event eventTask = new Event(userInput[0], userInput[1]);
                    arrList.add(eventTask);
                    System.out.println(line + "     This task has been added to your list:\n       "
                            + eventTask.toString() + "\n" + "\n"
                            + "     Number of task(s) in your list: " + arrList.size() + "\n"
                            + line);
                    break;
                }
            } else {
                System.out.println(line + "     Please enter a valid task type with description.       \n" + line);
            }
        }
    }

    static void checkRestOfInput(String description) throws EmptyDescException {

        if (description.isBlank()) {
            throw new EmptyDescException("     The description of a todo task cannot be empty.");
        } else {
            return;
        }
    }
}

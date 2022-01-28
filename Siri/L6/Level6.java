import java.util.Scanner;
import java.util.ArrayList;

public class Level6 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> arrList = new ArrayList<Task>();
        String line = "    ____________________________________________________________________________________\n";
        //prints a line before and after greeting message upon runnind of
        System.out.println(line + "     Hi, I'm Siri, your personal to-do list program. \n"
                + "     You can add 3 types of tasks to the list: todo, deadline and event tasks.\n"
                + "     To add to the list, specify the type of task before it's description.\n"
                + "     Only deadlines and events can accept dates and timings\n\n"
                + "     Examples:\n"
                + "     todo run a mile\n"
                + "     deadline return library book /by Sunday 2359\n"
                + "     event Jack's wedding /at Holiday Inn 1800\n\n"
                + "     What do you wish to take note of today? \n" + line);

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
            } else if (word.equals("delete")) {
                    int listIndex = sc.nextInt() - 1;
                    Task tempT = arrList.get(listIndex);
                    arrList.remove(listIndex);
                    System.out.println(line + "     Sure. I've removed this task from the list:\n       "
                            + tempT.toString() + "\n" + "\n"
                            + "     Number of task(s) in your list: " + arrList.size() + "\n"
                            + line);
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

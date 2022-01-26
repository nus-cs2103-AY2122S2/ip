import java.util.Scanner; //import Scanner
import java.util.ArrayList; //import ArrayList
import java.util.Arrays; //import Arrays

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String dukeGreeting = "Hello! I'm Duke \nWhat can I do for you?";
        boolean endChat = false;
        String endMessage = "Bye. Hope to see you again soon!";

        //arraylist to store the list of tasks
        ArrayList<Task> arrayLst = new ArrayList<>();

        System.out.println(dukeGreeting);
        levelFourRespond(arrayLst);
        System.out.println(endMessage);


    }

    /**
     * Method that handles the response for Level-1.
     */
    public static void levelOneRespond() {
        Scanner sc = new Scanner(System.in);
        String bye = "bye";
        String dukeMessage = "Duke: ";
        String endMessage = "Bye. Hope to see you again soon!";
        String input = sc.nextLine();
        if (!input.equals(bye)) {
            System.out.println(dukeMessage + input);
            levelOneRespond();
        }
    }

    /**
     * Method that handles the response for Level-4.
     * Level 3:added the ability to mark and unmark tasks as well as different representation for Tasks. Furthermore,
     * the arraylist is changed to contain Task objects
     * Level 4: Added Todo, Event and Deadline subclasses to be detected and handled in each seperate case
     *
     * @param arrayLst arraylist that stores the Task entries for add and can be listed out.
     */
    public static void levelFourRespond(ArrayList<Task> arrayLst) {
        Scanner sc = new Scanner(System.in);
        String bye = "bye";
        String lst = "list";
        String markCommand = "mark";
        String unmarkCommand = "unmark";
        String todo = "todo";
        String event = "event";
        String deadline = "deadline";
        String tasksInList = "Here are the tasks in your list:";
        String input = sc.nextLine();

        if (input.equals(bye)) ; //ends recursive loop, causing the bye statement in main to be executed

            //list command
        else if (input.equals(lst)) {
            System.out.println(tasksInList);
            int i = 0;
            for (Task item : arrayLst) {
                i += 1;
                if (item.isMark()) {
                    System.out.println(i + ". " + item);
                } else {
                    System.out.println(i + ". " + item);
                }
            }
            levelFourRespond(arrayLst);

            //unmark
        } else if (input.contains(unmarkCommand)) { //unmark command
            String stringIdx = input.split(" ")[1];
            int idx = Integer.parseInt(stringIdx) - 1;
            Task unmarkTask = arrayLst.get(idx);
            unmarkTask.unmarkTask();
            levelFourRespond(arrayLst);

            //mark
        } else if (input.contains(markCommand)) { //mark command
            String stringIdx = input.split(" ")[1];
            int idx = Integer.parseInt(stringIdx) - 1;
            Task markTask = arrayLst.get(idx);
            markTask.markTask();
            levelFourRespond(arrayLst);

            //Todo
        } else if (input.contains(todo)) {
            String[] stringArray = input.split(" ");
            String[] nameArray = Arrays.copyOfRange(stringArray, 1, stringArray.length);
            String name = String.join(" ", nameArray);
            Todo newTodo = new Todo(name);
            arrayLst.add(newTodo);
            String todoMessage = "Got it. I've added this task:\n" + newTodo + "\nNow you have " + arrayLst.size() + " tasks in the list.";
            System.out.println(todoMessage);
            levelFourRespond(arrayLst);

            //Event
        } else if (input.contains(event)) {
            String[] stringArrayIncludingEvent = input.split(" ");
            String[] stringArrayExcludingEvent = Arrays.copyOfRange(stringArrayIncludingEvent, 1, stringArrayIncludingEvent.length);
            String stringExcludingEvent = String.join(" ", stringArrayExcludingEvent);
            String[] nameAndTimeArray = stringExcludingEvent.split("/at");
            String name = nameAndTimeArray[0];
            String time = nameAndTimeArray[1];
            Event newEvent = new Event(name, time);
            arrayLst.add(newEvent);
            String eventMessage = "Got it. I've added this task:\n" + newEvent + "\nNow you have " + arrayLst.size() + " tasks in the list.";
            System.out.println(eventMessage);
            levelFourRespond(arrayLst);

            //Deadline
        } else if (input.contains(deadline)) {
            String[] stringArrayIncludingDeadline = input.split(" ");
            String[] stringArrayExcludingDeadline = Arrays.copyOfRange(stringArrayIncludingDeadline, 1, stringArrayIncludingDeadline.length);
            String stringExcludingDeadline = String.join(" ", stringArrayExcludingDeadline);
            String[] nameAndTimeArray = stringExcludingDeadline.split("/by");
            String name = nameAndTimeArray[0];
            String time = nameAndTimeArray[1];
            Deadline newDeadline = new Deadline(name, time);
            arrayLst.add(newDeadline);
            String deadlineMessage = "Got it. I've added this task:\n" + newDeadline + "\nNow you have " + arrayLst.size() + " tasks in the list.";
            System.out.println(deadlineMessage);
            levelFourRespond(arrayLst);
        } else { //add task
            String added = "added: ";
            Task newTask = new Task(input);
            arrayLst.add(newTask);
            System.out.println(added + input);
            levelFourRespond(arrayLst);
        }

    }
}










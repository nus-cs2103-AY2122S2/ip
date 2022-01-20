import java.util.Scanner; //import Scanner
import java.util.ArrayList; //import ArrayList

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
        levelThreeRespond(arrayLst);
        System.out.println(endMessage);


    }

    /**
     * Method that handles the response for Level-1.
     */
    public static void levelOneRespond () {
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
     * Method that handles the response for Level-3. Building on level 2, it adds the ability to mark and unmark tasks as well as different representation for Tasks. Furthermore,
     * the arraylist is changed to contain Task objects
     * @param arrayLst arraylist that stores the Task entries for add and can be listed out.
     */
    public static void levelThreeRespond (ArrayList<Task> arrayLst) {
        Scanner sc = new Scanner(System.in);
        String bye = "bye";
        String lst = "list";
        String markCommand = "mark";
        String unmarkCommand = "unmark";
        String tasksInList = "Here are the tasks in your list:\n";
        String input = sc.nextLine();

        if (input.equals(bye)); //ends recursive loop, causing the bye statement in main to be executed
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
            levelThreeRespond(arrayLst);

        } else if (input.contains(unmarkCommand)) {
            String stringIdx = input.split(" ")[1];
            int  idx = Integer.parseInt(stringIdx) - 1;
            Task unmarkTask = arrayLst.get(idx);
            unmarkTask.unmarkTask();
            levelThreeRespond(arrayLst);

        } else if (input.contains(markCommand)){
            String stringIdx = input.split(" ")[1];
            int idx = Integer.parseInt(stringIdx) - 1;
            Task markTask = arrayLst.get(idx);
            markTask.markTask();
            levelThreeRespond(arrayLst);

        } else {
            String added = "added: ";
            Task newTask = new Task(input);
            arrayLst.add(newTask);
            System.out.println(added + input);
            levelThreeRespond(arrayLst);
        }
    }

    /**
     * Represents a Task. Contains a Task constructor, two methods to mark and unmark tasks, toString() method as well as a isMark() method to check if Task is marked
     */

    public static class Task {
        private boolean mark;
        public String name;

        public Task (String name) {
            this.name = name;
            this.mark = false;
        }

        public void markTask () {
            String markedMessage = "Nice! I've marked this task as done:\n";
            this.mark = true;
            System.out.println(markedMessage + "  " + this);
        }
        public void unmarkTask() {
            String unmarkedMessage = "OK, I've marked this task as not done yet:\n";
            this.mark = false;
            System.out.println(unmarkedMessage + "  " + this);
        }
        public boolean isMark() {
            return this.mark;
        }
        public String toString() {
            if (this.mark) {
                String marked = "[X] ";
                return marked + this.name;
            } else {
                String unmarked = "[ ] ";
                return unmarked + this.name;
            }
        }
    }
}


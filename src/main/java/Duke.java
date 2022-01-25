import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;

public class Duke {
    public static void main(String[] args) {
        
        String logo = " ____        _        \n"
        + "|  _ \\ _   _| | _____ \n"
        + "| | | | | | | |/ / _ \\\n"
        + "| |_| | |_| |   <  __/\n"
        + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        ArrayList<Task> tasks = new ArrayList<Task>();
        
        Scanner sc = new Scanner(System.in);
        
        myPrint("Hello! I'm Duke\n    What can I do for you?");
        
        String input;
        input = sc.nextLine();
        
        while (!input.equals("bye")) {
            try {
                String[] stringArr = input.split(" ", 2);
                
                if (stringArr[0].equals("list")) {

                    list(tasks);

                } else if (stringArr[0].equals("mark")) {

                    mark(stringArr, tasks);
                    
                } else if (stringArr[0].equals("unmark")) {

                    unmark(stringArr, tasks);
                    
                } else if (stringArr[0].equals("delete")) {

                    delete(stringArr, tasks);
                    
                }else if (stringArr[0].equals("todo")) {

                    createToDo(stringArr, tasks);
                    
                } else if (stringArr[0].equals("deadline")) {

                    createDeadline(stringArr, tasks);
                    
                } else if (stringArr[0].equals("event")) {

                    createEvent(stringArr, tasks);
                    
                } else {
                    throw new DukeException();
                }
            } catch (DukeException e) {
                myPrint(e.toString());
            }
            
            input = sc.nextLine();
            
        }
        
        myPrint("Bye. Hope to see you again soon!");
        
        sc.close();
        
    }
    
    //Print the output with indentation and separator lines
    public static void myPrint(String toPrint) {
        System.out.println("    ________________________________________________________________");
        System.out.println("    " + toPrint);
        System.out.println("    ________________________________________________________________\n");
        
    }
    

    //Returns a string that describes the number of tasks in the list
    public static String countList(ArrayList<Task> tasks) {
        String isSingular = "s";
        
        if (tasks.size() == 1) {
            isSingular = "";
        }
        
        return "\n    Now you have " + tasks.size() + " task" + isSingular + " in your list.";
    }
    
    //Lists out the tasks in list
    public static void list(ArrayList<Task> tasks) {
        String tempStr = "Here are the tasks in your list:\n    ";
        for (int i = 0; i < tasks.size(); i++) {
            if (i != 0) {
                tempStr += "\n    ";
            }
            tempStr += (i + 1) + ". " + tasks.get(i);
        }
        myPrint(tempStr);
    }

    public static void mark(String[] stringArr, ArrayList<Task> tasks) throws DukeException {
        if (stringArr.length < 2) {
            throw new DukeAbsentInfoException("☹ OOPS!!! Please specify the task to be marked using a int");
        }
        
        int idx = Integer.parseInt(stringArr[1]);
        
        if (idx < 1 || idx > tasks.size()) {
            throw new DukeIdxOOBException();
        }
        
        tasks.get(idx - 1).mark();
        
        myPrint("Nice! I've marked this task as done:\n      " + tasks.get(idx - 1));
    }

    public static void unmark(String[] stringArr, ArrayList<Task> tasks) throws DukeException {
        if (stringArr.length < 2) {
            throw new DukeAbsentInfoException(
            "☹ OOPS!!! Please specify the task to be unmarked using a int");
        }
        
        int idx = Integer.parseInt(stringArr[1]);
        
        if (idx < 1 || idx > tasks.size()) {
            throw new DukeIdxOOBException();
        }
        
        tasks.get(idx - 1).unmark();
        
        myPrint("OK, I've marked this task as not done yet:\n      " + tasks.get(idx - 1));
    }

    public static void delete(String[] stringArr, ArrayList<Task> tasks) throws DukeException {
        if (tasks.size() == 0) {
            throw new DukeException("☹ OOPS!!! There is nothing on the list to delete!");
        }

        if (stringArr.length < 2) {
            throw new DukeAbsentInfoException(
                "☹ OOPS!!! Please specify the task to be deleted using an int value"
            );
        }

        int idx = Integer.parseInt(stringArr[1]);

        if (idx < 1 || idx > tasks.size()) {
            throw new DukeIdxOOBException();
        }

        myPrint("Noted. I've removed this task:\n      " + tasks.remove(idx - 1) + countList(tasks));

    }

    public static void createToDo(String[] stringArr, ArrayList<Task> tasks) throws DukeException {
        if (stringArr.length < 2) {
            throw new DukeAbsentInfoException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        
        Task tempTask = new ToDo(stringArr[1]);
        
        tasks.add(tempTask);
        myPrint("Got it. I've added this task:\n      " + tempTask + countList(tasks));
    }
    
    public static void createDeadline(String[] stringArr, ArrayList<Task> tasks) throws DukeException {
        if (stringArr.length < 2) {
            throw new DukeAbsentInfoException("☹ OOPS!!! The description of a deadline cannot be empty.");
        }
        
        String[] descriptionTime = stringArr[1].split("/", 2);
        
        if (descriptionTime.length < 2) {
            throw new DukeAbsentInfoException(
            "☹ OOPS!!! The date/time of the deadline has to be specified and seperated from the description using a \"/\".");
        }
        
        String[] prepoTime = descriptionTime[1].split(" ", 2);
        Task tempTask = new Deadline(descriptionTime[0], prepoTime[0] + ": " + prepoTime[1]);
        
        tasks.add(tempTask);
        myPrint("Got it. I've added this task:\n      " + tempTask + countList(tasks));
    }

    public static void createEvent(String[] stringArr, ArrayList<Task> tasks) throws DukeException {
        if (stringArr.length < 2) {
            throw new DukeAbsentInfoException("☹ OOPS!!! The description of an event cannot be empty.");
        }
        
        String[] descriptionTime = stringArr[1].split("/", 2);
        
        if (descriptionTime.length < 2) {
            throw new DukeAbsentInfoException(
            "☹ OOPS!!! The date/time of the event has to be specified and seperated from the description using a \"/\".");
        }
        
        String[] prepoTime = descriptionTime[1].split(" ", 2);
        Task tempTask = new Event(descriptionTime[0], prepoTime[0] + ": " + prepoTime[1]);
        
        tasks.add(tempTask);
        myPrint("Got it. I've added this task:\n      " + tempTask + countList(tasks));
    }

}

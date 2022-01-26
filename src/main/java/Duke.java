import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDate;

public class Duke {
    public static void main(String[] args) {
        
        String logo = " ____        _        \n"
        + "|  _ \\ _   _| | _____ \n"
        + "| | | | | | | |/ / _ \\\n"
        + "| |_| | |_| |   <  __/\n"
        + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        ArrayList<Task> tasks = new ArrayList<Task>();

        try {
            File f = new File("data/duke.txt");
            if (f.createNewFile()) {
                System.out.println("created file duke.txt");
            } else {
                System.out.println("file exists");
                tasks = loadList("data/duke.txt");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        
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

        try {
            writeToFile("data/duke.txt", convertWriteFormat(tasks));
        } catch (Exception e) {
            System.out.println(e);
        }
        
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
                    "☹ OOPS!!! The task needs to be specified in the format of [task] [description] /yyyy-mm-dd");
        }

        try {
            LocalDate.parse(descriptionTime[1]);
        } catch(Exception e) {
            throw new DukeException("☹ OOPS!!! The date has to be formatted in yyyy-mm-dd");
        }
        
        Task tempTask = new Deadline(descriptionTime[0], descriptionTime[1]);
        
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
                    "☹ OOPS!!! The task needs to be specified in the format of [task] [description] /yyyy-mm-dd");
        }
        
        try {
            LocalDate.parse(descriptionTime[1]);
        } catch(Exception e) {
            throw new DukeException("☹ OOPS!!! The date has to be formatted in yyyy-mm-dd");
        }

        Task tempTask = new Event(descriptionTime[0], descriptionTime[1]);
        
        tasks.add(tempTask);
        myPrint("Got it. I've added this task:\n      " + tempTask + countList(tasks));

    }

    public static String convertWriteFormat(ArrayList<Task> tasks) {
        String returnStr = "";

        for (Task t : tasks) {
            if (t instanceof ToDo) {
                returnStr += "T#" + t.isDone + "#" + t.description + "\n";
            } else if (t instanceof Deadline) {
                Deadline d = (Deadline) t;
                returnStr += "D#" + d.isDone + "#" + d.description + "#" + d.deadline + "\n";
            } else if (t instanceof Event) {
                Event e = (Event) t;
                returnStr += "E#" + e.isDone + "#" + e.description + "#" + e.time + "\n";
            }
        }

        return returnStr;
    }

    public static void writeToFile(String filePath, String content) throws Exception {
        FileWriter fw = new FileWriter(filePath);
        fw.write(content);
        fw.close();
    }

    public static ArrayList<Task> loadList(String filePath) throws Exception {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        ArrayList<Task> tasks = new ArrayList<Task>();
        String[] stringArr;

        while(s.hasNext()) {
            stringArr = s.nextLine().split("#");

            if (stringArr[0].equals("T")) {

                tasks.add(new ToDo(stringArr[2], stringArr[1].equals("true")));

            } else if (stringArr[0].equals("D")) {

                tasks.add(new Deadline(stringArr[2], stringArr[1].equals("true"), stringArr[3]));

            } else if (stringArr[0].equals("E")) {

                tasks.add(new Event(stringArr[2], stringArr[1].equals("true"), stringArr[3]));

            }
        }
        
        return tasks;
    }

}

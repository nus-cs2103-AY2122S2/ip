import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.util.regex.Pattern;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

public class Duke {
    /**
     * Enum of different types of tasks.
     */
    private enum TaskType {
        TODO,
        EVENT,
        DEADLINE
    }

    private List<Task> lst = new ArrayList<Task>(); // a list to collect tasks
    private Scanner sc= new Scanner(System.in);

    /**
     * Prints the msg between line breaks.
     *
     * @param msg A string of message to be printed.
     */
    private void printMsg(String msg) {
        String br = "\t____________________________________________________________\n";
        System.out.println(br + msg + br);
        return;
    }

    /**
     * Prints out the greeting words.
     */
    private void greeting() {
        this.printMsg("\t Hello! I'm Duke\n\t What can I do for you?\n");
    }

    /**
     * Prints out the goodbye words.
     */
    private void bye() {
        this.printMsg("\t Bye. Hope to see you again soon!\n");
    }

    /**
     * Lists out the current tasks as well as their status.
     */
    private void list() {
        String msg = "";
        for (int i = 1; i <= lst.size(); i++) {
            msg += "\t " + i + "." + lst.get(i-1).toString() + "\n";
        }
        this.printMsg("\t Here are the tasks in your list:\n" + msg);
    }

    /**
     * Adds a task to the list.
     * A task can be one of 'todo', 'event' or 'deadline', whilst 'event' and 'deadline' have a time 
     * specified after the keyword '/at' or '/by'.
     *
     * @param input The user input of adding a task. 
     *
     * @throws DukeException when part of the infomation of a todo, event or deadline is missing.
     */
    private void addTask(TaskType type, String input) throws DukeException {
        String description;
        String time;
        int timeIndex;
        Task t;

        if (type == TaskType.TODO) {
            try {
                description = input.substring(5);
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeException("\t ☹ OOPS!!! The description of a todo cannot be empty.\n");
            }
            if (description.equals("")) {
                throw new DukeException("\t ☹ OOPS!!! The description of a todo cannot be empty.\n");
            }
            t = new Todo(description);
        } else if (type == TaskType.EVENT) {
            timeIndex = input.indexOf(" /at ");
            if (timeIndex == -1) { 
                // cannot find '/at'
                throw new DukeException("\t ☹ OOPS!!! The time of an event cannot be empty.\n");
            }
            try {
                description = input.substring(6, timeIndex);
                time = input.substring(timeIndex + 5);
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeException("\t ☹ OOPS!!! The description or time of an event cannot be empty.\n");
            }
            if (description.equals("") || time.equals("")) {
                throw new DukeException("\t ☹ OOPS!!! The description or time of an event cannot be empty.\n");
            }
            t = new Event(description, this.strToDate(time));
        } else { 
            // deadline
            timeIndex = input.indexOf(" /by ");
            if (timeIndex == -1) { 
                // cannot find '/at'
                throw new DukeException("\t ☹ OOPS!!! The time of a deadline cannot be empty.\n");
            }
            try {
                description = input.substring(9, timeIndex);
                time = input.substring(timeIndex + 5);
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeException("\t ☹ OOPS!!! The description or time of a deadline cannot be empty.\n");
            }
            if (description.equals("") || time.equals("")) {
                throw new DukeException("\t ☹ OOPS!!! The description or time of a deadline cannot be empty.\n");
            }
            t = new Deadline(description, this.strToDate(time));
        }
        this.lst.add(t);
        this.printMsg("\t Got it. I've added this task:\n\t   " + t.toString()
                + "\n\t Now you have " + this.lst.size() + " tasks in the list.\n");
    }

    /**
     * Marks a particular task as done.
     *
     * @param num The number of the task to be marked.
     */
    private void mark(int num) {
        this.lst.get(num).markAsDone();
        this.printMsg("\t Nice! I've marked this task as done:\n\t   " + lst.get(num).toString() + "\n"); 
    }

    /**
     * Unmarks a particular task as done.
     *
     * @param num The number of the task to be unmarked.
     */
    private void unmark(int num) {
        this.lst.get(num).unmarkAsDone();
        this.printMsg("\t OK, I've marked this task as not done yet:\n\t   " + lst.get(num).toString() + "\n");
    }

    /**
     * Deletes a task in the specified index.
     *
     * @param num The number of the task to be removed.
     */
    private void delete(int num) {
        Task t = this.lst.remove(num);
        this.printMsg("\t Noted. I've removed this task:\n\t   " + t.toString()
                + "\n\t Now you have " + this.lst.size() + " tasks in the list.\n");
    }

    /**
     * Gives the bot intructions and let it do the corresponding job.
     */
    private void run() {
        String input = sc.nextLine();
        String[] splitted = input.split("\\s+");
        if (input.equals("bye")) { 
            return;
        } else if (input.equals("list")) {
            this.list();
        } else if (splitted.length == 2 && splitted[0].equals("mark")) {
            try {
                int num = Integer.parseInt(splitted[1]);
                this.mark(num-1);
            } catch (NumberFormatException e) {
                this.printMsg("\t Opps! Pls check your numbers.\n");
            } catch (IndexOutOfBoundsException e) {
                this.printMsg("\t Opps! The item you wanna mark is out of bounds.\n");
            }
        } else if (splitted.length == 2 && splitted[0].equals("unmark")) {
            try {
                int num = Integer.parseInt(splitted[1]);
                this.unmark(num-1);
            } catch (NumberFormatException e) {
                this.printMsg("\t Opps! Pls check your numbers.\n");
            } catch (IndexOutOfBoundsException e) {
                this.printMsg("\t Opps! The item you wanna unmark is out of bounds.\n");
            }
        } else if (splitted[0].equals("todo")) {
            try {
                this.addTask(TaskType.TODO, input);
            } catch (DukeException e) {
                this.printMsg(e.getMessage());
            }
        } else if (splitted[0].equals("event")) {
            try {
                this.addTask(TaskType.EVENT, input);
            } catch (DukeException e) {
                this.printMsg(e.getMessage());
            }
        } else if (splitted[0].equals("deadline")) {
            try {
                this.addTask(TaskType.DEADLINE, input);
            } catch (DukeException e) {
                this.printMsg(e.getMessage());
            }
        } else if (splitted.length == 2 && splitted[0].equals("delete")) {
            try {
                int num = Integer.parseInt(splitted[1]);
                this.delete(num-1);
            } catch (NumberFormatException e) {
                this.printMsg("\t Opps! Pls check your numbers.\n");
            } catch (IndexOutOfBoundsException e) {
                this.printMsg("\t Opps! The item you wanna mark is out of bounds.\n");
            }
        } else {
            this.printMsg("\t ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n");
        }
        this.run();
    }

    /**
     * Loads the list of tasks from the hard disk when Duke starts up.
     * 
     * @throws FileNotFoundException TODO
     * @throws IOException TODO
     */
    public void loadData() throws IOException {
        File f = new File("./data/tasks.txt");
        // create the folder if it does not exist
        if (f.getParentFile().exists() == false) {
            f.getParentFile().mkdir();
        }
        // create the file if it does not exist
        if (f.exists() == false) {
            f.createNewFile();
        }

        Scanner scanner = new Scanner(f);
        Task t;
        String line;
        String[] splitted;
        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            splitted = line.split(Pattern.quote(" | "));

            if (splitted.length == 4 && splitted[0].equals("D")) {
                // deadline
                t = new Deadline(splitted[2], splitted[3]);    
            } else if (splitted.length == 4 && splitted[0].equals("E")) {
                // event
                t = new Event(splitted[2], splitted[3]);
            } else if (splitted.length == 3 && splitted[0].equals("T")) {
                // todo
                t = new Todo(splitted[2]);
            } else {
                // TODO: handle exceptions
                return;
            }

            // marks the task as done if the second parameter is 1
            if (splitted[1].equals("1")) {
                t.markAsDone();
            }

            this.lst.add(t);
        }
    }

    /**
     * Stores the list of tasks in hard disk.
     */
    public void storeData() throws IOException {
        FileWriter fw = new FileWriter("./data/tasks.txt");
        String data = "";
        for (int i = 0; i < lst.size(); i++) {
            data += lst.get(i).toStoreInfo();
        }
        fw.write(data);        
        fw.close();
    /**
     * Changes from String to LocalDate.
     */
    private LocalDate strToDate(String str) {
        return LocalDate.parse(str);
    }

    public static void main(String[] args) {
        Duke bot = new Duke();

        bot.greeting();
        try {
            bot.loadData();
        } catch (IOException e) {
            bot.printMsg("\t Opps! The file ./data/tasks.txt is not in correct format.");
        } 
        bot.run();
        try {
            bot.storeData();
        } catch (IOException e) {
            bot.printMsg("\t Opps! An error occurs when writing to ./data/tasks.txt\n");
        }
        bot.bye();
    }
}

import java.util.*;
import java.io.*;
import java.time.format.DateTimeParseException;
import java.time.LocalDate;

/**
 * This program is used to add, list & mark the status of your current tasks.
 * @author Sim Jun Heng
 * @version CS2103T AY21/22 Sem 2
 */
public class Duke {
    // ArrayList to store all your tasks
    private static ArrayList<Task> list = new ArrayList<>();

    // Main method of this program
    public static void main(String[] args) throws IOException {
        // Start Message
        System.out.println("IF YOU ARE NEW TO THIS PROGRAM, ENTER ? TO SEE A LIST OF AVAILABLE COMMANDS.");
        System.out.println("---------------------------------------------------------------------------");
        System.out.println("HELLO! I'M DUKE");
        System.out.println("WHAT CAN I DO FOR YOU?");

        // Load file data into task list
        try {
            loadFile();
        } catch (DukeException ex) {
            System.out.println(ex.getMessage());
        }

        // Scanner Object
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        try {
            while (!input.equals("bye")) {
                String[] temp = input.split(" ");
                if (temp[0].equals("?")) {
                    helpCmd();
                } else if (temp[0].equals("mark")) {
                    int index = Integer.parseInt(temp[1]) - 1;
                    markTask(index);
                } else if (temp[0].equals("unmark")) {
                    int index = Integer.parseInt(temp[1]) - 1;
                    unMarkTask(index);
                } else if (temp[0].equals("list")) {
                    list();
                } else if (temp[0].equals("todo")) {
                    addToDo(input);
                } else if (temp[0].equals("deadline")) {
                    addDeadline(input);
                } else if (temp[0].equals("event")) {
                    addEvent(input);
                } else if (temp[0].equals("delete")) {
                    int index = Integer.parseInt(temp[1]) - 1;
                    deleteTask(index);
                } else {
                    notSpecified();
                }
                input = scanner.nextLine();
            }
            //Exit
            writeFile();
            System.out.println("Bye. Hope to see you again soon!");
        } catch (DukeException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     *  Prints out a list of available commands in this program.
     */
    public static void helpCmd() {
        System.out.println("LIST OF AVAILABLE COMMANDS IN THIS PROGRAM      DESCRIPTION");
        System.out.println("list                                            List out all added tasks");
        System.out.println("todo {task description}                         Add ToDo into list");
        System.out.println("deadline {task description} /by {DATE}          Add Deadline into list");
        System.out.println("event {task description} /at {DATE}             Add Event into list");
        System.out.println("mark {Task ID}                                  Mark specific task as done");
        System.out.println("unmark {Task ID}                                Mark specific task as not done");
        System.out.println("delete {Task ID}                                Delete specific task from list");
        System.out.println("bye                                             End the Duke program");
    }

    public static void loadFile() throws IOException, DukeException {
        // Check if data directory exist in the project root
        File directory = new File("./data");
        if (!directory.exists()) {
            directory.mkdir(); // Create if directory does not exist
        }
        // Check if duke.txt exist in the data directory
        File file = new File("./data/duke.txt");
        if (!file.exists()) {
            file.createNewFile(); // Create if file does not exist
        }
        // Read from duke.txt and store information back into task list
        Scanner fc = new Scanner(file);
        int index = 0;
        while (fc.hasNext()) {
            String[] str = fc.nextLine().split(",");
            if (str[0].equals("T")) {
                if (str[1].equals("1")) {
                    Task newTask = new ToDo(str[2], true);
                    list.add(newTask);
                } else {
                    Task newTask = new ToDo(str[2], false);
                    list.add(newTask);
                }
                index++;
            } else if (str[0].equals("D")) {
                try {
                    if (str[1].equals("1")) {
                        LocalDate date = LocalDate.parse(str[3]);
                        Task newTask = new Deadline(str[2], date, true);
                        list.add(newTask);
                    } else {
                        LocalDate date = LocalDate.parse(str[3]);
                        Task newTask = new Deadline(str[2], date, false);
                        list.add(newTask);
                    }
                    index++;
                } catch (DateTimeParseException ex) {
                    throw new DukeException("INVALID DATE FORMAT FOUND IN DUKE.TXT FILE. EXITING PROGRAM.");
                }
            } else if (str[0].equals("E")) {
                try {
                    if (str[1].equals("1")) {
                        LocalDate date = LocalDate.parse(str[3]);
                        Task newTask = new Event(str[2], date, true);
                        list.add(newTask);
                    } else {
                        LocalDate date = LocalDate.parse(str[3]);
                        Task newTask = new Event(str[2], date, false);
                        list.add(newTask);
                    }
                    index++;
                } catch (DateTimeParseException ex) {
                    throw new DukeException("INVALID DATE FORMAT FOUND IN DUKE.TXT FILE. EXITING PROGRAM.");
                }
            } else {
                throw new DukeException("INVALID TASK FOUND IN DUKE.TXT FILE. EXITING PROGRAM.");
            }
        }
    }

    public static void writeFile() throws IOException {
        FileWriter fw = new FileWriter("./data/duke.txt");
        String str = list.get(0).changeFormat();
        for (int i = 1; i < list.size(); i++) {
            str = str + System.lineSeparator() + list.get(i).changeFormat();
        }
        fw.write(str);
        fw.close();
    }

    /**
     * Prints out all the tasks in the list.
     */
    public static void list() {
        int num = 1;
        System.out.println("HERE ARE THE TASKS IN YOUR LIST:");
        for (Task task : list) {
            System.out.println(num + "." + task);
            num++;
        }
    }

    /**
     * Adds to do task into our current list.
     *
     * @param input string entered by the user.
     * @throws DukeException if task description is empty.
     */
    public static void addToDo(String input) throws DukeException {
        String[] str = input.split(" ");
        if (str.length == 1) {
            throw new DukeException("PLEASE INCLUDE TASK DESCRIPTION IN YOUR COMMAND.");
        }
        String desc = str[1];
        for (int i = 2; i < str.length; i++) {
            desc = desc + " " + str[i];
        }
        Task newTask = new ToDo(desc, false);
        list.add(newTask);
        System.out.println("GOT IT. I'VE ADDED THIS TASK:");
        System.out.println(newTask);
        System.out.println("NOW YOU HAVE "+ list.size() + " TASKS IN THE LIST.");
    }

    /**
     * Adds deadline task into our current list.
     *
     * @param input string entered by the user.
     * @throws DukeException if task description or date is empty.
     */
    public static void addDeadline(String input) throws DukeException {
        String[] str = input.split(" /by ");
        String[] str2 = str[0].split(" ");
        if (str2.length == 1) {
            throw new DukeException("PLEASE INCLUDE TASK DESCRIPTION IN YOUR COMMAND.");
        }
        if (str.length == 1) {
            throw new DukeException("PLEASE INCLUDE THE DATE IN YOUR COMMAND.");
        }
        String desc = str2[1];
        for (int i = 2; i < str2.length; i++) {
            desc = desc + " " + str2[i];
        }
        try {
            LocalDate date = LocalDate.parse(str[1]);
            Task newTask = new Deadline(desc, date, false);
            list.add(newTask);
            System.out.println("GOT IT. I'VE ADDED THIS TASK:");
            System.out.println(newTask);
            System.out.println("NOW YOU HAVE "+ list.size() + " TASKS IN THE LIST.");
        } catch (DateTimeParseException ex) {
            throw new DukeException("INVALID DATE FORMAT.");
        }
    }

    /**
     * Adds event task into our current list.
     *
     * @param input string entered by the user.
     * @throws DukeException if task description or date is empty.
     */
    public static void addEvent(String input) throws DukeException {
        String[] str = input.split(" /at ");
        String[] str2 = str[0].split(" ");
        if (str2.length == 1) {
            throw new DukeException("PLEASE INCLUDE TASK DESCRIPTION IN YOUR COMMAND.");
        }
        if (str.length == 1) {
            throw new DukeException("PLEASE INCLUDE THE DATE IN YOUR COMMAND.");
        }
        String desc = str2[1];
        for (int i = 2; i < str2.length; i++) {
            desc = desc + " " + str2[i];
        }
        try {
            LocalDate date = LocalDate.parse(str[1]);
            Task newTask = new Event(desc, date, false);
            list.add(newTask);
            System.out.println("GOT IT. I'VE ADDED THIS TASK:");
            System.out.println(newTask);
            System.out.println("NOW YOU HAVE "+ list.size() + " TASKS IN THE LIST.");
        } catch (DateTimeParseException ex) {
            throw new DukeException("INVALID DATE FORMAT.");
        }
    }

    /**
     * This method changes the status of a particular task to Done
     * in the list.
     *
     * @param index position of the task in the list
     * @throws DukeException if position of the task exceeds what we have on the list
     */
    public static void markTask(int index) throws DukeException {
        if (index >= list.size()) {
            throw new DukeException("☹ OOPS!!! I'M SORRY, CAN'T FIND TASK");
        } else {
            Task task = list.get(index);
            task.setAsDone();
            System.out.println("NICE! I'VE MARKED THIS TASK AS DONE:");
            System.out.println(task);
        }
    }

    /**
     * This method changes the status of a particular task to Not Done
     * in the list.
     *
     * @param index position of the task in the list
     * @throws DukeException if position of the task exceeds what we have on the list
     */
    public static void unMarkTask(int index) throws DukeException {
        if (index >= list.size()) {
            throw new DukeException("☹ OOPS!!! I'M SORRY, CAN'T FIND TASK");
        } else {
            Task task = list.get(index);
            task.setAsNotDone();
            System.out.println("NICE! I'VE MARKED THIS TASK AS NOT DONE:");
            System.out.println(task);
        }
    }


    /**
     * This method deletes Task from our list
     *
     * @param index the position of the task
     * @throws DukeException if position of the task exceeds what we have on the list
     */
    public static void deleteTask(int index) throws DukeException {
        if (index >= list.size()) {
            throw new DukeException("☹ OOPS!!! I'M SORRY, CAN'T FIND TASK");
        } else {
            Task task = list.remove(index);
            System.out.println("NOTED. I'VE REMOVED THIS TASK:");
            System.out.println(task);
            System.out.println("NOW YOU HAVE "+ list.size() + " TASKS IN THE LIST.");
        }
    }

    /**
     * This method handles the case where a random input is supplied by the user
     *
     * @throws DukeException if add type description is empty
     */
    public static void notSpecified() throws DukeException {
        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

}

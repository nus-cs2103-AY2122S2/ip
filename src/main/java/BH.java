import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;


public class BH {
    private ArrayList<Task> list;
    static Scanner sc = new Scanner(System.in);
    static String line = "\n---------------------\n";
    private static String filePath = "/Users/brandonrhan/Downloads/NUS/CS2103/ip/data/duke.txt";
    
    BH() {
        this.list = new ArrayList<Task>();
    }

    private BH(ArrayList<Task> list) {
        this.list = list;
    }
    
    void greet() {
        String logo = " ____         _   _     \n"
                + "|  _ \\       | | | |\n"
                + "| |_| |      | |-| |\n"
                + "| |_| |  _   | |-| |\n"
                + "|____/  |_|  |_| |_|\n";
        System.out.println("Hello, I am B.H. How can I help you?\n" + logo + line);
    }

    void run() throws DukeException {
        try {
            while (true) {
                String input = sc.nextLine();
                String[] inputArray = input.split(" ", 2);
                String s1 = inputArray[0].toLowerCase();
                if (s1.equals("bye")) {
                    System.out.println(line + "GoodBye! Thanks for using B.H!" + line);
                    break;
                } else if (s1.equals("list")) {
                    System.out.println(line + this.getList() + line);
                } else if (s1.equals("mark")) {
                    int index = Integer.parseInt(inputArray[1]) - 1;
                    if (index < this.getListSize()) {
                        System.out.println(line + "Well done! \n" + this.mark(index) + line);
                    } else {
                        System.out.println("Index out of range");
                    }
                } else if (s1.equals("unmark")) {
                    int index = Integer.parseInt(inputArray[1]) - 1;
                    System.out.println("Oh no! \n" + this.unmark(index));
                } else if (s1.equals("todo")) {
                    String task = inputArray[1];
                    Task newtask = new Todo(task);
                    this.addToList(newtask);
                    System.out.println(line + "Task added: " + newtask.toString() + "\n" +
                            "Now you have " + this.getListSize() + " tasks in the list" + line);
                } else if (s1.equals("deadline")) {
                    String s = inputArray[1];
                    String[] arr = s.split("/by");
                    String task = arr[0];
                    String time = arr[1];
                    Task newtask = new Deadline(task, time);
                    this.addToList(newtask);
                    System.out.println(line + "Task added:" + newtask.toString() + "\n" +
                            "Now you have " + this.getListSize() + " tasks in the list" + line);
                } else if (s1.equals("event")) {
                    String s = inputArray[1];
                    String[] arr = s.split("/at");
                    String task = arr[0];
                    String time = arr[1];
                    Task newtask = new Event(task, time);
                    this.addToList(newtask);
                    System.out.println(line + "Task added:" + newtask.toString() + "\n" +
                            "Now you have " + this.getListSize() + " tasks in the list" + line);
                } else if (s1.equals("delete")) {
                    int index = Integer.parseInt(inputArray[1]) - 1;
                    System.out.println(line + "Okay, I have remove this task:\n" +
                            this.deleteTask(index) + line);
                } else if (s1.equals("check")) {
                    LocalDate date = LocalDate.parse(inputArray[1]);
                    this.checkDate(date);
                } else {
                    System.out.println("Wrong input, please check again");
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
            throw new DukeException("Duke exception!!!");
        }
    }

    void load() throws DukeException {
        try {
            FileReader reader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String input = bufferedReader.readLine();
            while (input != null) {
                String[] task = input.split(" ### ");
                String type = task[0];
                String status = task[1];
                String thing = task[2];
                if (type.equals("T")) {
                    Task newTask = new Todo(thing);
                    if (status.equals("1")) {
                        newTask.mark();
                    }
                    this.addToList(newTask);
                } else if (type.equals("D")) {
                    Task newTask = new Deadline(thing, task[3]);
                    if (status.equals("1")) {
                        newTask.mark();
                    }
                    this.addToList(newTask);
                } else if (type.equals("E")) {
                    Task newTask = new Event(thing, task[3]);
                    if (status.equals("1")) {
                        newTask.mark();
                    }
                    this.addToList(newTask);
                }
                input = bufferedReader.readLine();
            }
        } catch (IOException | ArrayIndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
            throw new DukeException("Loading error");
        }
    }

    void checkDate(LocalDate date) {
        System.out.println(line);
        for (int i = 0; i < this.getListSize(); i++) {
            Task currTask = this.list.get(i);
            if (currTask.getDate().equals(date)) {
                System.out.println(currTask.toString());
            }
        }
        System.out.println(line);
    }

    void save() {
        try {
            FileWriter writer = new FileWriter(filePath);
            BufferedWriter br = new BufferedWriter(writer);
            for (int i = 0; i < this.getListSize(); i++) {
                br.write(this.list.get(i).saveFormat());
                br.newLine();
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void addToList(Task task) {
        this.list.add(task);
    }

    Task deleteTask(int index) {
        Task task =  this.list.get(index);
        this.list.remove(index);
        return task;
    }

    String getList() {
        String s = "";
        for (int i = 0; i < this.list.size(); i++) {
            s = s + (i + 1) + ". " + list.get(i) + "\n";
        }
        return s;
    }

    int getListSize() {
        return this.list.size();
    }

    String mark(int index) {
        this.list.get(index).mark();
        return this.list.get(index).toString();
    }

    String unmark(int index) {
        this.list.get(index).unmark();
        return this.list.get(index).toString();
    }
}

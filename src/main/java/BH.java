import java.util.Scanner;
import java.util.ArrayList;

public class BH {
    private ArrayList<Task> list;
    static Scanner sc = new Scanner(System.in);
    static String line = "\n---------------------\n";
    
    BH() {
        this.list = new ArrayList<Task>();
    }
    
    void greet() {
        String logo = " ____         _   _     \n"
                + "|  _ \\       | | | |\n"
                + "| |_| |      | |-| |\n"
                + "| |_| |  _   | |-| |\n"
                + "|____/  |_|  |_| |_|\n";
        System.out.println("Hello, I am B.H. How can I help you?\n" + logo + line);
    }
    
    void addToList(Task task) {
        this.list.add(task);
    }

    void run() {
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
                } else if (s1.equals("mark") && inputArray.length > 1) {
                    int index = Integer.parseInt(inputArray[1]) - 1;
                    if (index < this.getListSize()) {
                        System.out.println(line + "Well done! \n" + this.mark(index) + line);
                    } else {
                        System.out.println("Index out of range");
                    }
                } else if (s1.equals("unmark") && inputArray.length > 1) {
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
                }
                else {
                    throw new DukeException("Wrong input");
                }
            }
        } catch (DukeException e) {
            System.out.println(line + "Please check your input" + line);
        }
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

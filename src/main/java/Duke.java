import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;
import java.io.FileWriter;
import java.io.FileReader;

public class Duke {

    public static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        setUpData();
        try {
            startConversation();
        } catch (DukeException e) {
            System.out.println(e);
        }
    }

    public static void startConversation() throws DukeException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! I'm Duke :) \nWhat can I do for you? :D");
        String inputString = sc.nextLine();
        String[] inputStringArray = inputString.split(" ");

        while (!inputStringArray[0].equals("bye")) {
            switch (inputStringArray[0]) {
                case "list":
                    displayList();
                    break;
                case "mark":
                    mark(inputStringArray[1]);
                    break;
                case "unmark":
                    unmark(inputStringArray[1]);
                    break;
                case "todo":
                case "deadline":
                case "event":
                    addToList(inputStringArray);
                    break;
                case "delete":
                    delete(inputStringArray[1]);
                    break;
                default:
                    throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means O.o");
            }
            inputString = sc.nextLine();
            inputStringArray = inputString.split(" ");
        }
        System.out.println("Bye. Hope to see you again soon! :))");
    }

    public static void displayList() {
        String returnString = "";
        for (int i = 0; i < tasks.size(); i++) {
            returnString = returnString + (i + 1) + ". [" + tasks.get(i).symbol() + "][" + tasks.get(i).getStatusIcon()
                    + "] " + tasks.get(i) + "\n";
        }
        System.out.println(returnString);
    }

    public static void addToList(String[] stringToAdd) throws DukeException {
        if (stringToAdd.length < 2) {
            throw new DukeException("OOPS!! The description of a " + stringToAdd[0] + " cannot be empty.");
        } else {
            Task task;
            String returnstring = "";
            for (int i = 1; i < stringToAdd.length; i++) {
                returnstring = returnstring + stringToAdd[i] + " ";
            }
            if (stringToAdd[0].equals("todo")) {
                task = new Todo(returnstring);
            } else if (stringToAdd[0].equals("deadline")) {
                task = new Deadline(returnstring);
            } else {
                task = new Event(returnstring);
            }
            tasks.add(task);
            System.out.println("Got it!! :D I've added this task:\n" + " [" + task.symbol() + "][] " + returnstring +
                    "\nNow you have " + tasks.size() + " tasks in the list.");
        }
        updateData();
    }

    public static void mark(String number) {
        int num = Integer.parseInt(number);
        tasks.get(num - 1).setAsDone();
        Task temp = tasks.get(num - 1);
        System.out.println("Nice! :P I've marked this task as done:\n [" + temp.symbol() + "][" +
                temp.getStatusIcon() + "] " + temp);
        updateData();
    }

    public static void unmark(String number) {
        int num = Integer.parseInt(number);
        tasks.get(num - 1).setAsNotDone();
        Task temp = tasks.get(num - 1);
        System.out.println("OK ._. , I've marked this task as not done yet:\n [" + temp.symbol() + "][" +
                temp.getStatusIcon() + "] " + temp);
        updateData();
    }

    public static void delete(String number) {
        int num = Integer.parseInt(number);
        Task temp = tasks.get(num - 1);
        System.out.println("Noted. I've removed this task:\n [" + temp.symbol() + "][" +
                temp.getStatusIcon() + "] " + temp +
                "\nNow you have " + (tasks.size() - 1) + " tasks left in this list");
        tasks.remove(num - 1);
        updateData();
    }

    public static void updateData() {
        try {
            FileWriter myObj = new FileWriter("data.txt");
            myObj.flush();
            for (int i = 0; i < tasks.size(); i++) {

                        tasks.get(i).toString() + "\n");
            }
            myObj.close();
        } catch (IOException e) {
            System.out.println("File does not exist.");
        }
    }

    public static void setUpData() {
        try {
            FileReader myObj = new FileReader("data.txt");
            BufferedReader br = new BufferedReader(myObj);
            String line;
            while ((line = br.readLine()) != null) {

                Task task;
                switch (lineArray[0]) {
                    case "T":
                        task = new Todo(lineArray[2]);
                        break;
                    case "E":

                }
                if (lineArray[1].equals("X")) {
                    task.setAsDone();
                }
                tasks.add(task);
            }
        } catch (IOException e) {
            System.out.println("File does not exist.");
        }
    }
}

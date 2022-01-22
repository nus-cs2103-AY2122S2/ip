import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;


public class Duke {
    static String line = "------------------------------------";

    public void hello() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("How may I help you today?");
        System.out.println(line);
    }

    public void exit(ArrayList<Task> arr) {
        save(arr);
        System.out.println("Goodbye! I'll be here if you need anything else.");
        System.out.println(line);
    }

    public void list(ArrayList<Task> arr) {
        for (int i = 0; i < arr.size(); i++) {
            System.out.println(i + 1 + "." + arr.get(i).toString());
        }
        System.out.println(line);
    }

    public void mark(String input, ArrayList<Task> arr) {
        try {
            String s = input.replaceAll("\\D+", "");
            int clean = Integer.parseInt(s) - 1;  // Parse to find what number in list to toggle
            // Edge Case
            if (clean > arr.size()) {
                System.out.println("Error! No tasked added");
            }
            // Mark
            else if (input.toCharArray()[0] != 'u') {
                arr.get(clean).setMarked();
                System.out.println("Nice! I've marked this task as done:\n " +
                        "   " + arr.get(clean).toString());
            }
            // Unmark
            else {
                arr.get(clean).setUnmarked();
                System.out.println("OK, I've marked this task as not done yet:\n " +
                        "   " + arr.get(clean).toString());
            }
        } catch (NumberFormatException e) {
            System.out.println("Don't be cheeky. Please write something that makes sense.");
        }
        System.out.println(line);
    }

    public void delete(String input, ArrayList<Task> arr) {
        try {
            String s = input.replaceAll("\\D+", "");
            int delete = Integer.parseInt(s) - 1;  // Parse to find what number in list to delete
            // Edge Case
            if (delete > arr.size()) {
                System.out.println("Error! Nothing to delete!");
            } else {
                System.out.println("Noted. I've removed this task: \n   " +
                        arr.get(delete).toString());
                arr.remove(delete);
                System.out.println("Now you have " + arr.size() + " tasks in the list.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error! Nothing to delete!");
        }
        System.out.println(line);
    }

    public void deadline(String input, ArrayList<Task> arr) {
        try {
            String nondead = input.split("deadline ", 2)[1]; // Remove instruction
            String task = nondead.split(" /by ", 2)[0]; // Split to task
            String date = nondead.split(" /by ", 2)[1]; // Split to date
            Deadline newDeadline = new Deadline(task, date);
            arr.add(newDeadline);
            System.out.println("Got it! I've added this task: \n    " +
                    newDeadline.toString() + "\n" +
                    "Now you have " + arr.size() + " tasks in the list.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Don't be cheeky. Give me a deadline to stress you over.");
        }
        System.out.println(line);
    }

    public void event(String input, ArrayList<Task> arr) {
        try {
            String nonevent = input.split("event ", 2)[1]; // Remove instruction
            String task = nonevent.split(" /at ", 2)[0]; // Split to task
            String date = nonevent.split(" /at ", 2)[1]; // Split to date
            Event newEvent = new Event(task, date);
            arr.add(newEvent);
            System.out.println("Got it! I've added this task: \n    " +
                    newEvent.toString() + "\n" +
                    "Now you have " + arr.size() + " tasks in the list.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Don't be cheeky. Give me an event to record.");
        }
        System.out.println(line);
    }

    public void todo(String input, ArrayList<Task> arr) {
        try {
            String word = input.split(" ", 2)[1]; // Remove instruction
            ToDo newToDo = new ToDo(word);
            arr.add(newToDo);
            System.out.println("Got it! I've added this task: \n    " +
                    newToDo.toString() + "\n" +
                    "Now you have " + arr.size() + " tasks in the list.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Don't be cheeky. Give me something to do.");
        }
        System.out.println(line);
    }

    public void save(ArrayList<Task> arr) {
        File directory = new File("data");
        if (!directory.exists()) {
            directory.mkdirs();
        }
        try {
            FileOutputStream writeData = new FileOutputStream("data/duke.ser");
            ObjectOutputStream writeStream = new ObjectOutputStream(writeData);

            writeStream.writeObject(arr);
            writeStream.flush();
            writeStream.close();
        }
        catch (IOException e) {
            System.out.println("Something went wrong. I think I may be corrupted.");
            System.out.println(line);
        }
    }

    public LocalDate getDate(String s) {
        return LocalDate.parse(s);
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> arr = new ArrayList<>();
        Duke robot = new Duke();
        robot.hello();

        // Load
        try {
            FileInputStream readData = new FileInputStream("data/duke.ser");
            ObjectInputStream readStream = new ObjectInputStream(readData);

            arr = (ArrayList<Task>) readStream.readObject();
            readStream.close();
        }
        catch (Exception e) {
            robot.save(arr);
        }

        // Let's Go!
        while (true) {
            String input = scanner.nextLine();
            // Exit Feature
            if (input.equals("bye")) {
                robot.exit(arr);
                break;
            }
            // List Feature
            else if (input.equals("list")) {
                robot.list(arr);
            }
            // Mark & Unmark Feature
            else if (input.contains("mark")) {
                robot.mark(input, arr);
            }
            // Delete Feature
            else if (input.contains("delete")) {
                robot.delete(input, arr);
            }
            // Deadline Feature
            else if (input.contains("deadline")) {
                robot.deadline(input, arr);
            }
            // Event Feature
            else if (input.contains("event")) {
                robot.event(input, arr);
            }
            // To Do Feature
            else if (input.contains("todo")) {
                robot.todo(input, arr);
            }
            // Edge Case
            else {
                System.out.println(input + " is not a valid command. Please try again.");
                System.out.println(line);
            }
        }
    }
}



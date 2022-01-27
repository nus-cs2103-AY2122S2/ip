package duke.duke;
import java.util.ArrayList;

public class TaskList {

    protected ArrayList<Task> arr;

    public TaskList(ArrayList<Task> arr) {
        this.arr = arr;
    }

    public void list(ArrayList<Task> arr) {
        for (int i = 0; i < arr.size(); i++) {
            System.out.println(i + 1 + "." + arr.get(i).toString());
        }
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
    }
}

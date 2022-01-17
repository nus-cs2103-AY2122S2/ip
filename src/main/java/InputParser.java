import java.util.ArrayList;

public class InputParser {
    public int run(String ss, Printer p, ArrayList<Task> arr) {
        String[] args = ss.split("\\s+");
        String action = args[0];
        if(action.equals("bye")) {
            p.print(" Bye. Hope to see you again soon!");
            return -1;
        } else if (action.equals("list")) {
            p.print(arr);
        } else if (action.equals("mark")) {
            arr.get(Integer.parseInt(args[1]) - 1).mark();
            p.print("Nice! I've marked this task as done: "," " + arr.get(Integer.parseInt(args[1]) - 1).toString());
        } else if (action.equals("unmark")) {
            arr.get(Integer.parseInt(args[1]) - 1).unmark();
            p.print("OK, I've marked this task as not done yet: "," " + arr.get(Integer.parseInt(args[1]) - 1).toString());
        } else if (action.equals("todo")) {
            ToDoTask newTask = new ToDoTask((ss).substring(5, ss.length()));
            arr.add(newTask);
            p.print("Added Task: ", " " + newTask.toString(), String.format("There are now %d task(s) in the list.", arr.size()));
        } else if (action.equals("deadline")) {
            DeadlineTask newTask = new DeadlineTask(ss.substring(9, ss.length()).split("/by")[0], ss.split("/by")[1]);
            arr.add(newTask);
            p.print("Added Task: ", " " + newTask.toString(), String.format("There are now %d task(s) in the list.", arr.size()));
        } else if (action.equals("event")) {

        }
        else {
            //arr.add(new Task(ss));
            p.print(String.format("added: %s", ss));
        }
        return 0;
    }
}

import java.util.ArrayList;

public class InputParser {
    public int run(String ss, Printer p, ArrayList<Task> arr) throws DukeException {
        try {
            String[] args = ss.split("\\s+");
            String action = args[0];
            if(action.equals("bye")) {
                p.print(" Bye. Hope to see you again soon!");
                return -1;
            } else if (action.equals("list")) {
                p.print(arr);
            } else if (action.equals("mark")) {
                //===========ERROR HANDLING============================
                if(args.length == 1)
                    throw new DukeException("Task ID must be provided!");
                try {
                    Integer.parseInt(args[1]);
                } catch(NumberFormatException e) {
                    throw new DukeException("Task ID must be an integer!");
                }
                if(arr.size() == 0)
                    throw new DukeException("Task List is empty!");
                if(Integer.parseInt(args[1]) > arr.size())
                    throw new DukeException("Task ID out of range!");
                //=======================================================
                arr.get(Integer.parseInt(args[1]) - 1).mark();
                p.print("Nice! I've marked this task as done: ", " " + arr.get(Integer.parseInt(args[1]) - 1).toString());
            } else if (action.equals("unmark")) {
                //===========ERROR HANDLING============================
                if(args.length == 1)
                    throw new DukeException("Task ID must be provided!");
                try {
                    Integer.parseInt(args[1]);
                } catch(NumberFormatException e) {
                    throw new DukeException("Task ID must be an integer!");
                }
                if(arr.size() == 0)
                    throw new DukeException("Task List is empty!");
                if(Integer.parseInt(args[1]) > arr.size())
                    throw new DukeException("Task ID out of range!");
                //=======================================================
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
                EventTask newTask = new EventTask(ss.substring(6, ss.length()).split("/at")[0], ss.split("/at")[1]);
                arr.add(newTask);
                p.print("Added Task: ", " " + newTask.toString(), String.format("There are now %d task(s) in the list.", arr.size()));
            }
            else {
                throw new DukeException("Unknown Command");
            }
        } catch (DukeException de) {
            System.out.println(de.getMessage());
        }
        return 0;
    }
}

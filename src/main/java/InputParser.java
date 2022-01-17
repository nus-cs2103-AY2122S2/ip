import java.util.ArrayList;
import java.util.Arrays;

public class InputParser {
    public int run(String ss, Printer p, ArrayList<Task> arr) throws DukeException {
        try {
            String[] args = ss.split("\\s+");
            String action = args[0];
            if(action.equals("bye")) {
                p.print("Bye. Hope to see you again soon!");
                return -1;
            } else if (action.equals("list")) {
                // Empty List handled by Printer
                p.print(arr);
            } else if (action.equals("mark")) {

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

                arr.get(Integer.parseInt(args[1]) - 1).mark();
                p.print("Task marked as done: ", " " + arr.get(Integer.parseInt(args[1]) - 1).toString());
            } else if (action.equals("unmark")) {

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

                arr.get(Integer.parseInt(args[1]) - 1).unmark();
                p.print("Task as not done yet: "," " + arr.get(Integer.parseInt(args[1]) - 1).toString());
            } else if (action.equals("todo")) {
                if(args.length == 1)
                    throw new DukeException("Task Name must be provided!");

                ToDoTask newTask = new ToDoTask((ss).substring(5, ss.length()));
                arr.add(newTask);
                p.print("Added Task: ", " " + newTask.toString(), String.format("There are now %d task(s) in the list.", arr.size()));
            } else if (action.equals("deadline")) {

                if(args.length == 1)
                    throw new DukeException("Task Name must be provided!");
                if(!Arrays.stream(args).anyMatch("/by"::equals))
                    throw new DukeException("/by flag not detected. Please specify deadline date using /by!");
                if(ss.trim().split("/by").length == 1)
                    throw new DukeException("Please specify deadline date after /by!");
                if (ss.substring(9, ss.length()).split("/by")[0].trim().equals(""))
                    throw new DukeException("Task Name must be provided!");

                DeadlineTask newTask = new DeadlineTask(ss.substring(9, ss.length()).split("/by")[0], ss.split("/by")[1]);
                arr.add(newTask);
                p.print("Added Task: ", " " + newTask.toString(), String.format("There are now %d task(s) in the list.", arr.size()));
            } else if (action.equals("event")) {

                if(args.length == 1)
                    throw new DukeException("Task Name must be provided!");
                if(!Arrays.stream(args).anyMatch("/at"::equals))
                    throw new DukeException("/at flag not detected. Please specify event location using /at!");
                if(ss.trim().split("/at").length == 1)
                    throw new DukeException("Please specify deadline date after /at!");
                if (ss.substring(6, ss.length()).split("/at")[0].trim().equals(""))
                    throw new DukeException("Task Name must be provided!");

                EventTask newTask = new EventTask(ss.substring(6, ss.length()).split("/at")[0], ss.split("/at")[1]);
                arr.add(newTask);
                p.print("Added Task: ", " " + newTask.toString(), String.format("There are now %d task(s) in the list.", arr.size()));
            } else if (action.equals("delete")) {

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

                Task deletedTask = arr.get(Integer.parseInt(args[1]) - 1);
                arr.remove(Integer.parseInt(args[1]) - 1);
                p.print("Deleted Task:", " " + deletedTask.toString(), String.format("There are now %d task(s) in the list.", arr.size()));
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

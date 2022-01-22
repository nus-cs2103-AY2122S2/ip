import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

public class InputParser {
    public int run(String ss, Printer p, ArrayList<Task> arr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-M-yyyy hh:mm");
        String[] args = ss.split("\\s+");
        String action = args[0];
        try {
            validate(ss, action, args, arr);
        } catch (DukeException de) {
            System.out.println(de.getMessage());
            return 1;
        }
        switch(action){
            case "bye":
                p.print("Bye. Hope to see you again soon!");
                return -1;
            case "list":
                p.print(arr);
            case "mark":
                arr.get(Integer.parseInt(args[1]) - 1).mark();
                p.print("Task marked as done: ", " " + arr.get(Integer.parseInt(args[1]) - 1).toString());
                break;
            case "unmark":
                arr.get(Integer.parseInt(args[1]) - 1).unmark();
                p.print("Task as not done yet: "," " + arr.get(Integer.parseInt(args[1]) - 1).toString());
                break;
            case "todo":
                arr.add(new ToDoTask((ss).substring(5, ss.length())));
                p.print("Added Task: ", " " + arr.get(arr.size() - 1).toString(), String.format("There are now %d task(s) in the list.", arr.size()));
                break;
            case "deadline":
                arr.add(new DeadlineTask(ss.substring(9, ss.length()).split("/by")[0], LocalDate.parse(ss.split("/by")[1].substring(1, ss.split("/by")[1].length()),formatter)));
                p.print("Added Task: ", " " + arr.get(arr.size() - 1).toString(), String.format("There are now %d task(s) in the list.", arr.size()));
                break;
            case "event":
                arr.add(new EventTask(ss.substring(6, ss.length()).split("/at")[0], LocalDate.parse(ss.split("/at")[1].substring(1, ss.split("/at")[1].length()),formatter)));
                p.print("Added Task: ", " " + arr.get(arr.size() - 1).toString(), String.format("There are now %d task(s) in the list.", arr.size()));
                break;
            case "delete":
                Task deletedTask = arr.get(Integer.parseInt(args[1]) - 1);
                arr.remove(Integer.parseInt(args[1]) - 1);
                p.print("Deleted Task:", " " + deletedTask.toString(), String.format("There are now %d task(s) in the list.", arr.size()));
                break;
            default:
                System.out.println("Unknown Command");
        }
        return 0;

    }

    public void validate(String input, String action, String[] args, ArrayList<Task> arr) throws DukeException {
        switch(action) {
            case "mark":
            case "unmark":
            case "delete":
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
                break;
            case "todo":
                if(args.length == 1)
                    throw new DukeException("Task Name must be provided!");
                break;
            case "deadline":
            case "event":
                String flag = action == "deadline" ? "/by" : "/at";
                int actionLength = action == "deadline" ? 9 : 6;
                if(args.length == 1)
                    throw new DukeException("Task Name must be provided!");
                if(!Arrays.stream(args).anyMatch(flag::equals))
                    throw new DukeException(String.format("%s flag not detected. Please specify date using %s!", flag, flag));
                if(input.trim().split(flag).length == 1)
                    throw new DukeException(String.format("Please specify deadline date after %s!",flag));
                if (input.substring(actionLength, input.length()).split(flag)[0].trim().equals(""))
                    throw new DukeException("Task Name must be provided!");
                break;
        }
    }
}

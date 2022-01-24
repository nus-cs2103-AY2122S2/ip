import javax.swing.text.DateFormatter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static boolean findKeyword(String[] nextLineArr, String keyword){
        for(int i = 0; i < nextLineArr.length; i++) {
            if(nextLineArr[i].equals(keyword)) {
                return true;
            }
        }
        return false;
    }
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-mm-dd");
    public static boolean isDate(String possibleDate) {
        try{
            LocalDate date = LocalDate.parse(possibleDate);
            return true;
        } catch(DateTimeParseException e) {
            return false;
        }
    }
    public static void main(String[] args){
        String greeting = "Hello! I'm Duke\n" + "What can I do for you?";
        System.out.println(greeting);

        TaskList.initFile();
        TaskList taskList = new TaskList();
        taskList.load();


        Scanner scanner = new Scanner(System.in);
        String input = null;
        String bye = "bye";



        while(scanner.hasNextLine()) {
            try {
                input = scanner.nextLine().trim();
                String[] strArr = input.split(" ");
                String command = strArr[0];
                if(!(command.equals("bye") || command.equals("list") || command.equals("mark") || command.equals("todo")
                        || command.equals("deadline") || command.equals("event") || command.equals("delete"))) {
                    throw new DukeInvalidCommandException();
                }
                if (command.equals(bye)) {
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                }
                if (command.equals("list")) {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 1; i <= taskList.size(); i++) {
                        int index = i - 1;
                        System.out.println(i + ". " + taskList.get(index).toString());
                    }
                    continue;
                }
                if (command.equals("mark")) {
                    int index = Integer.parseInt(strArr[1]) - 1;
                    Task temp;
                    try{
                        temp = taskList.get(index);
                    } catch(IndexOutOfBoundsException e) {
                        throw new DukeInvalidIndexException();
                    }
                    temp.isDone = true;
                    System.out.println("Nice! I've marked this task as done:\n" + temp.toString());
                    continue;
                }

                if (command.equals("todo")) {
                    String title;
                    try{
                        title = input.substring("todo".length() + 1).trim();
                    }
                    catch (StringIndexOutOfBoundsException e){
                        throw new DukeEmptyArgumentException();
                    }

                    System.out.println("Got it. I've added this task:");
                    Task task = new Todo(title);
                    taskList.insert(task);
                    System.out.println("   " + task.toString() + "\n" + "Now you have " + taskList.size() + " tasks in the list.");
                }

                if (command.equals("deadline")) {
                    if (strArr.length == 1) throw new DukeEmptyArgumentException();
                    if (findKeyword(strArr, "/by")) {
                        String title;
                        String[] splitArr = input.split("/by", 2);
                        try{
                            title = splitArr[0].substring("deadline".length() + 1).trim();
                        }
                        catch (StringIndexOutOfBoundsException e){
                            throw new DukeEmptyArgumentException();
                        }
                        String time = splitArr[1].trim();
                        System.out.println("Got it. I've added this task:");
                        Task task;
                        if(isDate(time)) {
                            LocalDate ld = LocalDate.parse(time);
                            task = new Deadline(title, ld);
                        } else {
                            task = new Deadline(title, time);
                        }
                        taskList.insert(task);
                        System.out.println("   " + task.toString() + "\n" + "Now you have " + taskList.size() + " tasks in the list.");
                    }
                    else {
                        throw new DukeMissingArgumentException("/by");
                    }
                }
                if (command.equals("event")) {
                    if (strArr.length == 1) throw new DukeEmptyArgumentException();
                    if (findKeyword(strArr, "/at")) {
                        String title;
                        String[] splitArr = input.split("/at", 2);

                        try{
                            title = splitArr[0].substring("event".length() + 1).trim();
                        }
                        catch (StringIndexOutOfBoundsException e){
                            throw new DukeEmptyArgumentException();
                        }
                        String time = splitArr[1].trim();
                        System.out.println("Got it. I've added this task:");
                        Task task;
                        if(isDate(time)) {
                            LocalDate ld = LocalDate.parse(time);
                            task = new Event(title, ld);
                        } else {
                            task = new Event(title, time);
                        }
                        taskList.insert(task);
                        System.out.println("   " + task.toString() + "\n" + "Now you have " + taskList.size() + "tasks in the list.");
                    }
                    else {
                        throw new DukeMissingArgumentException("/at");
                    }
                }
                if (command.equals("delete")){
                    int index = Integer.parseInt(strArr[1]) - 1;
                    Task task;
                    try{
                        task = taskList.get(index);
                        taskList.delete(index);
                    } catch(IndexOutOfBoundsException e) {
                        throw new DukeInvalidIndexException();
                    }
                    System.out.println("Noted. I've removed this task:");
                    System.out.println(task.toString());
                    System.out.println("Now you have " + taskList.size() + " tasks in the list.");

                }
            }
            catch (DukeException e) {
                System.out.println(e.toString());
            }
        }
        taskList.save();
    }
}

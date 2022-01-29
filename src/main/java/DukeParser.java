import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class DukeParser {

    public static ArrayList<Task> readData(File f) throws IOException {
        Scanner s = new Scanner(f);
        ArrayList<Task> ans = new ArrayList<Task>();
        while(s.hasNext()){
            String[] k = s.nextLine().split("\\|");
            if(k[0].equals("T")){
                ToDos j = new ToDos(k[2]);
                if(k[1].equals("true")){
                    j.done = true;
                }
                ans.add(j);
            }
            else if(k[0].equals("D")){
                Task j = new Deadlines(k[2],k[3]);
                if(k[1].equals("true")){
                    j.done = true;
                }
                ans.add(j);
            }
            else if(k[0].equals("E")){
                Task j = new Events(k[2],k[3]);
                if(k[1].equals("true")){
                    j.done = true;
                }
                ans.add(j);
            }
        }
        return ans;
    }

    public static boolean isValidDate(String k) {
        try{
            LocalDate d = LocalDate.parse(k);
            return true;
        }catch(DateTimeParseException e) {
            return false;
        }
    }

    public static boolean isInt(String i){
        try{
            int n = Integer.parseInt(i);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public static Command createCommand(String[] arg, String cmd) throws DukeException{
        if(cmd.equals("bye")){
            if(arg.length == 1){
                return new ExitCommand();
            } else {
                throw new WrongFormatException();
            }
        } else if(cmd.equals("mark")){
            if(arg.length == 1){
                throw new MissingArgumentException();
            } else if (!isInt(arg[1])){
                throw new WrongFormatException();
            } else {
                return new MarkCommand(Integer.parseInt(arg[1].trim()));
            }
        } else if(cmd.equals("unmark")){
            if(arg.length == 1){
                throw new MissingArgumentException();
            } else if (!isInt(arg[1])){
                throw new WrongFormatException();
            } else {
                return new UnMarkCommand(Integer.parseInt(arg[1].trim()));
            }
        } else if(cmd.equals("delete")){
            if(arg.length == 1){
                throw new MissingArgumentException();
            } else if (!isInt(arg[1])){
                throw new WrongFormatException();
            } else {
                return new DeleteCommand(Integer.parseInt(arg[1].trim()));
            }
        } else if(cmd.equals("today")){
            if(arg.length == 1) {
                return new TodayTask();
            } else {
                throw new WrongFormatException();
            }
        } else if(cmd.equals("list")){
            if(arg.length == 1){
                return new ListCommand();
            } else {
                throw new WrongFormatException();
            }
        } else if(cmd.equals("todo")){
            if(arg.length == 1){
                throw new MissingArgumentException();
            } else {
                return new AddToDos(arg[1]);
            }
        } else if(cmd.equals("deadline")){
            if(arg.length == 1){
                throw new MissingArgumentException();
            } else if (!arg[1].matches(".+/by.+")) {
                throw new WrongFormatException();
            } else {
                String[] body = arg[1].split("/by");
                if(!isValidDate(body[1].trim())){
                    throw new InvalidDateException();
                } else {
                    return new AddDeadline(body[0], body[1].trim());
                }
            }
        } else if(cmd.equals("event")){
            if(arg.length == 1){
                throw new MissingArgumentException();
            } else if (!arg[1].matches(".+/at.+")) {
                throw new WrongFormatException();
            } else {
                String[] body = arg[1].split("/at");
                if(!isValidDate(body[1].trim())){
                    throw new InvalidDateException();
                } else {
                    return new AddEvents(body[1], body[1].trim());
                }
            }
        } else {
            throw new InvalidCommandException();
        }

    }

    public static Command parseInput(String inp){
        String[] arg = inp.split(" ", 2);
        try {
            Command c = createCommand(arg, arg[0]);
            return c;
        } catch(DukeException e) {
            System.out.println(e.getMessage());
            return new TryAgain();
        }
    }



}

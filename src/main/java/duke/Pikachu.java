package duke;
import java.io.*;
import java.time.*;
import java.util.*;

import duke.UI.UI;
import duke.tasks.*;


public class Pikachu {
    private ArrayList<Task> inputList;

    //Constructor
    public Pikachu() {
        inputList = new ArrayList<Task>();
    }

    //Constructor for existing tasklist
    public Pikachu(ArrayList<Task> inputList) {
        this.inputList = inputList;
    }

    //Accessor
    public ArrayList<Task> getInputList() {
        return this.inputList;
    }

    /**
     * Parses input from the user. This is the main command for Pikachu.
     *
     * @param str Input from user.
     */
    public String parseInput(String str) {
        String[] split = str.split(" ");

        //"commands"
        //Prints list of available commands.
        //Syntax: commands
        if (str.toLowerCase().equals("commands")) {
            return UI.printCommands();
        }

        //"list"
        //Prints current list of tasks in tasklist.
        //Syntax: list
        if (str.toLowerCase().equals("list")) {
            //System.out.println("list command reached!");
            String result;
            result = "Here are the tasks in your list:\n";
            int count = 1;
            for (Task t : inputList) {
                result += ("   " + count + ". " + t + "\n");
                count += 1;
            }
            count -= 1;
            result += "Now you have " + count + " task(s) in the list.";
            return result;
        }

        //"mark"
        //Marks the task at the given index as done.
        //Syntax: mark <index of task>
        if (split[0].toLowerCase().equals("mark")) {
            int index = 0;
            try {
                index = Integer.parseInt(split[1]) - 1;
            } catch (Exception e) {
                return "Invalid input for mark!";
            }
            if (index >= inputList.size() || index <= -1) { //Prevent invalid array accesses
                return "Invalid task number!";
            }
            Task t = inputList.get(index);
            t.mark();

            return "Pikachu has marked this task as done!\n   > " + t;
        }

        //"unmark"
        //Marks the task at the given index as not done.
        //Syntax: unmark <index of task>
        if (split[0].toLowerCase().equals("unmark")) {
            int index = 0;
            try {
                index = Integer.parseInt(split[1]) - 1;
            } catch (Exception e) {
                return "Invalid input for unmark!";
            }
            if (index >= inputList.size() || index <= -1) {
                return "Invalid task number!";
            }
            Task t = inputList.get(index);
            t.unmark();

            return "Pikachu has marked this task as not done yet!\n   > " + t;
        }

        //"delete"
        //Deletes the task at the given index.
        //Syntax: delete <index of task>
        if (split[0].toLowerCase().equals("delete")) {
            int index = 0;
            try {
                index = Integer.parseInt(split[1]) - 1;
            } catch (Exception e) {
                return "Invalid input for delete!";
            }
            if (index >= inputList.size() || index <= -1) { //Prevent invalid array accesses
                return "Invalid task number!";
            }
            Task t = inputList.remove(index);

            return "Pikachu has deleted this task!\n   > " + t + "\n"
                    + "You now have " + inputList.size() + " tasks in the list.";
        }

        //"todo"
        //Creates a todo task.
        //Syntax: todo <taskname>
        if (split[0].toLowerCase().equals("todo")) {
            String[] split2 = str.split(" ", 2);
            //System.out.printf("For debugging. split2[1] = %s\n", split2[1]);
            try {
                ToDo t = new ToDo(split2[1]);
                inputList.add(t);
                return "Pikachu has added this task to the list!\n" + "   > " + t + "\n"
                        + "You now have " + inputList.size() + " tasks in the list.";
            } catch (Exception e) {
                return "Task description is empty!";
            }
        }

        //"deadline"
        //Creates a deadline task.
        //Syntax: deadline <taskname> /<yyyy-mm-dd hhmm of deadline>
        if (split[0].toLowerCase().equals("deadline")) {
            try {
                String[] split2 = str.split("/");
                String[] split3 = split2[1].split(" ");
                String[] split4 = split2[0].split(" ", 2);
                String name = split4[1];
                String date = split3[0];
                String time = split3[1];
                String[] yymmdd = date.split("-");
                LocalDateTime deadline = LocalDateTime.of(Integer.parseInt(yymmdd[0]), Integer.parseInt(yymmdd[1]),
                        Integer.parseInt(yymmdd[2]), Integer.parseInt(time.substring(0, 2)),
                        Integer.parseInt(time.substring(2, 4)));
                //System.out.printf("For debugging. split2[1] = %s, split3[1] = %s\n", split2[1], split3[1]);
                Deadline d = new Deadline(name, deadline);
                inputList.add(d);
                return "Pikachu has added this task to the list!\n" + "   > " + d + "\n"
                        + "You now have " + inputList.size() + " tasks in the list.";
            } catch (Exception e) {
                return "Task description is empty/Empty or invalid deadline timing has been specified!";
            }
        }

        //"event"
        //Creates an event task.
        //Syntax: event <taskname> /<yyyy-mm-dd hhmm of start> <yymmdd hhmm of end>
        if (split[0].toLowerCase().equals("event")) {
            try {
                String[] split2 = str.split("/");
                String[] split3 = split2[1].split(" ");
                String[] split4 = split2[0].split(" ", 2);
                String name = split4[1];
                String dateStart = split3[0];
                String timeStart = split3[1];
                String dateEnd = split3[2];
                String timeEnd = split3[3];
                String[] yymmddStart = dateStart.split("-");
                String[] yymmddEnd = dateEnd.split("-");
                LocalDateTime start = LocalDateTime.of(Integer.parseInt(yymmddStart[0]),
                        Integer.parseInt(yymmddStart[1]), Integer.parseInt(yymmddStart[2]),
                        Integer.parseInt(timeStart.substring(0, 2)), Integer.parseInt(timeStart.substring(2, 4)));
                LocalDateTime end = LocalDateTime.of(Integer.parseInt(yymmddEnd[0]), Integer.parseInt(yymmddEnd[1]),
                        Integer.parseInt(yymmddEnd[2]), Integer.parseInt(timeEnd.substring(0, 2)),
                        Integer.parseInt(timeEnd.substring(2, 4)));
                if (end.isBefore(start)) {
                    return "End time cannot be earlier than start time!";
                }
                //System.out.printf("For debugging. split2[1] = %s, split3[1] = %s\n", split2[1], split3[1]);
                Event e = new Event(name, start, end);
                inputList.add(e);
                return "Pikachu has added this task to the list!\n" + "   > " + e + "\n"
                        + "You now have " + inputList.size() + " tasks in the list.";
            } catch (Exception e) {
                return "Task description is empty/Empty or invalid event duration has been specified!";
            }
        }

        //"find"
        //Returns a list of all tasks containing the given keyword.
        //Syntax: find <keyword>
        if (split[0].toLowerCase().equals("find")) {
            String result = "Here are the matching tasks in your list:\n";
            int count = 1;
            String[] split2 = str.split(" ", 2);
            for (Task t : inputList) {
                if (!t.getName().contains(split2[1])) continue; //Current task does not contain the keyword
                result += ("   " + count + ". " + t + "\n");
                count += 1;
            }
            return result;
        }

        //For non-recognizable inputs
        return "Pikachu does not understand...";
    }
}

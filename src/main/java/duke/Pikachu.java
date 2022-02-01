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
    public void parseInput(String str) {
        String[] split = str.split(" ");

        /**
         * "commands"
         * Prints list of available commands.
         * Syntax: commands
         */
        if (str.toLowerCase().equals("commands")) {
            UI.printCommands();
            return;
        }

        /**
         * "list"
         * Prints current list of tasks in tasklist.
         * Syntax: list
         */
        if (str.toLowerCase().equals("list")) {
            //System.out.println("list command reached!");
            System.out.println("Here are the tasks in your list:");
            int count = 1;
            for (Task t : inputList) {
                System.out.println("   " + count + ". " + t);
                count += 1;
            }
            count -= 1;
            System.out.println("Now you have " + count + " task(s) in the list.");
            return;
        }

        /**
         * "mark"
         * Marks the task at the given index as done.
         * Syntax: mark <index of task>
         */
        if (split[0].toLowerCase().equals("mark")) {
            int index = 0;
            try {
                index = Integer.parseInt(split[1]) - 1;
            } catch (Exception e) {
                System.out.println("Invalid input for mark!");
                return;
            }
            if (index >= inputList.size() || index <= -1) { //Prevent invalid array accesses
                System.out.println("Invalid task number!");
                return;
            }
            Task t = inputList.get(index);
            t.mark();

            System.out.println("Pikachu has marked this task as done!\n   > " + t);
            return;
        }

        /**
         * "unmark"
         * Marks the task at the given index as not done.
         * Syntax: unmark <index of task>
         */
        if (split[0].toLowerCase().equals("unmark")) {
            int index = 0;
            try {
                index = Integer.parseInt(split[1]) - 1;
            } catch (Exception e) {
                System.out.println("Invalid input for unmark!");
                return;
            }
            if (index >= inputList.size() || index <= -1) {
                System.out.println("Invalid task number!");
                return;
            }
            Task t = inputList.get(index);
            t.unmark();

            System.out.println("Pikachu has marked this task as not done yet!\n   > " + t);
            return;
        }

        /**
         * "delete"
         * Deletes the task at the given index.
         * Syntax: delete <index of task>
         */
        if (split[0].toLowerCase().equals("delete")) {
            int index = 0;
            try {
                index = Integer.parseInt(split[1]) - 1;
            } catch (Exception e) {
                System.out.println("Invalid input for delete!");
                return;
            }
            if (index >= inputList.size() || index <= -1) { //Prevent invalid array accesses
                System.out.println("Invalid task number!");
                return;
            }
            Task t = inputList.remove(index);

            System.out.println("Pikachu has deleted this task!\n   > " + t);
            System.out.println("You now have " + inputList.size() + " tasks in the list.");
            return;
        }

        /**
         * "todo"
         * Creates a todo task.
         * Syntax: todo <taskname>
         */
        if (split[0].toLowerCase().equals("todo")) {
            String[] split2 = str.split(" ", 2);
            //System.out.printf("For debugging. split2[1] = %s\n", split2[1]);
            try {
                ToDo t = new ToDo(split2[1]);
                inputList.add(t);
                System.out.println("Pikachu has added this task to the list!");
                System.out.println("   > " + t);
                System.out.println("You now have " + inputList.size() + " tasks in the list.");
                return;
            } catch (Exception e) {
                System.out.println("Task description is empty!");
                return;
            }
        }

        /**
         * "deadline"
         * Creates a deadline task.
         * Syntax: deadline <taskname> /<yyyy-mm-dd hhmm of deadline>
         */
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
                System.out.println("Pikachu has added this task to the list!");
                System.out.println("   > " + d);
                System.out.println("You now have " + inputList.size() + " tasks in the list.");
                return;
            } catch (Exception e) {
                System.out.println("Task description is empty/Empty or invalid deadline timing has been specified!");
                return;
            }
        }

        /**
         * "event"
         * Creates an event task.
         * Syntax: event <taskname> /<yyyy-mm-dd hhmm of start> <yymmdd hhmm of end>
         */
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
                    System.out.println("End time cannot be earlier than start time!");
                    return;
                }
                //System.out.printf("For debugging. split2[1] = %s, split3[1] = %s\n", split2[1], split3[1]);
                Event e = new Event(name, start, end);
                inputList.add(e);
                System.out.println("Pikachu has added this task to the list!");
                System.out.println("   > " + e);
                System.out.println("You now have " + inputList.size() + " tasks in the list.");
                return;
            } catch (Exception e) {
                System.out.println("Task description is empty/Empty or invalid event duration has been specified!");
                return;
            }
        }

        /**
         * "find"
         * Returns a list of all tasks containing the given keyword.
         * Syntax: find <keyword>
         */
        if (split[0].toLowerCase().equals("find")) {
            System.out.println("Here are the matching tasks in your list:");
            int count = 1;
            String[] split2 = str.split(" ", 2);
            for (Task t : inputList) {
                if (!t.getName().contains(split2[1])) continue; //Current task does not contain the keyword
                System.out.println("   " + count + ". " + t);
                count += 1;
            }
            return;
        }

        //For non-recognizable inputs
        System.out.println("Pikachu does not understand...");
    }
}

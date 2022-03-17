package juke;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Parser {


    public static String taskToString(Task t) {
        return t.getDescription() + "%" + t.tag;
    }

    public static Task stringToTask(String s) {

        String[] splitTask = s.split("%");
        if (splitTask.length == 1) { //no tag
            if (splitTask[0].contains("T")) {
                return new Todo(splitTask[0].split("] ")[1], "");
            } else if (splitTask[0].contains("D")) {
                return new Deadline(splitTask[0].split("] ")[1],"");
            } else {
                return new Event(splitTask[0].split("] ")[1], "");
            }
        } else {
            if (splitTask[0].contains("T")) {
                return new Todo(splitTask[0].split("] ")[1], splitTask[1]);
            } else if (splitTask[0].contains("D")) {
                return new Deadline(splitTask[0].split("] ")[1],splitTask[1]);
            } else {
                return new Event(splitTask[0].split("] ")[1], splitTask[1]);
            }
        }
    }

    /**
     *
     * @param input
     * @return Juke's response based on the command
     */
    public String getResponse(String input, ArrayList<Task> itemList) {
        Outputs op = new Outputs();
        while(true) {
            String reply = input;
            String[] splittedString = input.split(" ");
            try {
                if (Tag.isTag(splittedString)) { // check for mark tag
                    return Tag.addTag(itemList, splittedString, reply, op);
                } else if (Mark.isMark(splittedString)) { // check for mark tag
                    return Mark.addMark(itemList, reply, op);
                } else if (Unmark.isUnmark(splittedString)) { // check for unmark tag
                    return Unmark.addUnmark(itemList, reply, op);
                } else if (Delete.isDelete(splittedString)) { //check for delete
                    return Delete.executeDelete(itemList, splittedString, op);
                } else if (Todo.isTodo(splittedString)) { // check for todo tag
                    return Todo.executeTodo(itemList, splittedString, reply, op);
                } else if (Deadline.isDeadline(splittedString)) { // check for deadline tag
                    return Deadline.executeDeadline(itemList, splittedString, reply, op);
                } else if (Event.isEvent(splittedString)) { // check for event tag
                    return Event.executeEvent(itemList, splittedString, reply, op);
                } else if (reply.equals("list")) { // check for list
                    return Generalcommands.executeList(itemList, op);
                } else if (splittedString[0].equals("find")) {
                    return Generalcommands.executeFind(itemList, splittedString, op);
                } else if (splittedString[0].equals("help")) {
                    return op.help;
                } else if (reply.equals("bye")) { // check for bye
                    System.exit(0);
                } else { //check non-existing commands
                    return op.border +
                            "     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" +
                            op.instructions +
                            op.border;
                }
            } finally {
                if (!new File("data/Juke.txt").exists()) {

                }
                try (PrintWriter out = new PrintWriter("data/Juke.txt")) {
                    for (Task t : itemList) {
                        out.println(Parser.taskToString(t));
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

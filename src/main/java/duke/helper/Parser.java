package duke.helper;

import duke.Duke;
import duke.exception.*;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.function.Predicate;

/**
 * Represents parser which parses user's input and String of texts and try to make sense of it.
 */
public class Parser {

    /**
     * Returns if the input String is a signal for exiting the program.
     * @param input the input user entered.
     * @return return if the input starts with "bye".
     */
    public static boolean isExit(String input) {
        return input.startsWith("bye");
    }

    /**
     * Returns if the keyword to be searched is in the given String array
     * @param nextLineArr an Array of String as input
     * @param keyword the keyword to be searched
     * @return whether the nextLineArr contains keyword
     */
    public static boolean hasKeyword(String[] nextLineArr, String keyword){
        for (int i = 0; i < nextLineArr.length; i++) {
            if(nextLineArr[i].equals(keyword)) {
                return true;
            }
        }
        return false;
    }
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-mm-dd");

    public static boolean isDate(String possibleDate) {
        try {
            LocalDate date = LocalDate.parse(possibleDate);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Returns the task parsed from the nextLine argument.
     * @param nextLine input as next line of String.
     * @return the task parsed from the line of input.
     */
    public static Task parseFileLine(String nextLine) {
        String[] strArr = nextLine.split(" \\| ");
        String[] subStrArr;
        String command = strArr[0];
        boolean isDone = strArr[1].equals("1") ? true : false;
        switch (command) {
        case "T":
            assert strArr.length == 3;
            return new Todo(strArr[2], isDone);
        case "D":
            assert strArr.length == 4;
            if (isDate(strArr[3])) {
                LocalDate ld = LocalDate.parse(strArr[3]);
                return new Deadline(strArr[2], ld, isDone);
            }
            return new Deadline(strArr[2], strArr[3], isDone);
        case "E":
            assert strArr.length == 4;
            if (isDate(strArr[3])) {
                LocalDate ld = LocalDate.parse(strArr[3]);
                return new Event(strArr[2], ld, isDone);
            }
            return new Event(strArr[2], strArr[3], isDone);
        default:
            assert false : nextLine;
            return null;
        }
    }

    /**
     * Returns the feedback of parsing the input line which is subsequently sent to an Ui object.
     * @param input input String given by the user.
     * @param taskList taskList to be updated.
     * @return feedback of the input to be handled to an Ui object for output in front of user.
     * @throws DukeException possible exceptions that may arise during parsing of the input.
     */
    public static String parseInputLine(String input, TaskList taskList) throws DukeException {
        String[] strArr = input.split(" ");
        String command = strArr[0];
        if (command.equals("bye")) {
            return null;
        }
        if (command.equals("list")) {
            StringBuilder message = new StringBuilder("Here are the tasks in your list:");
            for (int i = 1; i <= taskList.size(); i++) {
                int index = i - 1;
                message.append("\n" + i + ". " + taskList.get(index).toString());
            }
            return message.toString();
        }
        if (command.equals("mark")) {
            int index;
            try {
                index = Integer.parseInt(strArr[1]) - 1;
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeEmptyArgumentException();
            }
            Task temp;
            try {
                temp = taskList.get(index);
            } catch(IndexOutOfBoundsException e) {
                throw new DukeInvalidIndexException();
            }
            temp.markDone();
            return "Nice! I've marked this task as done:\n" + temp.toString();
        }

        if (command.equals("todo")) {
            String title;
            try {
                title = input.substring("todo".length() + 1).trim();
            } catch (StringIndexOutOfBoundsException e){
                throw new DukeEmptyArgumentException();
            }
            Task task = new Todo(title);
            taskList.insert(task);
            return "Got it. I've added this task:" + "\n" +
            "   " + task.toString() + "\n" + "Now you have " + taskList.size() + " tasks in the list.";
        }

        if (command.equals("deadline")) {
            if (strArr.length == 1) throw new DukeEmptyArgumentException();
            if (!hasKeyword(strArr, "/by")) {
                throw new DukeMissingArgumentException("/by");
            }
            String title;
            String[] splitArr = input.split("/by", 2);
            try {
                title = splitArr[0].substring("deadline".length() + 1).trim();
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeEmptyArgumentException();
            }
            String time = splitArr[1].trim();
            Task task;
            if (isDate(time)) {
                LocalDate ld = LocalDate.parse(time);
                task = new Deadline(title, ld);
            } else {
                task = new Deadline(title, time);
            }
            taskList.insert(task);
            return "Got it. I've added this task:" + "\n" + "   " + task.toString() + "\n" + "Now you have " + taskList.size() + " tasks in the list.";

        }
        if (command.equals("event")) {
            if (strArr.length == 1) throw new DukeEmptyArgumentException();
            if (!hasKeyword(strArr, "/at")) {
                throw new DukeMissingArgumentException("/at");
            }
            String title;
            String[] splitArr = input.split("/at", 2);
            try {
                title = splitArr[0].substring("event".length() + 1).trim();
            } catch (StringIndexOutOfBoundsException e){
                throw new DukeEmptyArgumentException();
            }
            String time = splitArr[1].trim();
            Task task;
            if (isDate(time)) {
                LocalDate ld = LocalDate.parse(time);
                task = new Event(title, ld);
            } else {
                task = new Event(title, time);
            }
            taskList.insert(task);
            return "Got it. I've added this task:" + "\n" +
                    "   " + task.toString() + "\n" + "Now you have " + taskList.size() + " tasks in the list.";
        }
        if (command.equals("delete")) {
            int index;
            try {
                index = Integer.parseInt(strArr[1]) - 1;
            } catch (ArrayIndexOutOfBoundsException e){
               throw new DukeEmptyArgumentException();
            }
            Task task;
            try {
                task = taskList.get(index);
                taskList.delete(index);
            } catch (IndexOutOfBoundsException e) {
                throw new DukeInvalidIndexException();
            }
            return "Noted. I've removed this task:" + "\n" + task.toString() + "\n"+
            "Now you have " + taskList.size() + " tasks in the list.";
        }
        if (command.equals("find")){
            String keyword;
            try {
                keyword = strArr[1];
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeEmptyArgumentException();
            }
            StringBuilder message = new StringBuilder("Here are the matching tasks in your list:");
            int j = 1;
            for (int i = 1; i <= taskList.size(); i++) {
                int index = i - 1;
                if (taskList.get(index).getDescription().contains(keyword)) {
                    message.append("\n" + j + ". " + taskList.get(index).toString());
                    j++;
                }
            }
            return message.toString();
        }
        if (command.equals("archive")) {
            Storage archiveStorage = new Storage("data/archive.txt");
            archiveStorage.save(taskList.getTaskList());
            taskList.deleteAll();
            return "your tasks have been archived, type 'unarchive' to  retrieve the archived list";
        }
        if (command.equals("unarchive")) {
            Storage archiveStorage = new Storage("data/archive.txt");
            taskList.addAll(archiveStorage.load());
            archiveStorage.deleteContent();
            return "you have unarchived your task, now they are in your task list !";
        }
        throw new DukeInvalidCommandException();
    }
}

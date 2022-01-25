package duke.command;

import java.util.Arrays;
import java.util.HashMap;

public class DukeCommands {

    public enum DUKE_COMMANDS {
        todo, deadline, event, mark, unmark, delete, list, bye, commands
    }

    public enum DUKE_DESCRIPTION_COMMANDS {
        todo, deadline, event, mark, unmark, delete
    }

    public enum ADD_COMMANDS {
        todo, deadline, event
    }

    public enum OUTPUT_COMMANDS {
        list
    }

    public enum EXIT_COMMANDS {
        bye
    }

    public enum MARK_COMMANDS {
        mark
    }

    public enum UNMARK_COMMANDS {
        unmark
    }

    public enum DELETE_COMMANDS {
        delete
    }

   static HashMap<String, String> hashMap = new HashMap<String, String>();

    public static boolean isDukeCommand(String string) {
        return Arrays.stream(DUKE_COMMANDS.values()).anyMatch((command) -> command.name().equals(string));
    }

    public static boolean isDukeDescriptionCommand(String string) {
        return Arrays.stream(DUKE_DESCRIPTION_COMMANDS.values()).anyMatch((command) -> command.name().equals(string));
    }

    public static HashMap<String, String> getTypeMap() {
        HashMap<String, String> h = new HashMap<>();
        h.put("list", "OUTPUT_COMMAND");
        h.put("todo", "ADD_COMMAND");
        h.put("event", "ADD_COMMAND");
        h.put("deadline", "ADD_COMMAND");
        h.put("mark", "MARK_COMMAND");
        h.put("unmark", "UNMARK_COMMAND");
        h.put("delete", "DELETE_COMMAND");
        h.put("bye", "EXIT_COMMAND");
        return h;
    }




    public static HashMap<String, String> getDukeCommandsDescription() {
        hashMap.put("list", "Produce a list of your todo, deadline, event tasks");
        hashMap.put("todo <your_task>", "Add a todo task into your task list. Enter a task after the todo command");
        hashMap.put("deadline <your_task> /by <date>", "Add a deadline task into your task list. Enter a task after" +
                "the deadline command, followed with \"by\" and the deadline date");
        hashMap.put("event <your_task> /at <date>", "Add a event task into your task list. Enter a task after" +
                "the event command, followed with \"at\" and the event date");
        hashMap.put("mark <task_number>", "Mark a task number in the list as done");
        hashMap.put("unmark <task_number>", "Unmark a task number in the list as done");
        hashMap.put("delete <task_number>", "Delete a task number from the task list");
        hashMap.put("bye", "Close the Ekud bot");
        return hashMap;
    }


}
